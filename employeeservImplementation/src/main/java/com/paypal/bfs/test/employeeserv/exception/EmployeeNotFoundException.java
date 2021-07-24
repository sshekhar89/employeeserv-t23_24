package com.paypal.bfs.test.employeeserv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(String id)
    {
        super(String.format("Employee with Id %s not found.", id));
    }
}
