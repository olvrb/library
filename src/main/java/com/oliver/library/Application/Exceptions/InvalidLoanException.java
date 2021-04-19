package com.oliver.library.Application.Exceptions;

import javax.naming.NamingException;

public class InvalidLoanException extends Exception {
    public InvalidLoanException(String message) {
        super(message);
    }
}
