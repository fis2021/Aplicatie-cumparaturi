package org.example;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException(){
        super(String.format("Username or Password is incorrect!"));
    }
}
