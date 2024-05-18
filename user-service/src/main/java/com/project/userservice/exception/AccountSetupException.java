package com.project.userservice.exception;

public class AccountSetupException extends RuntimeException{
    public AccountSetupException(){
        super("Account Setup not completed");
    }
}
