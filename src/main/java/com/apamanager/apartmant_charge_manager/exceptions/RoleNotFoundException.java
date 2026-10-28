package com.apamanager.apartmant_charge_manager.exceptions;

import java.util.function.Supplier;

public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException(String message) {
        super(message);
    }
}
