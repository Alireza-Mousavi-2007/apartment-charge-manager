package com.apamanager.apartmant_charge_manager.exceptions;

public class ExpenseNotFoundException extends RuntimeException{
    public ExpenseNotFoundException(String message) {
        super(message);
    }
}
