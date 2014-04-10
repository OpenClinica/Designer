package org.akaza.openclinica.designer.web.controller;

import org.akaza.openclinica.designer.web.controller.FlashMap.Message;

import java.util.ArrayList;

public class DefaultResponseHandler implements ResponseHandler {

    ArrayList<Message> messages = new ArrayList<Message>();

    public DefaultResponseHandler() {
        // Default constructor
    }

    public DefaultResponseHandler(Message... messages) {
        for (Message message : messages) {
            this.messages.add(message);
        }
    }

    @Override
    public ArrayList<Message> handle() {
        return messages;
    }
}
