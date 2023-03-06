package com.bakery.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {

    private String response;

    public Message(String response) {
        this.response = response;
    }
}
