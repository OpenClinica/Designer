package org.akaza.openclinica.designer.web.controller;

import org.akaza.openclinica.designer.web.controller.FlashMap.Message;
import org.akaza.openclinica.designer.web.controller.FlashMap.MessageType;
import org.akaza.openclinica.designer.web.fields.InputField;
import org.openclinica.ns.response.v31.MessagesType;
import org.openclinica.ns.response.v31.Response;
import org.openclinica.ns.rules.v31.RuleAssignmentType;
import org.openclinica.ns.rules.v31.Rules;
import org.openclinica.ns.rules_test.v31.ParameterType;
import org.openclinica.ns.rules_test.v31.RulesTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

@Controller
@RequestMapping(value = "/ruleBuilder")
public class RuleBuilderController {

    @Autowired
    private Unmarshaller unMarshaller;

    @Autowired
    private Marshaller marshaller;

    @Autowired
    private UserPreferences userPreferences;

    private MessageSource messageSource;
    protected final Logger logger = LoggerFactory.getLogger(getClass().getName());

    private static final Message SAVED_MESSAGE = new Message(MessageType.saved, "Submitted successfully to your instance");
    private static final Message VALID_RULE_MESSAGE = new Message(MessageType.success, "This is a valid Rule");
    private static final Message INVALID_XML_MESSAGE = new Message(MessageType.error, "Could not transform XML");
    private static final String DUPLICATE_MESSAGE = "DUPLICATE";

    private static final String SESSION_ATTR_UIODMCONTAINER = "uiODMContainer";
    private static final String SESSION_ATTR_TESTINPUTFIELDS = "testInputFields";
    private static final String SESSION_ATTR_FORM = "form";

    private static final String PARAM_RESET = "reset";

    @InitBinder("rulesCommand")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new RulesCommandValidator());
    }

    public static boolean isAjaxRequest(String requestedWith) {
        return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
    }

    public static boolean isAjaxUploadRequest(HttpServletRequest request) {
        return request.getParameter("ajaxUpload") != null;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getCreateForm(Model model, HttpSession session, HttpServletRequest request) throws IOException {

        final String ruleOid = request.getParameter("ruleOid");
        final String target = request.getParameter("target");

        if (ruleOid != null && target != null) {
            final UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute(SESSION_ATTR_UIODMCONTAINER);
            session.setAttribute(SESSION_ATTR_FORM, uiODMContainer.getRuleCommandByRuleOidAndTarget(ruleOid, target));
            userPreferences.turnOnEditMode();
        }
        return "ruleBuilder";
    }

    @RequestMapping(value = "/ruleBuilderFormA", method = RequestMethod.GET)
    public void form(@RequestHeader(value = "X-Requested-With", required = false) String requestedWith, HttpSession session, Model model,
            @RequestParam(value = PARAM_RESET, required = false) String resetParam) {

        RulesCommand form = (RulesCommand) session.getAttribute(SESSION_ATTR_FORM) != null ? (RulesCommand) session.getAttribute(SESSION_ATTR_FORM) : null;

        if (form == null || (resetParam != null && resetParam.equals("true"))) {
            form = new RulesCommand();
            session.setAttribute(SESSION_ATTR_FORM, form);
            userPreferences.turnOffEditMode();
        }

        model.addAttribute(form);
        model.addAttribute("ajaxRequest", isAjaxRequest(requestedWith));
    }

    /**
     * Auto-POST the form when user is moving from Design to Test tab.
     */
    @RequestMapping(value = "/processDesignTabToTest", method = RequestMethod.POST)
    public @ResponseBody
    ArrayList<Message> processDesignTabToTest(@RequestHeader(value = "X-Requested-With", required = false) String requestedWith, @Valid RulesCommand form,
            BindingResult result, HttpSession session, Model model) throws IOException {

        ArrayList<Message> messages = new ArrayList<Message>();
        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute(SESSION_ATTR_UIODMCONTAINER);
        // TODO: See if there is a way to move this to one place currently happens here and in Validator.
        form.getRuleRef().lazyToNonLazy();

        if (result.hasErrors()) {
            messages.add(new Message(MessageType.error, "Error"));
            model.addAttribute("messages", messages);
            model.addAttribute("ajaxRequest", isAjaxRequest(requestedWith));
            return messages;
        }

        messages = validateRule(uiODMContainer, rulesCommandToRules(form), new DefaultResponseHandler());

        session.setAttribute(SESSION_ATTR_FORM, form);

        // success response handling
        if (isAjaxRequest(requestedWith)) {
            model.addAttribute("messages", messages);
            model.addAttribute("ajaxRequest", true);
            return messages;
        } else {
            return messages;
        }
    }

    /**
     * Auto-POST the form when user is moving from Design to Xml tab.
     */
    @RequestMapping(value = "/processDesignTabSubmit", method = RequestMethod.POST)
    public @ResponseBody
    String processDesignTabSubmit(@RequestHeader(value = "X-Requested-With", required = false) String requestedWith, RulesCommand form, BindingResult result,
            HttpSession session, Model model) throws IOException {
        form.getRuleRef().lazyToNonLazy();
        ArrayList<Message> messages = new ArrayList<Message>();

        if (result.hasErrors()) {
            model.addAttribute("ajaxRequest", isAjaxRequest(requestedWith));
            return null;
        }

        session.setAttribute(SESSION_ATTR_FORM, form);

        if (isAjaxRequest(requestedWith)) {
            model.addAttribute("messages", messages);
            model.addAttribute("ajaxRequest", true);
            return null;
        } else {
            return "redirect:/form";
        }
    }

    /**
     * POST the form when user clicks the validate link in the Design Tab.
     */
    @RequestMapping(value = "/ruleBuilderFormA", method = RequestMethod.POST)
    public String processSubmit(@RequestHeader(value = "X-Requested-With", required = false) String requestedWith, @Valid RulesCommand form,
            BindingResult result, HttpSession session, Model model) throws IOException {

    	logger.debug("Validating form ...");
        ArrayList<Message> messages = new ArrayList<Message>();
        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute(SESSION_ATTR_UIODMCONTAINER);
        // TODO: See if there is a way to move this to one place currently happens here and in Validator.
        form.getRuleRef().lazyToNonLazy();

        if (result.hasErrors()) {
            model.addAttribute("ajaxRequest", isAjaxRequest(requestedWith));
            return null;
        }

        messages = validateRule(uiODMContainer, rulesCommandToRules(form), new DefaultResponseHandler(VALID_RULE_MESSAGE));

        session.setAttribute(SESSION_ATTR_FORM, form);

        // success response handling
        if (isAjaxRequest(requestedWith)) {
            model.addAttribute("messages", messages);
            model.addAttribute("ajaxRequest", true);
            return null;
        } else {
            return "redirect:/form";
        }
    }

    /**
     * POST the form when user clicks the save link in the Design Tab.
     */
    @RequestMapping(value = "/saveRuleFromDesignerTab", method = RequestMethod.POST)
    public String saveRuleFromDesignerTab(@RequestHeader(value = "X-Requested-With", required = false) String requestedWith, @Valid RulesCommand form,
            BindingResult result, HttpSession session, Model model, @RequestParam("ignoreDuplicates") Boolean ignoreDuplicates) throws IOException {
        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute(SESSION_ATTR_UIODMCONTAINER);
        form.getRuleRef().lazyToNonLazy();
        ArrayList<Message> messages = new ArrayList<Message>();

        if (result.hasErrors()) {
            model.addAttribute("ajaxRequest", isAjaxRequest(requestedWith));
            return null;
        }

        if (userPreferences.getEditMode() == Boolean.TRUE) {
            messages = saveRule(uiODMContainer, rulesCommandToRules(form), new DefaultResponseHandler(SAVED_MESSAGE), true);
        } else {
            messages = saveRule(uiODMContainer, rulesCommandToRules(form), new DefaultResponseHandler(SAVED_MESSAGE), ignoreDuplicates);
        }

        session.setAttribute(SESSION_ATTR_FORM, form);

        // success response handling
        if (isAjaxRequest(requestedWith)) {
            model.addAttribute("messages", messages);
            model.addAttribute("ajaxRequest", true);
            return null;
        } else {
            return "redirect:/form";
        }
    }

    /**
     * GET the XML Tab.
     */
    @RequestMapping(value = "/xmlForm", method = RequestMethod.GET)
    public void processSubmit(@RequestHeader(value = "X-Requested-With", required = false) String requestedWith, HttpSession session, Model model,
            @RequestParam(value = PARAM_RESET, required = false) String resetParam) throws IOException {

        RulesCommand form = (RulesCommand) session.getAttribute(SESSION_ATTR_FORM);
        if (resetParam != null && resetParam.equals("true")) {
            form = new RulesCommand();
            session.setAttribute(SESSION_ATTR_FORM, form);
            userPreferences.turnOffEditMode();
        }

        Rules r = rulesCommandToRules(form);

        try {
            StringWriter writer = new StringWriter();
            StreamResult theResult = new StreamResult(writer);
            this.marshaller.marshal(r, theResult);
            String xml = theResult.getWriter().toString();
            form.setXml(xml);
            model.addAttribute(form);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XmlMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
        }

        session.setAttribute(SESSION_ATTR_FORM, form);
        model.addAttribute("ajaxRequest", isAjaxRequest(requestedWith));
    }

    /**
     * POST the XML form when user clicks the save link in the XML Tab.
     */
    @RequestMapping(value = "/xmlForm", method = RequestMethod.POST)
    public String processXmlFormSubmit(@RequestHeader(value = "X-Requested-With", required = false) String requestedWith, RulesCommand form,
            BindingResult result, HttpSession session, Model model, @RequestParam("ignoreDuplicates") Boolean ignoreDuplicates) throws IOException {

        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute(SESSION_ATTR_UIODMCONTAINER);
        ArrayList<Message> messages = new ArrayList<Message>();

        Rules r = null;
        try {
            r = loadRulesFromString(form.getXml());
        } catch (UnMarshallingException e) {
            messages.add(INVALID_XML_MESSAGE);
            model.addAttribute("messages", messages);
            model.addAttribute("ajaxRequest", isAjaxRequest(requestedWith));
            return null;
        }

        messages = saveRule(uiODMContainer, r, new DefaultResponseHandler(SAVED_MESSAGE), ignoreDuplicates);

        form.setTarget(r.getRuleAssignment().get(0).getTarget());
        form.setRuleDef(r.getRuleDef().get(0));
        LazyRuleRefType2 ruleRefType = new LazyRuleRefType2(r.getRuleAssignment().get(0).getRuleRef().get(0));
        form.setRuleRef(ruleRefType);
        session.setAttribute(SESSION_ATTR_FORM, form);

        if (isAjaxRequest(requestedWith)) {
            model.addAttribute("messages", messages);
            model.addAttribute("ajaxRequest", true);
            return null;
        } else {
            return "redirect:/form";
        }
    }

    /**
     * POST the XML form when user clicks the validate link in the XML Tab.
     */
    @RequestMapping(value = "/xmlFormValidate", method = RequestMethod.POST)
    public String xmlTabValidate(@RequestHeader(value = "X-Requested-With", required = false) String requestedWith, RulesCommand form, BindingResult result,
            HttpSession session, Model model) throws IOException {
        ArrayList<Message> messages = new ArrayList<Message>();
        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute(SESSION_ATTR_UIODMCONTAINER);

        Rules r = null;
        try {
            r = loadRulesFromString(form.getXml());
        } catch (UnMarshallingException e) {
            messages.add(INVALID_XML_MESSAGE);
            model.addAttribute("messages", messages);
            model.addAttribute("ajaxRequest", isAjaxRequest(requestedWith));
            return null;
        }

        messages = validateRule(uiODMContainer, r, new DefaultResponseHandler(VALID_RULE_MESSAGE));

        form.setTarget(r.getRuleAssignment().get(0).getTarget());
        form.setRuleDef(r.getRuleDef().get(0));
        LazyRuleRefType2 ruleRefType = new LazyRuleRefType2(r.getRuleAssignment().get(0).getRuleRef().get(0));
        form.setRuleRef(ruleRefType);
        session.setAttribute(SESSION_ATTR_FORM, form);

        // success response handling
        if (isAjaxRequest(requestedWith)) {
            model.addAttribute("messages", messages);
            model.addAttribute("ajaxRequest", true);
            return null;
        } else {
            return "redirect:/form";
        }
    }

    /**
     * Auto-POST the XML form when user is moving from XML to Test tab.
     */
    @RequestMapping(value = "/xmlTabSubmitToTestTab", method = RequestMethod.POST)
    public @ResponseBody
    ArrayList<Message> xmlTabSubmitToTestTab(@RequestHeader(value = "X-Requested-With", required = false) String requestedWith, RulesCommand form,
            BindingResult result, HttpSession session, Model model) throws IOException {
        ArrayList<Message> messages = new ArrayList<Message>();
        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute(SESSION_ATTR_UIODMCONTAINER);

        Rules r = null;
        try {
            r = loadRulesFromString(form.getXml());
        } catch (UnMarshallingException e) {
            messages.add(INVALID_XML_MESSAGE);
            model.addAttribute("messages", messages);
            model.addAttribute("ajaxRequest", isAjaxRequest(requestedWith));
            return messages;
        }

        messages = validateRule(uiODMContainer, r, new DefaultResponseHandler());

        form.setTarget(r.getRuleAssignment().get(0).getTarget());
        form.setRuleDef(r.getRuleDef().get(0));
        LazyRuleRefType2 ruleRefType = new LazyRuleRefType2(r.getRuleAssignment().get(0).getRuleRef().get(0));
        form.setRuleRef(ruleRefType);
        session.setAttribute(SESSION_ATTR_FORM, form);

        // success response handling
        if (isAjaxRequest(requestedWith)) {
            model.addAttribute("messages", messages);
            model.addAttribute("ajaxRequest", true);
            return messages;
        } else {
            return messages;
        }
    }

    /**
     * Auto-POST the XML form when user is moving from XML to Design tab.
     */
    @RequestMapping(value = "/processXmlTabSubmit", method = RequestMethod.POST)
    public @ResponseBody
    ArrayList<Message> processXmlTabSubmit(@RequestHeader(value = "X-Requested-With", required = false) String requestedWith, RulesCommand form,
            BindingResult result, HttpSession session, Model model) throws IOException {
        ArrayList<Message> messages = new ArrayList<Message>();

        if (result.hasErrors()) {
            model.addAttribute("ajaxRequest", isAjaxRequest(requestedWith));
            return messages;
        }

        Rules r = null;
        try {
            r = loadRulesFromString(form.getXml());
            form.setTarget(r.getRuleAssignment().get(0).getTarget());
            form.setRuleDef(r.getRuleDef().get(0));
            LazyRuleRefType2 ruleRefType = new LazyRuleRefType2(r.getRuleAssignment().get(0).getRuleRef().get(0));
            form.setRuleRef(ruleRefType);
            session.setAttribute(SESSION_ATTR_FORM, form);
        } catch (UnMarshallingException e) {
            messages.add(INVALID_XML_MESSAGE);
        }

        // success response handling
        if (isAjaxRequest(requestedWith)) {
            model.addAttribute("messages", messages);
            model.addAttribute("ajaxRequest", true);
            return messages;
        } else {
            return messages;
        }
    }

    /**
     * GET the test tab.
     */
    @RequestMapping(value = "/testForm", method = RequestMethod.GET)
    public String testForm(@RequestHeader(value = "X-Requested-With", required = false) String requestedWith, HttpSession session, Model model)
            throws IOException {

        ArrayList<Message> messages = new ArrayList<Message>();
        RulesCommand form = (RulesCommand) session.getAttribute(SESSION_ATTR_FORM);
        form.getRuleRef().lazyToNonLazy();
        form.setTestRulesResults(new HashMap<String, String>());

        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute(SESSION_ATTR_UIODMCONTAINER);
        UIODMBuilder uiODMBuilder = new UIODMBuilder(uiODMContainer);

        /*
         * RulesTest restResponse = null; List<InputField> inputFields = new ArrayList<InputField>(); try { restResponse =
         * testRule(createRulesTestFromCommand(form)); } catch (Exception e) { messages.add(createRestExceptionMessage(e));
         * model.addAttribute("messages", messages); model.addAttribute("ajaxRequest", true); return null; } for
         * (ParameterType parameterType : restResponse.getParameters()) { if (!parameterType.getKey().equals("result") &&
         * !parameterType.getKey().equals("ruleEvaluatesTo") && !parameterType.getKey().equals("ruleValidation")) {
         * UIItemDetail itemDetail = uiODMBuilder.buildItemDetail(parameterType.getKey());
         * form.getRuleProperties().put(parameterType.getKey(), parameterType.getValue());
         * inputFields.add(InputFieldFactory.createInputField("ruleProperties['" + parameterType.getKey() + "']",
         * parameterType.getKey(), itemDetail)); } } form.setRulePropertiesHtml(inputFields);
         * session.setAttribute(SESSION_ATTR_TESTINPUTFIELDS, inputFields);
         */

        testRules(form, uiODMBuilder, new TestRulesGetResponseHandler());
        session.setAttribute(SESSION_ATTR_TESTINPUTFIELDS, form.getRulePropertiesHtml());

        // success response handling
        if (isAjaxRequest(requestedWith)) {
            model.addAttribute("messages", messages);
            model.addAttribute("ajaxRequest", true);
            model.addAttribute(form);
            return null;
        } else {
            return "redirect:/form";
        }

    }

    /**
     * POST the test form when user clicks the test link in the test tab.
     */
    @RequestMapping(value = "/testForm", method = RequestMethod.POST)
    public String processTestSubmit(@RequestHeader(value = "X-Requested-With", required = false) String requestedWith, @Valid RulesCommand form,
            BindingResult result, HttpSession session, Model model) throws IOException {

        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute(SESSION_ATTR_UIODMCONTAINER);
        UIODMBuilder uiODMBuilder = new UIODMBuilder(uiODMContainer);

        List<InputField> inputFields = (ArrayList<InputField>) session.getAttribute(SESSION_ATTR_TESTINPUTFIELDS);
        form.setRulePropertiesHtml(inputFields);
        BeanWrapper ruleCommandBeanWrapper = new BeanWrapperImpl(form);
        for (InputField inputField : inputFields) {
            inputField.validate(ruleCommandBeanWrapper, result);
        }

        if (result.hasErrors()) {
            model.addAttribute("ajaxRequest", isAjaxRequest(requestedWith));
            return null;
        }

        form.getRuleRef().lazyToNonLazy();
        ArrayList<Message> messages = new ArrayList<Message>();

        testRules(form, uiODMBuilder, new TestRulesSubmitResponseHandler());
        form.populateTestWillActionsRun();
        session.setAttribute(SESSION_ATTR_FORM, form);

        // success response handling
        if (isAjaxRequest(requestedWith)) {
            model.addAttribute(form);
            model.addAttribute("messages", messages);
            model.addAttribute("ajaxRequest", true);
            return null;
        } else {
            return "redirect:/form";
        }
    }

    private Message createRestExceptionMessage(Exception e) {
        String message = e.getMessage() == null ? e.getClass().getName() : e.getMessage();
        return new Message(MessageType.error, "Could not submit to OC instance because :: " + message);
    }

    private void testRules(RulesCommand form, UIODMBuilder uiODMBuilder, TestRulesResponseHandler responseHandler) {
        ArrayList<Message> messages = new ArrayList<Message>();
        RulesTest resp = null;
        try {
            resp =
                userPreferences.getRestTemplate().postForObject(userPreferences.getValidateAndTestRuleURL(), createRulesTestFromCommand(form), RulesTest.class);
            responseHandler.handle(form, resp, uiODMBuilder);
            return;
        } catch (Exception e) {
            messages.add(createRestExceptionMessage(e));
        }

    }

    private ArrayList<Message> callREST(UIODMContainer uiODMContainer, Rules rule, ResponseHandler responseHandler, String url) {
        ArrayList<Message> messages = new ArrayList<Message>();
        Map<String, String> vars = Collections.singletonMap("study", uiODMContainer.getStudyOid());
        Response resp = null;
        try {
            resp = userPreferences.getRestTemplate().postForObject(url, rule, Response.class, vars);
            if (resp.isValid()) {
                messages.addAll(responseHandler.handle());
            } else {
                for (MessagesType messageType : resp.getMessages()) {
                    if (messageType.getMessage().equals(DUPLICATE_MESSAGE))
                        messages.add(new Message(MessageType.warning, messageType.getMessage()));
                    else
                        messages.add(new Message(MessageType.error, messageType.getMessage()));
                }
            }
        } catch (Exception e) {
            messages.add(createRestExceptionMessage(e));
        }

        return messages;
    }

    private ArrayList<Message> validateRule(UIODMContainer uiODMContainer, Rules rule, ResponseHandler responseHandler) {
        return callREST(uiODMContainer, rule, responseHandler, userPreferences.getValidateRuleURL());
    }

    private ArrayList<Message> saveRule(UIODMContainer uiODMContainer, Rules rule, ResponseHandler responseHandler, Boolean ignoreDuplicates) {
        if (ignoreDuplicates)
            return callREST(uiODMContainer, rule, responseHandler, userPreferences.getValidateAndSaveOrUpdateRuleURL());
        else
            return callREST(uiODMContainer, rule, responseHandler, userPreferences.getValidateAndSaveRuleURL());
    }

    /**
     * Convert RulesCommand to Rules Object
     */
    private Rules rulesCommandToRules(RulesCommand form) {
        form.getRuleRef().lazyToNonLazy();
        RuleAssignmentType ra = new RuleAssignmentType();
        form.getTarget().setValue(form.getTarget().getValue() == null ? "" : form.getTarget().getValue().trim());
        ra.setTarget(form.getTargetCurated(form.getTarget()));
        ra.getRuleRef().add(form.getRuleRef());
        ra.getRuleRef().get(0).setOID(form.getRuleDef().getOID());
        Rules r = new Rules();
        r.getRuleAssignment().add(ra);
        r.getRuleDef().add(form.getRuleDefCurated());
        r.getRuleDef().get(0).setName(r.getRuleDef().get(0).getOID());
        r.getRuleDef().get(0).setDescription(r.getRuleDef().get(0).getOID());
        return r;

    }

    /**
     * Convert RulesCommand to RulesTest Object
     */

    private RulesTest createRulesTestFromCommand(RulesCommand form) {
        RulesTest ruleTest = new RulesTest();
        ruleTest.setRules(rulesCommandToRules(form));

        // Assign Parameters from Form
        for (Map.Entry<String, String> entry : form.getRuleProperties().entrySet()) {
            ParameterType parameter = new ParameterType();
            parameter.setKey(entry.getKey());
            parameter.setValue(entry.getValue());
            ruleTest.getParameters().add(parameter);
        }
        return ruleTest;
    }

    /**
     * Convert XML which is represented as a String to Rules Object
     */
    private Rules loadRulesFromString(String rulesString) throws UnMarshallingException {

        StringReader reader = new StringReader(rulesString);
        Rules rules = null;
        try {
            rules = (Rules) this.unMarshaller.unmarshal(new StreamSource(reader));
        } catch (FileNotFoundException e) {
            throw new UnMarshallingException();
            // TODO Auto-generated catch block
            // e.printStackTrace();
        } catch (XmlMappingException e) {
            throw new UnMarshallingException();
            // TODO Auto-generated catch block
            // e.printStackTrace();
        } catch (IOException e) {
            throw new UnMarshallingException();
            // TODO Auto-generated catch block
            // e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return rules;

    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /*
     * @RequestMapping(value = "/saveRuleFromDesignTab", method = RequestMethod.POST) public String
     * saveRuleFromDesignTab(@RequestHeader(value = "X-Requested-With", required = false) String requestedWith, @Valid
     * RulesCommand form, BindingResult result, HttpSession session, Model model) throws IOException {
     * form.getRuleRef().lazyToNonLazy(); ArrayList<Message> messages = new ArrayList<Message>(); if (result.hasErrors()) {
     * model.addAttribute("ajaxRequest", isAjaxRequest(requestedWith)); return null; } Rules r = rulesCommandToRules(form);
     * UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute("uiODMContainer"); Map<String, String> vars =
     * Collections.singletonMap("study", uiODMContainer.getOdm().getStudy().get(0).getOID()); Response resp = null; try { resp
     * = userPreferences.getRestTemplate().postForObject(userPreferences.getValidateAndSaveRuleURL(), r, Response.class,
     * vars); if (resp.isValid()) { messages.add(new Message(MessageType.saved, "Submitted successfully to your instance")); }
     * else { for (MessagesType messageType : resp.getMessages()) { messages.add(new Message(MessageType.error,
     * messageType.getMessage())); } } } catch (Exception e) { messages.add(new Message(MessageType.error,
     * "Could not submit to OC instance because :: " + e.getMessage())); } session.setAttribute("form", form); // success
     * response handling if (isAjaxRequest(requestedWith)) { model.addAttribute("messages", messages);
     * model.addAttribute("ajaxRequest", true); return null; } else { return "redirect:/form"; } }
     */

    /*
     * private void executeTestRulesRest2(String studyOid, RulesCommand form, Boolean submitTest, UIODMBuilder uiODMBuilder) {
     * TestRulesSubmitResponseHandler testRulesSubmitResponseHandler = new TestRulesSubmitResponseHandler(); RulesTest resp =
     * null; try { resp = userPreferences.getRestTemplate().postForObject(userPreferences.getValidateAndTestRuleURL(),
     * createRulesTestFromCommand(form), RulesTest.class); testRulesSubmitResponseHandler.handle(form, resp); return; } catch
     * (Exception e) { // messages.add(new Message(MessageType.error, "Could not submit to OC instance because :: " +
     * e.getMessage())); } } private void executeTestRulesRest(String studyOid, RulesCommand form, Boolean submitTest,
     * UIODMBuilder uiODMBuilder) { RulesTest resp = null; try { resp =
     * userPreferences.getRestTemplate().postForObject(userPreferences.getValidateAndTestRuleURL(),
     * createRulesTestFromCommand(form), RulesTest.class); if (submitTest) { loadRuleProperties(form, resp.getParameters(),
     * resp.getRulesTestMessages(), uiODMBuilder); } else { loadExpressionProperties(form, resp.getParameters(),
     * resp.getRulesTestMessages(), uiODMBuilder); } return; } catch (Exception e) { // messages.add(new
     * Message(MessageType.error, "Could not submit to OC instance because :: " + e.getMessage())); } }
     */

    /*
     * public ODM getODM(HttpSession session) { return (ODM) session.getAttribute("odm"); }
     */

    /*
     * private ODM loadStudyMetadata() throws IOException { // final String FILE_NAME =
     * "/Users/kkrikor/Desktop/DownloadStudyMetadata.xml"; final String FILE_NAME =
     * "/Users/kkrikor/Desktop/MetadataFromSvn.xml"; // final String FILE_NAME = "c:\\MetadataFromSvn.xml"; ODM odm = null;
     * FileInputStream is = null; try { is = new FileInputStream(FILE_NAME); odm = (ODM) this.unMarshaller.unmarshal(new
     * StreamSource(is)); } catch (FileNotFoundException e) { // TODO Auto-generated catch block e.printStackTrace(); } catch
     * (XmlMappingException e) { // TODO Auto-generated catch block e.printStackTrace(); } catch (IOException e) { // TODO
     * Auto-generated catch block e.printStackTrace(); } finally { if (is != null) { is.close(); } } return odm; }
     */

    /*
     * private Rules createRulesFromCommand(RulesCommand form) { RuleAssignmentType ra = new RuleAssignmentType();
     * form.getTarget().setValue(form.getTarget().getValue().trim()); ra.setTarget(form.getTarget());
     * ra.getRuleRef().add(form.getRuleRef()); ra.getRuleRef().get(0).setOID(form.getRuleDef().getOID()); Rules r = new
     * Rules(); r.getRuleAssignment().add(ra); r.getRuleDef().add(form.getRuleDef());
     * r.getRuleDef().get(0).setName(r.getRuleDef().get(0).getOID());
     * r.getRuleDef().get(0).setDescription(r.getRuleDef().get(0).getOID()); return r; }
     */

    /*
     * private String getStudyOidFromSession(HttpSession session) { UIODMContainer uiODMContainer = (UIODMContainer)
     * session.getAttribute(SESSION_ATTR_UIODMCONTAINER); return uiODMContainer.getOdm().getStudy().get(0).getOID(); } private
     * void loadExpressionProperties(RulesCommand form, List<ParameterType> parameters, List<RulesTestMessagesType> messages,
     * UIODMBuilder uiODMBuilder) { for (ParameterType parameterType : parameters) { form.setTestRulesResults(new
     * HashMap<String, String>()); if (parameterType.getKey().equals("result") ||
     * parameterType.getKey().equals("ruleEvaluatesTo") || parameterType.getKey().equals("ruleValidation")) { //
     * form.getTestRulesResults().put(parameterType.getKey(), parameterType.getValue()); } else { UIItemDetail itemDetail =
     * uiODMBuilder.buildItemDetail(parameterType.getKey()); form.getRuleProperties().put(parameterType.getKey(),
     * parameterType.getValue()); // form.getRulePropertiesHtml().add(InputFieldFactory.createTextField("ruleProperties['" +
     * // parameterType.getKey() + "']", parameterType.getKey())); form.getRulePropertiesHtml().add(
     * InputFieldFactory.createInputField("ruleProperties['" + parameterType.getKey() + "']", parameterType.getKey(),
     * itemDetail)); } } } private void loadRuleProperties(RulesCommand form, List<ParameterType> parameters,
     * List<RulesTestMessagesType> messages, UIODMBuilder uiODMBuilder) { for (ParameterType parameterType : parameters) { if
     * (parameterType.getKey().equals("result") || parameterType.getKey().equals("ruleEvaluatesTo") ||
     * parameterType.getKey().equals("ruleValidation")) { form.getTestRulesResults().put(parameterType.getKey(),
     * parameterType.getValue()); } else { UIItemDetail itemDetail = uiODMBuilder.buildItemDetail(parameterType.getKey());
     * form.getRuleProperties().put(parameterType.getKey(), parameterType.getValue()); } } for (RulesTestMessagesType
     * messageType : messages) { form.getTestRulesResults().put(messageType.getKey(), messageType.getValue()); } }
     */

    // EVENTUALLY MOVE TO Utils class
    /*
     * public void initializeCrfs(HttpSession session) throws IOException { if (session.getAttribute("uiODMContainer") !=
     * null) { return; } ODM odm = loadStudyMetadata(); UIODMBuilder uiODMBuilder = new UIODMBuilder(odm);
     * uiODMBuilder.build(); session.setAttribute("uiODMContainer", uiODMBuilder.getContainer()); }
     */
}