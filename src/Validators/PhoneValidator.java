package Validators;

import Exceptions.InvalidPhoneNumberException;
import java.util.*;

public class PhoneValidator {
    public PhoneValidator () {}
    public PhoneValidator (HashMap<String, Integer> countries) {
        this.countries = countries;
    }

    public String validate(String phoneNumber) throws InvalidPhoneNumberException {
        //Null?
        if (phoneNumber == null) {
            throw new InvalidPhoneNumberException();
        }
        //Symbol validation
        for (int i = 0; i < phoneNumber.length(); i++) {
            if ((phoneNumber.charAt(i) > 57 || phoneNumber.charAt(i) < 48) && phoneNumber.charAt(i) != '+') {
                throw new InvalidPhoneNumberException();
            }
        }
        if (phoneNumber.startsWith("+")) {
            boolean isRuleFound = false;
            String thisPrefix = "";
            for (String prefix : countries.keySet()) {
                if (phoneNumber.startsWith(prefix)) {
                    isRuleFound = true;
                    thisPrefix = prefix;
                }
            }
            if (!isRuleFound) {
                throw new InvalidPhoneNumberException();
            }
            //Wrong length
            if (phoneNumber.length() != countries.get(thisPrefix)) {
                System.out.println(phoneNumber.length());
                throw new InvalidPhoneNumberException();
            }
        } //Change to +370
        else {
            return "+370" +  phoneNumber.substring(1);
        }
        return phoneNumber;
    }

    private HashMap<String, Integer> countries = new HashMap<>();
}

/*
public boolean areOnlyNumbersPresent(String phoneNumber) {
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (phoneNumber.charAt(i) > 57 || phoneNumber.charAt(i) < 48) {
                return false;
            }
        }
        return true;
    }

    public String changeIfStartsWithEight(String phoneNumber) {
        if (phoneNumber.charAt(0) == '8') {
            return "+370" +  phoneNumber.substring(1);
        }
        else return phoneNumber;
    }

    public boolean isValidInChosenCountry(String phoneNumber, int length, String prefix) {
        if (phoneNumber.length() == length && phoneNumber.startsWith(prefix)) {
            return true;
        }
        else return false;
    }
 */