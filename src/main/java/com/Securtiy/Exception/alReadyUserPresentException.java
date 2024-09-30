package com.Securtiy.Exception;

public class alReadyUserPresentException extends RuntimeException{
    public alReadyUserPresentException(String message){
        super(message);
    }
    public alReadyUserPresentException(String message,Throwable cause){
        super(message, cause);
    }
}
