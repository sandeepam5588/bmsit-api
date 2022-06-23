package com.bmsit.bmsitapi.exceptions.handler;

import com.bmsit.bmsitapi.exceptions.FacultyNotFoundException;
import com.bmsit.bmsitapi.exceptions.JsonConversionError;
import com.bmsit.bmsitapi.exceptions.RecordNotFoundException;
import com.bmsit.bmsitapi.model.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private String  NO_RECORDS_FOUND = "NO_RECORDS_FOUND";
    private String BAD_REQUEST = "BAD_REQUEST";

    @ExceptionHandler(FacultyNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleFacultyNotFoundException(FacultyNotFoundException e, WebRequest request){
        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(), e.getMessage(), "please provide the valid id"
        );

        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException e, WebRequest request) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", LocalDateTime.now());
//        body.put("message", "No record found");
//
//        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        ErrorResponse response = new ErrorResponse(LocalDateTime.now(), e.getMessage(), "please provide the valid id");
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(JsonConversionError.class)

    /**
     *
     * The handleMethodArgumentNotValid handles the MethodArgumentNotValidException
     * which is thrown when validation on an argument annotated with @Valid fails.
     */
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
