package com.bmsit.bmsitapi.exceptions;

public class RecordNotFoundException extends Exception{
    public RecordNotFoundException(int id){
        super("Record with id "+id+" does not found");
    }
}
