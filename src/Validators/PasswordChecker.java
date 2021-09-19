package Validators;

import java.util.List;

public class PasswordChecker {
    public static void setSpecialCharacters(List<Character> specialCharacters) {
        specialSymbolsList = specialCharacters;
    }
    public static boolean validate(String password) {
        //Password null?
        if (password == null) {
            System.out.println("Password is null");
            return false;
        }
        //Password length
        if (password.length() < minLength) {
            System.out.println("Password is too short");
            return false;
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
            return false;
        }
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
            return false;
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
            return false;
        }

        //Special Characters
        boolean isSpecialCharacterPresent = false;
        for (int i = 0; i < password.length(); i++) {
            for (char symbol : specialSymbolsList) {
                if (password.charAt(i) == symbol) {
                    isSpecialCharacterPresent = true;
                    break;
                }
            }
        }
        if(!isSpecialCharacterPresent) {
            System.out.println("Missing special character");
            return false;
        }

        return true;
    }
    public static void addSpecialCharacter(char character) {
        specialSymbolsList.add(character);
    }
    public static void remove(char character) {
        for (int i = 0; i < specialSymbolsList.size(); i++) {
            if (specialSymbolsList.get(i) == character) {
                specialSymbolsList.remove(i);
                break;
            }
        }
    }

    private static List<Character> specialSymbolsList;
    private static int minLength = 8;
}
