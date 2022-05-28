package com.bmsit.bmsitapi.exceptions;

public class FacultyNotFoundException extends Exception {
    public FacultyNotFoundException(int id) {
        super("Faculty details for the id " +id+ " not found");
    }
}
