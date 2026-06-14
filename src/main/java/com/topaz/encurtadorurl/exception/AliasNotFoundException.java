package com.topaz.encurtadorurl.exception;

public class AliasNotFoundException extends RuntimeException {
    public AliasNotFoundException() {
      super("Alias não encontrado");
    }
}
