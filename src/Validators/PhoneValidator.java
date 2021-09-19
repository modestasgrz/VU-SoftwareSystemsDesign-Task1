package Validators;

import GivenUnitTests.CountryRule;

import java.util.*;

public class PhoneValidator {
    public static void addCountryRule(CountryRule countryRule) {
        countryRules.add(countryRule);
    }
    public static boolean validate(String phoneNumber) {
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
    public static String formatNumberToString(String country, String phoneNumber) {
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
    public static void addNewCountryRule(CountryRule countryRule) {

    }
    private static List<CountryRule> countryRules = new ArrayList<>();
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