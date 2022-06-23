package com.bmsit.bmsitapi.exceptions;

public class JsonConversionError extends RuntimeException {
    public JsonConversionError(String msg){
        super("unable to patch the record "+msg);
    }
}
