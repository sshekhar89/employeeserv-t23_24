package com.paypal.bfs.test.employeeserv.exception;

import java.util.Map;

public class InvalidAdditionalPropertyException extends RuntimeException {
    public InvalidAdditionalPropertyException(Map<String, Object> message) {
        super(String.format("Invalid property: %s found.", message.toString()));
    }
}
