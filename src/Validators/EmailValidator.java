package Validators;

import Exceptions.InvalidEmailException;

public class EmailValidator {

    public boolean validate(String email) throws InvalidEmailException {
        //null
        if (email == null) {
            throw new InvalidEmailException();
            //return false;
        }
        //etaValidation
        int etaOccurence = 0;
        String specialSymbols = "\"(),:;<>@[\\]";
        String specialSymbolsEmailCannotStartWith = "!#$%&'*+-/=?^_`{|}~";

        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                etaOccurence = i;
            }
        }
        if (etaOccurence == 0) {
            System.out.println("@ symbol is missing");
            throw new InvalidEmailException();
            //return false;
        }

        //Local part of email address validation
        String localPart = email.substring(0, etaOccurence);
        String domain = email.substring(etaOccurence + 1);
        System.out.println(localPart + "\n" + domain);

        //Local part too long
        if (localPart.length() > 64) {
            throw new InvalidEmailException();
            //return false;
        }
        //Symbols from 32 to 126 included
        if (localPart.startsWith("\"") && localPart.endsWith("\"")) {
            for (char symbol : email.toCharArray()) {
                if (symbol <= 32 || symbol >= 126) {
                    System.out.println("Prohibited character was found, even between commas \"\"");
                    throw new InvalidEmailException();
                    //return false;
                }
            }
        }
        else {
            if (hasCommetsInEmailAddress(localPart)) {
                int indexOfOpeningBracket = localPart.indexOf('(');
                int indexOfClosingBracket = localPart.indexOf(')');
                if (indexOfOpeningBracket == 0) {
                    //If address' local part starts with a comment
                    localPart = localPart.substring(indexOfClosingBracket + 1);
                } else {
                    //If address' local part ends with a comment
                    localPart = localPart.substring(0, indexOfOpeningBracket);
                }
                System.out.println("Comment in local part of email address was found");
            }
            for (char symbol : localPart.toCharArray()) {
                if (symbol <= 32 || symbol >= 126) {
                    System.out.println("Prohibited character found (out of ascii bounds)");
                    throw new InvalidEmailException();
                    //return false;
                }
                for (char specialChar : specialSymbols.toCharArray()) {
                    if (symbol == specialChar) {
                        System.out.println("Prohibited character found");
                        throw new InvalidEmailException();
                        //return false;
                    }
                }
            }
            for (char specialChar : specialSymbolsEmailCannotStartWith.toCharArray()) {
                if (localPart.startsWith(specialChar + "")) {
                    System.out.println("Prohibited character at the start of the email");
                    throw new InvalidEmailException();
                    //return false;
                }
            }
        }

        //Validating domain part
        //Is length of domain not too long?
        if (domain.length() > 254) {
            System.out.println("Domain of the email is too long");
            throw new InvalidEmailException();
            //return false;
        }
        String tld = "";
        if(hasTLD(domain)) {
            tld = domain.substring(domain.indexOf('.') + 1);
            domain = domain.substring(0, domain.indexOf('.'));
        }

        //What if domain is missing?
        if (domain.length() < 1) {
            throw  new InvalidEmailException();
            //return false;
        }
        for (char symbol : domain.toCharArray()) {
            if ((symbol < 45) || (symbol > 45 && symbol < 48) || (symbol > 57 && symbol < 65 ) || (symbol > 90 && symbol < 97) || (symbol > 122)) {
                //Check if domain has some invalid characters
                System.out.println("Prohibited symbol was found in domain part of email address");
                throw new InvalidEmailException();
                //return false;
            }
        }
        if (domain.startsWith("-") || domain.endsWith("-")) return false;

        //System.out.println(localPart + '\n' + domain + '\n' + tld + '\n');

        //Validating tld part - same as domain, but this time '.' symbol included
        for (char symbol : tld.toCharArray()) {
            if ((symbol < 45) || (symbol > 46 && symbol < 48) || (symbol > 57 && symbol < 65 ) || (symbol > 90 && symbol < 97) || (symbol > 122)) {
                //Check if domain has some invalid characters
                System.out.println("Prohibited symbol was found in TLD part of email address");
                throw new InvalidEmailException();
                //return false;
            }
        }
        //Check for doubled '.' symbols
        if (tld.startsWith("-") || tld.startsWith(".") || tld.endsWith("-") || tld.endsWith(".")) {
            throw new InvalidEmailException();
            //return false;
        }
        for (int i = 0; i < tld.length() - 1; i++) {
            if (tld.charAt(i) == '.' && tld.charAt(i + 1) == '.') {
                throw new InvalidEmailException();
                //return false;
            }
        }
        //What if tld too short?
        if (tld.length() < 2) {
            throw new InvalidEmailException();
            //return false;
        }
        //TLD does start with a letter?
        if (tld.charAt(0) < 'a' || tld.charAt(0) > 'z') {
            throw new InvalidEmailException();
            //return false;
        }


        //Otherwise
        return true;
    }

    private boolean hasCommetsInEmailAddress(String email) {
        if (email.startsWith("(")) {
            for (char symbol : email.toCharArray()) {
                if (symbol == ')') return true;
            }
        }
        if (email.endsWith(")")) {
            for (char symbol : email.toCharArray()) {
                if (symbol == '(') return true;
            }
        }
        return false;
    }

    private boolean hasTLD(String domain) {
        for (char symbol : domain.toCharArray()) {
            if (symbol == '.') return true;
        }
        return false;
    }
}
