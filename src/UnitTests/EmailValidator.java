package UnitTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class EmailValidator {

    //{"vardenis.pavardenis@mif.stud.vu.lt", "labasvakaras@gmail.com", "\"/><{||]raNdOM\"@vps.random.com", "cia.leidziamas.email@email.eu", "ciakomentaras(komentaras)@mail.net", "sitas@nebeleidziamas@wowow.com", "kazkasneleidziamo@cia..nope", "nesamone"};
    @ParameterizedTest
    @ValueSource (strings = {"vardenis.pavardenis@mif.stud.vu.lt", "labasvakaras@gmail.com", "\"/><{||]raNdOM\"@vps.random.com", "cia.leidziamas.email@email.eu", "ciakomentaras(komentaras)@mail.net", "sitas@nebeleidziamas@wowow.com", "kazkasneleidziamo@cia..nope", "nesamone", "wow@wow"})
    public void testSomeEmails(String email) {
        assertTrue(validateEmail(email));
    }



    public static boolean validateEmail(String email) {
        //etaValidation
        int etaOccurence = 0;
        String specialSymbols = "\"(),:;<>@[\\]";

        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                etaOccurence = i;
            }
        }
        if (etaOccurence == 0) {
            System.out.println("@ symbol is missing");
            return false;
        }

        //Local part of email address validation
        String localPart = email.substring(0, etaOccurence);
        String domain = email.substring(etaOccurence + 1);
                        //Symbols from 32 to 126 included
        if (localPart.startsWith("\"") && localPart.endsWith("\"")) {
            for (char symbol : email.toCharArray()) {
                if (symbol <= 32 || symbol >= 126) {
                    System.out.println("Prohibited character was found, even between commas \"\"");
                    return false;
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
                    return false;
                }
                for (char specialChar : specialSymbols.toCharArray()) {
                    if (symbol == specialChar) {
                        System.out.println("Prohibited character found");
                        return false;
                    }
                }
            }
        }

        //Validating domain part
        String tld = "";
        if(hasTLD(domain)) {
            tld = domain.substring(domain.indexOf('.') + 1);
            domain = domain.substring(0, domain.indexOf('.'));
        }
        for (char symbol : domain.toCharArray()) {
            if ((symbol < 45) || (symbol > 45 && symbol < 48) || (symbol > 57 && symbol < 65 ) || (symbol > 90 && symbol < 97) || (symbol > 122)) {
                //Check if domain has some invalid characters
                System.out.println("Prohibited symbol was found in domain part of email address");
                return false;
            }
        }
        if (domain.startsWith("-") || domain.endsWith("-")) return false;

        //System.out.println(localPart + '\n' + domain + '\n' + tld + '\n');

        //Validating tld part - same as domain, but this time '.' symbol included
        for (char symbol : tld.toCharArray()) {
            if ((symbol < 45) || (symbol > 46 && symbol < 48) || (symbol > 57 && symbol < 65 ) || (symbol > 90 && symbol < 97) || (symbol > 122)) {
                //Check if domain has some invalid characters
                System.out.println("Prohibited symbol was found in TLD part of email address");
                return false;
            }
        }
        //Check for doubled '.' symbols
        if (tld.startsWith("-") || tld.startsWith(".") || tld.endsWith("-") || tld.endsWith(".")) return false;
        for (int i = 0; i < tld.length() - 1; i++) {
            if (tld.charAt(i) == '.' && tld.charAt(i + 1) == '.') return false;
        }


        //Otherwise
        return true;
    }

    private static boolean hasCommetsInEmailAddress(String email) {
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

    private static boolean hasTLD(String domain) {
        for (char symbol : domain.toCharArray()) {
            if (symbol == '.') return true;
        }
        return false;
    }
}
