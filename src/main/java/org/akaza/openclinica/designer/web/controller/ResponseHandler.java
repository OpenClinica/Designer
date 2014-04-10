package org.akaza.openclinica.designer.web.controller;

import org.akaza.openclinica.designer.web.controller.FlashMap.Message;

import java.util.ArrayList;

public interface ResponseHandler {

    ArrayList<Message> handle();

}
