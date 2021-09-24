package Validators;

import java.util.*;

public class PhoneValidator {
    public PhoneValidator () {}
    public PhoneValidator (HashMap<String, Integer> countries) {
        this.countries = countries;
    }
    public void addCountryRule(CountryRule countryRule) {
        countryRules.add(countryRule);
    }
    public boolean validate(String phoneNumber) {
        for (int i = 0; i < phoneNumber.length(); i++) {
            if ((phoneNumber.charAt(i) > 57 || phoneNumber.charAt(i) < 48) && phoneNumber.charAt(i) != '+') {
                return false;
            }
        }
        if (phoneNumber.startsWith("+")) {
            boolean isRuleFound = false;
            for (int i = 0; i < countryRules.size(); i++) {
                if (phoneNumber.startsWith(countryRules.get(i).getPrefix())) {
                    isRuleFound = true;
                }
            }
            return isRuleFound;
        }
        return true;
    }
    public String formatNumberToString(String country, String phoneNumber) {
        String prefix = "";
        boolean isPrefixAssigned = false;
        for (int i = 0; i < countryRules.size(); i++) {
            if(countryRules.get(i).getCountry().equals(country)) {
                prefix = countryRules.get(i).getPrefix();
                isPrefixAssigned = true;
                break;
            }
        }
        if (isPrefixAssigned && phoneNumber.startsWith("8")) {
            return prefix +  phoneNumber.substring(1);
        }
        else return phoneNumber;
    }
    public void addNewCountryRule(CountryRule countryRule) {

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