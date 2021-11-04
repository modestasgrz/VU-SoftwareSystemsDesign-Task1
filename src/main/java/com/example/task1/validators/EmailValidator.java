package com.example.task1.validators;

public abstract class EmailValidator {
    private static char[] specialSymbols = {'!', '~', '!', '$', '%', '^', '&', '*', '_', '=', '+', '}', '{', '?', '-', '.', '#'};

    public static boolean checkEmail(String email) {

        if (email == null) {
            return false;
        }

        int localPartEndPos = email.lastIndexOf('@');
        int domainPartEndPos = email.lastIndexOf('.');
        int topLevelDomainPartEndPos = email.length() - 1;

        if (localPartEndPos < 1 || domainPartEndPos == -1 || domainPartEndPos < localPartEndPos) {
            return false;
        }

        String localPart = email.substring(0, localPartEndPos);
        String domainPart = email.substring(localPartEndPos + 1, domainPartEndPos);
        String topLevelDomainPart = email.substring(domainPartEndPos + 1, topLevelDomainPartEndPos);


        return verifyEmailFormat(localPart) && verifyEmailFormat(domainPart) && verifyEmailFormat(topLevelDomainPart);
    }

    private static boolean verifyEmailFormat(String emailPart) {
        char[] emailPartChars = emailPart.toCharArray();
        int arrLength = emailPartChars.length;
        char symbol;

        for (int i = 0; i < arrLength; i++) {
            symbol = emailPartChars[i];

            if (!(symbol >= 'a' && symbol <= 'z') && !(symbol >= 'A' && symbol <= 'Z') && !(symbol >= '0' && symbol <= '9')) {
                if (i == 0 || i == arrLength - 1) {
                    return false;
                }

                if (checkEmailSpecialCharacters(symbol) == false) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkEmailSpecialCharacters(char symbol)
    {
        for (char specialSymbol: specialSymbols) {
            if(specialSymbol == symbol){
                return true;
            }
        }

        return false;
    }
}
