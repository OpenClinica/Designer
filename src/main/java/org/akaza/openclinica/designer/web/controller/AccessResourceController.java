package org.akaza.openclinica.designer.web.controller;

import org.akaza.openclinica.designer.core.DisableSSLHostNameVerifier;
import org.akaza.openclinica.designer.core.NaiveTrustProvider;
import org.akaza.openclinica.designer.web.HostAccessService;
import org.cdisc.ns.odm.v130.ODM;
import org.openclinica.ns.response.v31.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.transform.stream.StreamSource;

@RequestMapping("/access")
@Controller
public class AccessResourceController {

    @Autowired
    Unmarshaller unMarshaller;
    @Autowired
    UserPreferences userPreferences;
    @Autowired
    HostAccessService hostAccessService;

    private static final String PARAM_RULE_OID = "ruleOid";
    private static final String PARAM_TARGET = "target";
    private static final String PARAM_HOST = "host";
    private static final String PARAM_APP = "app";
    private static final String PARAM_APP_PATH = "path";
    private static final String PARAM_STUDY_OID = "study_oid";
    private static final String PARAM_PROVIDER_USER = "provider_user";
    private static final String SESSION_ATTR_FORM = "form";
    
    protected final Logger logger = LoggerFactory.getLogger(getClass().getName());

    public AccessResourceController() {
        disableSSLChecks();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String createForm(Model model, HttpSession session, HttpServletRequest request, @RequestParam(PARAM_HOST) String providerHost,
            @RequestParam(PARAM_APP) String providerApp, @RequestParam(PARAM_STUDY_OID) String studyOid,
            @RequestParam(PARAM_PROVIDER_USER) String providerUser, @RequestParam(value = PARAM_APP_PATH, required = false) String path,
            @RequestParam(value = PARAM_RULE_OID, required = false) String ruleOid, @RequestParam(value = PARAM_TARGET, required = false) String target)
            throws Exception {

        if (!hostAccessService.isHostAllowedAccess(providerHost)) {
            return "index";
        }
        logger.debug("Host is Valid ...");

        userPreferences.setAppName(providerApp);
        userPreferences.setPath(path);
        userPreferences.setUser(providerUser);
        userPreferences.setHost(providerHost);
        userPreferences.setStudyOid(studyOid);
        userPreferences.initRestTemplate();
        ODM odm = getMetadata();
        UIODMBuilder uiODMBuilder = new UIODMBuilder(odm);
        uiODMBuilder.build();
        session.setAttribute("uiODMContainer", uiODMBuilder.getContainer());
        // TODO: Why not use attrs directly from userPreferences ? Does it not work in Filter ?
        session.setAttribute("providerUser", userPreferences.getUser());
        session.setAttribute("providerHost", userPreferences.getHost());
        if (ruleOid != null && target != null) {
            session.setAttribute(SESSION_ATTR_FORM, uiODMBuilder.getContainer().getRuleCommandByRuleOidAndTarget(ruleOid, target));
            userPreferences.turnOnEditMode();
        }
        // doRest();
        return "ruleBuilder";
    }

    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public String exit(HttpServletRequest request, HttpSession session) throws Exception {

        String url = userPreferences.getExitURL();
        session.invalidate();
        return "redirect:" + url;
    }

    @RequestMapping(value = "/refreshSession", method = RequestMethod.GET)
    public @ResponseBody
    String refreshSession() throws IOException {
        return "sessionRefreshed";
    }

    private void doRest() {
        Response resp = new Response();
        String uri = userPreferences.getConnectURL();
        try {
            resp = userPreferences.getRestTemplate().postForObject(uri, resp, Response.class);
        } catch (Exception exception) {
            throw new IllegalStateException(exception);
        }
    }

    @RequestMapping(value = "/initMetadata", method = RequestMethod.GET)
    public ModelAndView initMetadata(Model model, HttpSession session, @RequestParam("host") String providerHost, @RequestParam("app") String providerApp,
            @RequestParam("study_oid") String studyOid) throws Exception {
        // model.addAttribute("contact", new Contact());
        userPreferences.setAppName(providerApp);
        userPreferences.setHost(providerHost);
        userPreferences.setStudyOid(studyOid);
        userPreferences.initRestTemplate();
        ODM odm = getMetadata();
        UIODMBuilder uiODMBuilder = new UIODMBuilder(odm);
        uiODMBuilder.build();
        session.setAttribute("uiODMContainer", uiODMBuilder.getContainer());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("ruleBuilder");
        return mav;
    }

    private ODM getMetadata() {
        ODM odm = null;
        String uri = userPreferences.getMetadataURL();
        InputStream studyMetadataXML = new ByteArrayInputStream(userPreferences.getRestTemplate().getForObject(URI.create(uri), byte[].class));
        try {
            InputStreamReader isr = new InputStreamReader(studyMetadataXML, "UTF-8");
            odm = (ODM) this.unMarshaller.unmarshal(new StreamSource(isr));
            // odm = (ODM) this.unMarshaller.unmarshal(new StreamSource(studyMetadataXML));
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
            if (studyMetadataXML != null) {
                // DO THIS PROPERLY !!!!!
                // studyMetadataXML.close();
            }
        }
        return odm;
    }

    private void disableSSLChecks() {
        NaiveTrustProvider.setAlwaysTrust(true);
        DisableSSLHostNameVerifier.disableSSLHostNameVerifier();
    }

}
