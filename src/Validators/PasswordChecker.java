package Validators;

import Exceptions.InvalidPasswordException;

import java.util.List;

public class PasswordChecker {
    public PasswordChecker() {}
    public PasswordChecker(int minLength, char [] specialSymbolsList) {
        this.specialSymbolsList = specialSymbolsList;
        this.minLength = minLength;
    }

    public boolean validate(String password) throws InvalidPasswordException {

        //Password null?
        if (password == null) {
            System.out.println("Password is null");
            throw new InvalidPasswordException();
            //return false;
        }

        //Password length
        if (password.length() < minLength) {
            System.out.println("Password is too short");
            throw new InvalidPasswordException();
            //return false;
        }

        //UpperCase letters
        boolean isUpperCaseLettersPresent = false;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) >= 65 && password.charAt(i) <= 90) {
                isUpperCaseLettersPresent = true;
                break;
            }
        }
        if (!isUpperCaseLettersPresent) {
            System.out.println("Missing upper case letters");
            throw new InvalidPasswordException();
            //return false;
        }

        //Special Characters
        boolean isSpecialCharacterPresent = false;
        for (int i = 0; i < password.length(); i++) {
            for (char symbol : this.specialSymbolsList) {
                if (password.charAt(i) == symbol) {
                    isSpecialCharacterPresent = true;
                    break;
                }
            }
        }
        if(!isSpecialCharacterPresent) {
            System.out.println("Missing special character");
            throw new InvalidPasswordException();
            //return false;
        }

        //Spaces in password
        for (char symbol : password.toCharArray())  {
            if (symbol == ' ' || symbol == '\t') {
                throw new InvalidPasswordException();
                //return false;
            }
        }
        return true;
    }

    private char [] specialSymbolsList;
    private int minLength = 8;
}

/*
        //LowerCase letters
        boolean isLowerCaseCaseLettersPresent = false;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) >= 97 && password.charAt(i) <= 122) {
                isLowerCaseCaseLettersPresent = true;
                break;
            }
        }
        if (!isLowerCaseCaseLettersPresent) {
            System.out.println("Missing lower case letters");
            throw new InvalidPasswordException;
            //return false;
        }

        //Numbers
        boolean isNumberPresent = false;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) >= 48 && password.charAt(i) <= 57) {
                isNumberPresent = true;
                break;
            }
        }
        if (!isNumberPresent) {
            System.out.println("Missing number letters");
            throw new InvalidPasswordException;
            //return false;
        }
         */