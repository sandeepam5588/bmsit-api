package com.bmsit.bmsitapi.exceptions;

public class CourseRecordNotFoundException extends RuntimeException {
    public CourseRecordNotFoundException(String name) {
        super("No such course exist"+name);
    }
}
