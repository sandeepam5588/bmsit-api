package com.bmsit.bmsitapi.exceptions;

public class StudentRecordNotFoundException extends RuntimeException{
    public StudentRecordNotFoundException(int id) {
        super("Student Record with id="+id+" not found");
    }
}
