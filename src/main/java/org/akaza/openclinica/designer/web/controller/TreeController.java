package org.akaza.openclinica.designer.web.controller;

import org.cdisc.ns.odm.v130.ODMcomplexTypeDefinitionItemDef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 * Controller methods populate tree root and branches
 * 
 * <p>
 * This controller uses a {@code @ResponseBody}. The @ResponseBody annotation instructs Spring MVC to serialize the Object to
 * the client.
 * </p>
 * 
 * @author Krikor Krumlian
 * @since 3.0
 */

@Controller
@RequestMapping(value = "/tree")
public class TreeController {

    private MessageSource messageSource;

    protected final Logger logger = LoggerFactory.getLogger(getClass().getName());

    public static boolean isAjaxRequest(String requestedWith) {
        return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
    }

    public static boolean isAjaxUploadRequest(HttpServletRequest request) {
        return request.getParameter("ajaxUpload") != null;
    }

    @RequestMapping(value = "/itemDetails", method = RequestMethod.GET)
    public @ResponseBody
    UIItemDetail getItemDetails(@RequestParam String name, HttpSession session) throws IOException {

        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute("uiODMContainer");
        UIODMBuilder uiODMBuilder = new UIODMBuilder(uiODMContainer);
        UIItemDetail itemDetail = uiODMBuilder.buildItemDetail(name);

        return itemDetail;
    }

    @RequestMapping(value = "/eventsList", method = RequestMethod.GET)
    public @ResponseBody
    List<TreeModel> eventsList(@RequestParam String name, HttpSession session) throws IOException {

        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute("uiODMContainer");
        return getEventsList(uiODMContainer, false);
    }

    @RequestMapping(value = "/eventsListOid", method = RequestMethod.GET)
    public @ResponseBody
    List<TreeModel> eventsListOid(@RequestParam String name, HttpSession session) throws IOException {

        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute("uiODMContainer");
        return getEventsList(uiODMContainer, true);
    }

	private List<TreeModel> getEventsList(UIODMContainer uiODMContainer,
			Boolean useOid) {
		List<TreeModel> crfs = new ArrayList<TreeModel>();

		if (null != uiODMContainer.getEvents()) {
			for (UIEvent uiEvent : uiODMContainer.getEvents()) {

				TreeModel newCrf = new TreeModel(useOid ? uiEvent.getOid()
						: uiEvent.getName(), "closed", "event", "E_");
				newCrf.setName(uiEvent.getName());
				newCrf.setOid(uiEvent.getOid());
				newCrf.addAttr("oid", uiEvent.getOid());
				newCrf.getData().setIcon("event");
				crfs.add(newCrf);

			}
		}
		return crfs;

	}

    @RequestMapping(value = "/eventCrfsList", method = RequestMethod.GET)
    public @ResponseBody
    List<TreeModel> eventsCrfsList(@RequestParam String eventOid, HttpSession session) throws IOException {

        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute("uiODMContainer");
        return getEventCrfs(uiODMContainer, eventOid, false);
    }

    @RequestMapping(value = "/eventCrfsListOid", method = RequestMethod.GET)
    public @ResponseBody
    List<TreeModel> eventsCrfsListOid(@RequestParam String eventOid, HttpSession session) throws IOException {

        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute("uiODMContainer");
        return getEventCrfs(uiODMContainer, eventOid, true);
    }

    private List<TreeModel> getEventCrfs(UIODMContainer uiODMContainer, String eventOid, Boolean useOid) {
        List<TreeModel> crfs = new ArrayList<TreeModel>();
        UIEvent uiEvent = uiODMContainer.getEventsByOID(eventOid);

        for (UICrf uiCrf : uiEvent.getCrfs()) {

            TreeModel newCrf = new TreeModel(useOid ? uiCrf.getOid() : uiCrf.getName(), "closed", "crf", "E_");
            newCrf.setName(uiCrf.getName());
            newCrf.setOid(uiCrf.getOid());
            newCrf.addAttr("oid", uiCrf.getOid());
            newCrf.addAttr("eventOid", eventOid);
            // newCrf.getData().setIcon("crf");
            newCrf.getData().setIcon("crf");
            crfs.add(newCrf);

        }
        return crfs;

    }

    @RequestMapping(value = "/eventCrfGroupsAndItemsList", method = RequestMethod.GET)
    public @ResponseBody
    List<TreeModelInterface> eventCrfGroupsAndItemsList(@RequestParam String eventOid, @RequestParam String crfOid, HttpSession session) {

        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute("uiODMContainer");
        UIEvent uiEvent = uiODMContainer.getEventsByOID(eventOid);
        UICrf uiCrf = uiEvent.getCrfByOID(crfOid);

        List<TreeModelInterface> itemGroups = new ArrayList<TreeModelInterface>();
        for (UIGroup uiGroup : uiCrf.getGroups()) {
            TreeModel itemGroup = new TreeModel(uiGroup.getName(), "closed", "itemGroup", "E_");
            itemGroup.setName(uiGroup.getName());
            itemGroup.setOid(uiGroup.getOid());
            itemGroup.addAttr("oid", uiGroup.getOid());
            itemGroup.addAttr("crfOid", crfOid);
            itemGroup.addAttr("eventOid", eventOid);
            itemGroup.getData().setIcon("group");
            itemGroups.add(itemGroup);
        }
        if (uiCrf.getUngroupedGroup() != null) {
            List<TreeModelLeaf> ungroupedItems = new ArrayList<TreeModelLeaf>();
            for (ODMcomplexTypeDefinitionItemDef itemDef : uiCrf.getUngroupedGroup().getItems()) {
                TreeModelLeaf newItem = new TreeModelLeaf(itemDef.getName(), "closed", "item", "E_", itemDef.getOID());
                newItem.setName(itemDef.getName());
                newItem.addStyleToItemBasedonParameter(uiODMContainer.isItemDetailHidden(itemDef));
                newItem.addAttr("groupOid", uiCrf.getUngroupedGroup().getOid());
                newItem.addAttr("crfOid", crfOid);
                newItem.addAttr("eventOid", eventOid);
                newItem.addAttr("targetOid", eventOid + "." + crfOid + "." + uiCrf.getUngroupedGroup().getOid() + "." + itemDef.getOID());
                newItem.getData().setIcon("item");
                ungroupedItems.add(newItem);
            }
            itemGroups.addAll(ungroupedItems);
        }

        return itemGroups;

    }

    @RequestMapping(value = "/eventCrfGroupsAndItemsListOid", method = RequestMethod.GET)
    public @ResponseBody
    List<TreeModelInterface> eventCrfGroupsAndItemsListOid(@RequestParam String eventOid, @RequestParam String crfOid, HttpSession session) {

        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute("uiODMContainer");
        UIEvent uiEvent = uiODMContainer.getEventsByOID(eventOid);
        UICrf uiCrf = uiEvent.getCrfByOID(crfOid);

        List<TreeModelInterface> itemGroups = new ArrayList<TreeModelInterface>();
        for (UIGroup uiGroup : uiCrf.getGroups()) {
            TreeModel itemGroup = new TreeModel(uiGroup.getOid(), "closed", "itemGroup", "E_");
            itemGroup.setName(uiGroup.getName());
            itemGroup.setOid(uiGroup.getOid());
            itemGroup.addAttr("oid", uiGroup.getOid());
            itemGroup.addAttr("crfOid", crfOid);
            itemGroup.addAttr("eventOid", eventOid);
            itemGroup.getData().setIcon("group");
            itemGroups.add(itemGroup);
        }
        if (uiCrf.getUngroupedGroup() != null) {
            List<TreeModelLeaf> ungroupedItems = new ArrayList<TreeModelLeaf>();
            for (ODMcomplexTypeDefinitionItemDef itemDef : uiCrf.getUngroupedGroup().getItems()) {
                TreeModelLeaf newItem = new TreeModelLeaf(itemDef.getOID(), "closed", "item", "E_", itemDef.getOID());
                newItem.setName(itemDef.getName());
                newItem.addStyleToItemBasedonParameter(uiODMContainer.isItemDetailHidden(itemDef));
                newItem.addAttr("groupOid", uiCrf.getUngroupedGroup().getOid());
                newItem.addAttr("crfOid", crfOid);
                newItem.addAttr("eventOid", eventOid);
                newItem.addAttr("targetOid", eventOid + "." + crfOid + "." + uiCrf.getUngroupedGroup().getOid() + "." + itemDef.getOID());
                newItem.getData().setIcon("item");
                ungroupedItems.add(newItem);
            }
            itemGroups.addAll(ungroupedItems);
        }

        return itemGroups;

    }

    @RequestMapping(value = "/eventCrfGroupItemsList", method = RequestMethod.GET)
    public @ResponseBody
    List<TreeModelInterface> eventCrfGroupItemsList(@RequestParam String itemGroupOid, @RequestParam String crfOid, @RequestParam String eventOid,
            HttpSession session) {

        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute("uiODMContainer");
        UIEvent uiEvent = uiODMContainer.getEventsByOID(eventOid);
        UICrf uiCrf = uiEvent.getCrfByOID(crfOid);
        UIGroup uiGroup = uiCrf.getGroupByOID(itemGroupOid);

        List<TreeModelInterface> items = new ArrayList<TreeModelInterface>();
        for (ODMcomplexTypeDefinitionItemDef itemDef : uiGroup.getItems()) {
            TreeModelLeaf newItem = new TreeModelLeaf(itemDef.getName(), "closed", "item", "E_", itemDef.getOID());
            newItem.setName(itemDef.getName());
            newItem.addStyleToItemBasedonParameter(uiODMContainer.isItemDetailHidden(itemDef));
            newItem.addAttr("groupOid", itemGroupOid);
            newItem.addAttr("crfOid", crfOid);
            newItem.addAttr("eventOid", eventOid);
            newItem.addAttr("targetOid", eventOid + "." + crfOid + "." + uiGroup.getOid() + "." + itemDef.getOID());
            newItem.getData().setIcon("item");
            items.add(newItem);
        }
        return items;
    }

    @RequestMapping(value = "/eventCrfGroupItemsListOid", method = RequestMethod.GET)
    public @ResponseBody
    List<TreeModelInterface> eventCrfGroupItemsListOid(@RequestParam String itemGroupOid, @RequestParam String crfOid, @RequestParam String eventOid,
            HttpSession session) {

        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute("uiODMContainer");
        UIEvent uiEvent = uiODMContainer.getEventsByOID(eventOid);
        UICrf uiCrf = uiEvent.getCrfByOID(crfOid);
        UIGroup uiGroup = uiCrf.getGroupByOID(itemGroupOid);

        List<TreeModelInterface> items = new ArrayList<TreeModelInterface>();
        for (ODMcomplexTypeDefinitionItemDef itemDef : uiGroup.getItems()) {
            TreeModelLeaf newItem = new TreeModelLeaf(itemDef.getOID(), "closed", "item", "E_", itemDef.getOID());
            newItem.setName(itemDef.getName());
            newItem.addStyleToItemBasedonParameter(uiODMContainer.isItemDetailHidden(itemDef));
            newItem.addAttr("groupOid", itemGroupOid);
            newItem.addAttr("crfOid", crfOid);
            newItem.addAttr("eventOid", eventOid);
            newItem.addAttr("targetOid", eventOid + "." + crfOid + "." + uiGroup.getOid() + "." + itemDef.getOID());
            newItem.getData().setIcon("item");
            items.add(newItem);
        }
        return items;
    }

	@RequestMapping(value = "/crfList", method = RequestMethod.GET)
	public @ResponseBody
	List<TreeModel> getCrfs(@RequestParam String name, HttpSession session)
			throws IOException {

		UIODMContainer uiODMContainer = (UIODMContainer) session
				.getAttribute("uiODMContainer");
		List<TreeModel> crfs = new ArrayList<TreeModel>();

		if (null != uiODMContainer.getCrfs()) {
			for (UICrf uiCrf : uiODMContainer.getCrfs()) {

				TreeModel newCrf = new TreeModel(uiCrf.getName(), "closed",
						"crf", "CV_");
				newCrf.setName(uiCrf.getName());
				newCrf.setOid(uiCrf.getOid());
				newCrf.getData().setIcon("crf");
				newCrf.addAttr("oid", uiCrf.getOid());
				crfs.add(newCrf);

			}
		}
		return crfs;
	}

    @RequestMapping(value = "/crfListOid", method = RequestMethod.GET)
    public @ResponseBody
    List<TreeModel> getCrfsOid(@RequestParam String name, HttpSession session) throws IOException {

        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute("uiODMContainer");
        List<TreeModel> crfs = new ArrayList<TreeModel>();

        for (UICrf uiCrf : uiODMContainer.getCrfs()) {

            TreeModel newCrf = new TreeModel(uiCrf.getOid(), "closed", "crf", "CV_");
            newCrf.setName(uiCrf.getName());
            newCrf.setOid(uiCrf.getOid());
            newCrf.addAttr("oid", uiCrf.getOid());
            newCrf.getData().setIcon("crf");
            crfs.add(newCrf);

        }
        return crfs;
    }

	@RequestMapping(value = "/crfListNew", method = RequestMethod.GET)
	public @ResponseBody
	List<TreeModel> getCrfsNew(@RequestParam String name, HttpSession session)
			throws IOException {

		UIODMContainer uiODMContainer = (UIODMContainer) session
				.getAttribute("uiODMContainer");
		List<TreeModel> crfs = new ArrayList<TreeModel>();

		if (null != uiODMContainer.getCrfs()) {
			for (UICrf uiCrf : uiODMContainer.getCrfs()) {

				TreeModel newCrf = new TreeModel(uiCrf.getName(), "closed",
						"crf", "C_");
				newCrf.setName(uiCrf.getName());
				newCrf.setOid(uiCrf.getOid());
				newCrf.getData().setIcon("crf");
				newCrf.addAttr("oid", uiCrf.getOid());
				crfs.add(newCrf);

			}
		}
		return crfs;
	}

    @RequestMapping(value = "/crfListNewOid", method = RequestMethod.GET)
    public @ResponseBody
    List<TreeModel> getCrfsNewOid(@RequestParam String name, HttpSession session) throws IOException {

        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute("uiODMContainer");
        List<TreeModel> crfs = new ArrayList<TreeModel>();

        for (UICrf uiCrf : uiODMContainer.getCrfs()) {

            TreeModel newCrf = new TreeModel(uiCrf.getOid(), "closed", "crf", "C_");
            newCrf.setName(uiCrf.getName());
            newCrf.setOid(uiCrf.getOid());
            newCrf.getData().setIcon("crf");
            newCrf.addAttr("oid", uiCrf.getOid());
            crfs.add(newCrf);

        }
        return crfs;
    }

    @RequestMapping(value = "/crfBasedGroupList", method = RequestMethod.GET)
    public @ResponseBody
    List<TreeModelInterface> getGroupsByCrf(@RequestParam String crfOid, HttpSession session) {

        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute("uiODMContainer");
        UICrf uiCrf = uiODMContainer.getCrfByOID(crfOid);
        if (uiCrf == null) {
            // Report error here
        }
        return getUngroupedItemsAndGroups(uiCrf, crfOid, uiODMContainer);

    }

    @RequestMapping(value = "/crfBasedGroupListOid", method = RequestMethod.GET)
    public @ResponseBody
    List<TreeModelInterface> getGroupsByCrfOid(@RequestParam String crfOid, HttpSession session) {

        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute("uiODMContainer");
        UICrf uiCrf = uiODMContainer.getCrfByOID(crfOid);
        if (uiCrf == null) {
            // Report error here
        }
        List<TreeModelInterface> itemGroups = new ArrayList<TreeModelInterface>();
        for (UIGroup uiGroup : uiCrf.getGroups()) {
            TreeModel itemGroup = new TreeModel(uiGroup.getOid(), "closed", "itemGroup", "C_");
            itemGroup.setName(uiGroup.getName());
            itemGroup.setOid(uiGroup.getOid());
            itemGroup.addAttr("oid", uiGroup.getOid());
            itemGroup.getData().setIcon("group");
            itemGroup.addAttr("crfOid", crfOid);
            itemGroups.add(itemGroup);
        }
        if (uiCrf.getUngroupedGroup() != null) {
            List<TreeModelLeaf> ungroupedItems = new ArrayList<TreeModelLeaf>();
            for (ODMcomplexTypeDefinitionItemDef itemDef : uiCrf.getUngroupedGroup().getItems()) {
                TreeModelLeaf newItem = new TreeModelLeaf(itemDef.getOID(), "closed", "item", "C_", itemDef.getOID());
                newItem.setName(itemDef.getName());
                newItem.addStyleToItemBasedonParameter(uiODMContainer.isItemDetailHidden(itemDef));
                newItem.getData().setIcon("item");
                newItem.addAttr("groupOid", uiCrf.getUngroupedGroup().getOid());
                newItem.addAttr("crfOid", crfOid);
                ungroupedItems.add(newItem);
            }
            itemGroups.addAll(ungroupedItems);
        }
        return itemGroups;

    }

    private List<TreeModelInterface> getUngroupedItemsAndGroups(UICrf uiCrf, String crfOid, UIODMContainer uiODMContainer) {

        List<TreeModelInterface> itemGroups = new ArrayList<TreeModelInterface>();
        for (UIGroup uiGroup : uiCrf.getGroups()) {
            TreeModel itemGroup = new TreeModel(uiGroup.getName(), "closed", "itemGroup", "C_");
            itemGroup.setName(uiGroup.getName());
            itemGroup.setOid(uiGroup.getOid());
            itemGroup.getData().setIcon("group");
            itemGroup.addAttr("oid", uiGroup.getOid());
            itemGroup.addAttr("crfOid", crfOid);
            itemGroups.add(itemGroup);
        }
        if (uiCrf.getUngroupedGroup() != null) {
            List<TreeModelLeaf> ungroupedItems = new ArrayList<TreeModelLeaf>();
            for (ODMcomplexTypeDefinitionItemDef itemDef : uiCrf.getUngroupedGroup().getItems()) {
                TreeModelLeaf newItem = new TreeModelLeaf(itemDef.getName(), "closed", "item", "C_", itemDef.getOID());
                newItem.setName(itemDef.getName());
                newItem.getData().setIcon("item");
                newItem.addStyleToItemBasedonParameter(uiODMContainer.isItemDetailHidden(itemDef));
                if (newItem.isHidden() != null && newItem.isHidden()) {
                    newItem.getData().getAttr().put("class", "textcolor");
                }
                newItem.addAttr("groupOid", uiCrf.getUngroupedGroup().getOid());
                newItem.addAttr("crfOid", crfOid);
                ungroupedItems.add(newItem);
            }
            itemGroups.addAll(ungroupedItems);
        }
        return itemGroups;

    }

    @RequestMapping(value = "/crfBasedItemList", method = RequestMethod.GET)
    public @ResponseBody
    List<TreeModelInterface> getItemsByCrfOid(@RequestParam String itemGroupOid, @RequestParam String crfOid, HttpSession session) {

        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute("uiODMContainer");
        UICrf uiCrf = uiODMContainer.getCrfByOID(crfOid);
        UIGroup uiGroup = uiCrf.getGroupByOID(itemGroupOid);

        List<TreeModelInterface> items = new ArrayList<TreeModelInterface>();
        for (ODMcomplexTypeDefinitionItemDef itemDef : uiGroup.getItems()) {
            TreeModelLeaf newItem = new TreeModelLeaf(itemDef.getName(), "closed", "item", "C_", itemDef.getOID());
            newItem.setName(itemDef.getName());
            newItem.addStyleToItemBasedonParameter(uiODMContainer.isItemDetailHidden(itemDef));
            // newItem.getData().setIcon("item");
            newItem.getData().setIcon("item");
            items.add(newItem);
        }
        return items;
    }

    @RequestMapping(value = "/crfBasedItemListOid", method = RequestMethod.GET)
    public @ResponseBody
    List<TreeModelInterface> getItemsByCrf(@RequestParam String itemGroupOid, @RequestParam String crfOid, HttpSession session) {

        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute("uiODMContainer");
        UICrf uiCrf = uiODMContainer.getCrfByOID(crfOid);
        UIGroup uiGroup = uiCrf.getGroupByOID(itemGroupOid);

        List<TreeModelInterface> items = new ArrayList<TreeModelInterface>();
        for (ODMcomplexTypeDefinitionItemDef itemDef : uiGroup.getItems()) {
            TreeModelLeaf newItem = new TreeModelLeaf(itemDef.getOID(), "closed", "item", "C_", itemDef.getOID());
            newItem.setName(itemDef.getName());
            newItem.addStyleToItemBasedonParameter(uiODMContainer.isItemDetailHidden(itemDef));
            newItem.getData().setIcon("item");
            items.add(newItem);
        }
        return items;
    }

    @RequestMapping(value = "/crfVersionList", method = RequestMethod.GET)
    public @ResponseBody
    List<TreeModel> getCrfVersions(@RequestParam String crfOid, HttpSession session) {
        List<TreeModel> crfs = new ArrayList<TreeModel>();
        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute("uiODMContainer");
        UICrf uiCrf = uiODMContainer.getCrfByOID(crfOid);
        for (UICrf crfVersion : uiCrf.getCrfVersions()) {
            TreeModel newCrf = new TreeModel(crfVersion.getName(), "closed", "crfVersion", "CV_");
            newCrf.setName(crfVersion.getName());
            newCrf.setOid(crfVersion.getOid());
            newCrf.setName(crfVersion.getName());
            newCrf.addAttr("crfOid", crfOid);
            newCrf.addAttr("oid", crfVersion.getOid());
            newCrf.getData().setIcon("crfVersion");
            crfs.add(newCrf);
        }
        return crfs;
    }

    @RequestMapping(value = "/crfVersionListOid", method = RequestMethod.GET)
    public @ResponseBody
    List<TreeModel> getCrfVersionsOid(@RequestParam String crfOid, HttpSession session) {
        List<TreeModel> crfs = new ArrayList<TreeModel>();
        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute("uiODMContainer");
        UICrf uiCrf = uiODMContainer.getCrfByOID(crfOid);
        for (UICrf crfVersion : uiCrf.getCrfVersions()) {
            TreeModel newCrf = new TreeModel(crfVersion.getOid(), "closed", "crfVersion", "CV_");
            newCrf.setName(crfVersion.getName());
            newCrf.setOid(crfVersion.getOid());
            newCrf.setName(crfVersion.getName());
            newCrf.addAttr("crfOid", crfOid);
            newCrf.addAttr("oid", crfVersion.getOid());
            newCrf.getData().setIcon("crfVersion");
            crfs.add(newCrf);
        }
        return crfs;
    }

    @RequestMapping(value = "/groupList", method = RequestMethod.GET)
    public @ResponseBody
    List<TreeModelInterface> getGroups(@RequestParam String crfOid, @RequestParam String crfVersionOid, HttpSession session) {
        // itemGroupDAO = new ItemGroupDAO(dataSource);
        // itemDAO = new ItemDAO(dataSource);

        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute("uiODMContainer");
        UICrf uiCrf = uiODMContainer.getCrfByOID(crfOid);
        UICrf uiCrfVersion = uiCrf.getCrfByOID(crfVersionOid);

        List<TreeModelInterface> itemGroups = new ArrayList<TreeModelInterface>();
        for (UIGroup uiGroup : uiCrfVersion.getGroups()) {
            TreeModel itemGroup = new TreeModel(uiGroup.getName(), "closed", "itemGroup", "CV_");
            itemGroup.setName(uiGroup.getName());
            itemGroup.setOid(uiGroup.getOid());
            itemGroup.addAttr("oid", uiGroup.getOid());
            itemGroup.addAttr("crfOid", crfOid);
            itemGroup.addAttr("crfVersionOid", crfVersionOid);
            itemGroup.getData().setIcon("group");
            itemGroups.add(itemGroup);
        }
        List<TreeModelLeaf> ungroupedItems = new ArrayList<TreeModelLeaf>();
        if (uiCrfVersion.getUngroupedGroup() != null) {
            for (ODMcomplexTypeDefinitionItemDef itemDef : uiCrfVersion.getUngroupedGroup().getItems()) {
                TreeModelLeaf newItem = new TreeModelLeaf(itemDef.getName(), "closed", "item", "CV_", itemDef.getOID());
                newItem.setName(itemDef.getName());
                newItem.addStyleToItemBasedonParameter(uiODMContainer.isItemDetailHidden(itemDef));
                newItem.addAttr("crfOid", crfOid);
                newItem.addAttr("crfVersionOid", crfVersionOid);
                newItem.addAttr("targetOid", uiCrfVersion.getOid() + "." + uiCrfVersion.getUngroupedGroup().getOid() + "." + newItem.getOid());
                newItem.getData().setIcon("item");
                ungroupedItems.add(newItem);
            }
        }
        itemGroups.addAll(ungroupedItems);
        return itemGroups;
    }

    @RequestMapping(value = "/groupListOid", method = RequestMethod.GET)
    public @ResponseBody
    List<TreeModelInterface> getGroupsOid(@RequestParam String crfOid, @RequestParam String crfVersionOid, HttpSession session) {
        // itemGroupDAO = new ItemGroupDAO(dataSource);
        // itemDAO = new ItemDAO(dataSource);

        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute("uiODMContainer");
        UICrf uiCrf = uiODMContainer.getCrfByOID(crfOid);
        UICrf uiCrfVersion = uiCrf.getCrfByOID(crfVersionOid);

        List<TreeModelInterface> itemGroups = new ArrayList<TreeModelInterface>();
        for (UIGroup uiGroup : uiCrfVersion.getGroups()) {
            TreeModel itemGroup = new TreeModel(uiGroup.getOid(), "closed", "itemGroup", "CV_");
            itemGroup.setName(uiGroup.getName());
            itemGroup.setOid(uiGroup.getOid());
            itemGroup.addAttr("oid", uiGroup.getOid());
            itemGroup.addAttr("crfOid", crfOid);
            itemGroup.addAttr("crfVersionOid", crfVersionOid);
            itemGroup.getData().setIcon("group");
            itemGroups.add(itemGroup);
        }
        List<TreeModelLeaf> ungroupedItems = new ArrayList<TreeModelLeaf>();
        if (uiCrfVersion.getUngroupedGroup() != null) {
            for (ODMcomplexTypeDefinitionItemDef itemDef : uiCrfVersion.getUngroupedGroup().getItems()) {
                TreeModelLeaf newItem = new TreeModelLeaf(itemDef.getOID(), "closed", "item", "CV_", itemDef.getOID());
                newItem.setName(itemDef.getName());
                newItem.addAttr("crfOid", crfOid);
                newItem.addStyleToItemBasedonParameter(uiODMContainer.isItemDetailHidden(itemDef));
                newItem.addAttr("crfVersionOid", crfVersionOid);
                newItem.addAttr("targetOid", uiCrfVersion.getOid() + "." + uiCrfVersion.getUngroupedGroup().getOid() + "." + newItem.getOid());
                newItem.getData().setIcon("item");
                ungroupedItems.add(newItem);
            }
        }
        itemGroups.addAll(ungroupedItems);
        return itemGroups;
    }

    @RequestMapping(value = "/itemList", method = RequestMethod.GET)
    public @ResponseBody
    List<TreeModelInterface> getItems(@RequestParam String itemGroupOid, @RequestParam String crfVersionOid, @RequestParam String crfOid, HttpSession session) {

        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute("uiODMContainer");
        UICrf uiCrf = uiODMContainer.getCrfByOID(crfOid);
        UICrf uiCrfVersion = uiCrf.getCrfByOID(crfVersionOid);
        UIGroup uiGroup = uiCrfVersion.getGroupByOID(itemGroupOid);

        List<TreeModelInterface> items = new ArrayList<TreeModelInterface>();
        for (ODMcomplexTypeDefinitionItemDef itemDef : uiGroup.getItems()) {
            TreeModelLeaf newItem = new TreeModelLeaf(itemDef.getName(), "closed", "item", "CV_", itemDef.getOID());
            newItem.setName(itemDef.getName());
            newItem.addStyleToItemBasedonParameter(uiODMContainer.isItemDetailHidden(itemDef));
            newItem.addAttr("targetOid", uiCrfVersion.getOid() + "." + uiGroup.getOid() + "." + newItem.getOid());
            newItem.getData().setIcon("item");
            items.add(newItem);
        }
        return items;
    }

    @RequestMapping(value = "/itemListOid", method = RequestMethod.GET)
    public @ResponseBody
    List<TreeModelInterface> getItemsOid(@RequestParam String itemGroupOid, @RequestParam String crfVersionOid, @RequestParam String crfOid, HttpSession session) {

        UIODMContainer uiODMContainer = (UIODMContainer) session.getAttribute("uiODMContainer");
        UICrf uiCrf = uiODMContainer.getCrfByOID(crfOid);
        UICrf uiCrfVersion = uiCrf.getCrfByOID(crfVersionOid);
        UIGroup uiGroup = uiCrfVersion.getGroupByOID(itemGroupOid);

        List<TreeModelInterface> items = new ArrayList<TreeModelInterface>();
        for (ODMcomplexTypeDefinitionItemDef itemDef : uiGroup.getItems()) {
            TreeModelLeaf newItem = new TreeModelLeaf(itemDef.getOID(), "closed", "item", "CV_", itemDef.getOID());
            newItem.setName(itemDef.getName());
            newItem.addStyleToItemBasedonParameter(uiODMContainer.isItemDetailHidden(itemDef));
            newItem.addAttr("targetOid", uiCrfVersion.getOid() + "." + uiGroup.getOid() + "." + newItem.getOid());
            newItem.getData().setIcon("item");
            items.add(newItem);
        }
        return items;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

}
