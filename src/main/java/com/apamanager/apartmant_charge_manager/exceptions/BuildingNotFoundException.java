package com.apamanager.apartmant_charge_manager.exceptions;

public class BuildingNotFoundException extends RuntimeException{
    public BuildingNotFoundException(String message) {
        super(message);
    }
}
