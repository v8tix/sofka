package com.exercise.cuenta.exception;

import java.util.UUID;
public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(UUID cuentaId) {
        super("La cuenta con ID " + cuentaId + " no fue encontrada.");
    }
}
