package com.apamanager.apartmant_charge_manager.exceptions;

public class ChargeNotFoundException extends RuntimeException{
    public ChargeNotFoundException(String message) {
        super(message);
    }
}
