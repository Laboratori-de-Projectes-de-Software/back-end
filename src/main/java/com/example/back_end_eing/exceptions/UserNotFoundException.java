package com.example.back_end_eing.exceptions;


public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String message) {
    super("El usuario  " + message + " ya existe");
  }
}
