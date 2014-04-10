package org.akaza.openclinica.designer.web.objects;

import java.util.ArrayList;

class Response {
    Boolean valid;
    ArrayList<String> messages;

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public void addMessage(String message) {
        if (this.messages == null) {
            messages = new ArrayList<String>();
        }
        messages.add(message);
    }

}