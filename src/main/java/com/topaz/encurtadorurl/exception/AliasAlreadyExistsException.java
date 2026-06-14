package com.topaz.encurtadorurl.exception;

public class AliasAlreadyExistsException extends RuntimeException {
    public AliasAlreadyExistsException() {
        super("Alias já existe no banco de dados");
    }
}
