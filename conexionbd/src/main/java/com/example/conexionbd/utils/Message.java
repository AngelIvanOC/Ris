package com.example.conexionbd.utils;

public class Message {
    private Object data;
    private String message;
    private TypesResponse responseType;

    public Message(Object data, String message, TypesResponse responseType) {
        this.data = data;
        this.message = message;
        this.responseType = responseType;
    }

    public Message(String message, TypesResponse responseType) {
        this.message = message;
        this.responseType = responseType;
        this.data = null;
    }

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public TypesResponse getResponseType() {
        return responseType;
    }
}