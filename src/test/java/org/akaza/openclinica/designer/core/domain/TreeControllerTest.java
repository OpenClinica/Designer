package org.akaza.openclinica.designer.core.domain;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.akaza.openclinica.designer.web.controller.TreeController;
import org.akaza.openclinica.designer.web.controller.TreeModel;
import org.akaza.openclinica.designer.web.controller.TreeModelLeaf;
import org.akaza.openclinica.designer.web.controller.TreeModelInterface;
import org.akaza.openclinica.designer.web.controller.UICrf;
import org.akaza.openclinica.designer.web.controller.UIEvent;
import org.akaza.openclinica.designer.web.controller.UIODMContainer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TreeControllerTest {
    TreeController treeController;

    private List<UIEvent> getEventsList() {
        List<UIEvent> uiEvents = new ArrayList<UIEvent>();
        UIEvent event = new UIEvent("SE_BOSTON", "First Visit");
        uiEvents.add(event);
        return uiEvents;
    }

    private UIEvent getEvent() {
        UIEvent event = new UIEvent("SE_BOSTON", "First Visit");
        UICrf crf = new UICrf("C_CRF", "AE");
        event.addCrfIfNotExist(crf);
        return event;
    }

    @Before
    public void init() {
        treeController = new TreeController();

    }

    @Test
    public void eventsList() throws IOException {
        MockHttpSession mockHttpSession = new MockHttpSession();
        UIODMContainer mockedUIODMContainer = mock(UIODMContainer.class);
        when(mockedUIODMContainer.getEvents()).thenReturn(getEventsList());
        mockHttpSession.setAttribute("uiODMContainer", mockedUIODMContainer);
        List<TreeModel> treeModel = treeController.eventsList("test", mockHttpSession);
        assertEquals(treeModel.size(), 1);
        assertEquals(treeModel.get(0).getOid(), "SE_BOSTON");
        assertEquals(treeModel.get(0).getData().getIcon(), "event");
        assertEquals(treeModel.get(0).getData().getTitle(), "First Visit");
    }

    @Test
    public void eventsListOid() throws IOException {
        MockHttpSession mockHttpSession = new MockHttpSession();
        UIODMContainer mockedUIODMContainer = mock(UIODMContainer.class);
        when(mockedUIODMContainer.getEvents()).thenReturn(getEventsList());
        mockHttpSession.setAttribute("uiODMContainer", mockedUIODMContainer);
        List<TreeModel> treeModel = treeController.eventsListOid("test", mockHttpSession);
        assertEquals(treeModel.size(), 1);
        assertEquals(treeModel.get(0).getOid(), "SE_BOSTON");
        assertEquals(treeModel.get(0).getData().getIcon(), "event");
        assertEquals(treeModel.get(0).getData().getTitle(), "SE_BOSTON");
    }

    @Test
    public void eventsCrfsList() throws IOException {
        final String METHOD_PARAM = "test";
        MockHttpSession mockHttpSession = new MockHttpSession();
        UIODMContainer mockedUIODMContainer = mock(UIODMContainer.class);
        when(mockedUIODMContainer.getEventsByOID(METHOD_PARAM)).thenReturn(getEvent());
        mockHttpSession.setAttribute("uiODMContainer", mockedUIODMContainer);
        List<TreeModelInterface> treeModel = treeController.eventsCrfsList(METHOD_PARAM, mockHttpSession);
        assertEquals(treeModel.size(), 3);
        assertEquals(((TreeModelLeaf) treeModel.get(0)).getOid(), "STARTDATE");
        assertEquals(((TreeModelLeaf) treeModel.get(0)).getData().getIcon(), "item");
        assertEquals(((TreeModelLeaf) treeModel.get(0)).getData().getTitle(), "Start Date");
        assertEquals(((TreeModelLeaf) treeModel.get(1)).getOid(), "STATUS");
        assertEquals(((TreeModelLeaf) treeModel.get(1)).getData().getIcon(), "item");
        assertEquals(((TreeModelLeaf) treeModel.get(1)).getData().getTitle(), "Status");
        assertEquals(((TreeModel) treeModel.get(2)).getOid(), "C_CRF");
        assertEquals(((TreeModel) treeModel.get(2)).getData().getIcon(), "crf");
        assertEquals(((TreeModel) treeModel.get(2)).getData().getTitle(), "AE");
    }

    @Test
    public void eventsCrfsListOid() throws IOException {
        final String METHOD_PARAM = "test";
        MockHttpSession mockHttpSession = new MockHttpSession();
        UIODMContainer mockedUIODMContainer = mock(UIODMContainer.class);
        when(mockedUIODMContainer.getEventsByOID(METHOD_PARAM)).thenReturn(getEvent());
        mockHttpSession.setAttribute("uiODMContainer", mockedUIODMContainer);
        List<TreeModelInterface> treeModel = treeController.eventsCrfsListOid(METHOD_PARAM, mockHttpSession);
        assertEquals(treeModel.size(), 3);
        assertEquals(((TreeModelLeaf) treeModel.get(0)).getOid(), "STARTDATE");
        assertEquals(((TreeModelLeaf) treeModel.get(0)).getData().getIcon(), "item");
        assertEquals(((TreeModelLeaf) treeModel.get(0)).getData().getTitle(), "STARTDATE");
        assertEquals(((TreeModelLeaf) treeModel.get(1)).getOid(), "STATUS");
        assertEquals(((TreeModelLeaf) treeModel.get(1)).getData().getIcon(), "item");
        assertEquals(((TreeModelLeaf) treeModel.get(1)).getData().getTitle(), "STATUS");
        assertEquals(((TreeModel) treeModel.get(2)).getOid(), "C_CRF");
        assertEquals(((TreeModel) treeModel.get(2)).getData().getIcon(), "crf");
        assertEquals(((TreeModel) treeModel.get(2)).getData().getTitle(), "C_CRF");
    }
}