package com.anderson.address_api.core.util;

public class ValidateZipCode {

    public static boolean validate(String zipCode) {
        if(!zipCode.matches("\\d{8}") || zipCode.matches("(\\d)\\1{7}")) return false;

        return true;
    }
}
