package com.example.task1.validators;

public abstract class PasswordValidator {

    private static char[] specialSymbols= {'!', '~', '!', '$', '%', '^', '&', '*', '_', '=', '+', '}', '{', '?', '-', '.', '#'};

    public static boolean checkPassword(String password, int length){
        return verifyPasswordLength(password, length) && verifyPasswordUpperCase(password) && verifyPasswordSpecialSymbol(password);
    }

    private static boolean verifyPasswordLength(String password, int length){
        return password.length() >= length;
    }

    private static boolean verifyPasswordUpperCase(String password)
    {
        for (char symbol: password.toCharArray()) {
            if(symbol >= 'A' && symbol <= 'Z')
            {
                return true;
            }
        }
        return false;
    }

    private static boolean verifyPasswordSpecialSymbol(String password){
        for (char symbol: password.toCharArray()) {
            for (char specialSymbol: specialSymbols){
                if(symbol == specialSymbol){
                    return true;
                }
            }
        }
        return false;
    }
}
