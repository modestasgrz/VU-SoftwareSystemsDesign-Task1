package com.example.task1.validators;

public abstract class FormValidator {

    public static boolean checkPassword(String password, int length){

        if(password == null)
        {
            return false;
        }

        return PasswordValidator.checkPassword(password, length);
    }

    public static boolean checkEmail(String email){

        if(email == null){
            return false;
        }

        return EmailValidator.checkEmail(email);
    }

    public static boolean checkPhoneNumber(String phoneNumber){

        if(phoneNumber == null){
            return false;
        }

        return PhoneValidator.checkPhoneFormat(phoneNumber);
    }

    public static boolean checkPhoneNumberByCountry(String country, String phoneNumber){

        if(phoneNumber == null || country == null){
            return false;
        }

        return PhoneValidator.checkPhoneNumberByCountry(country, phoneNumber);
    }

    public static String changePrefix(String phoneNumber){
        if(phoneNumber.startsWith("+")){
            return phoneNumber;
        }

        return PhoneValidator.changePrefix(phoneNumber);
    }








}
