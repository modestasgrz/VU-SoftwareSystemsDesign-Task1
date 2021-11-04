package com.example.task1.validators;

public abstract class PhoneValidator {
    private static String[] countryCodes = {"LT", "UK", "LV"};
    private static String[] countryInternationalPrefixes = {"+370", "+44", "+371"};
    private static String[] countryMobilePrefixes = {"8", "7", "2"};
    private static int[] countryPhoneNumberLengths = {8, 10, 8};

    public static boolean checkPhoneFormat(String phoneNumber){

        if(phoneNumber == ""){
            return false;
        }

        int startPos = 0;
        char currentChar;
        if(phoneNumber.startsWith("+")){
            startPos++;
        }

        for (int i = startPos; i < phoneNumber.length(); i++){
            currentChar = phoneNumber.charAt(i);

            if((currentChar >= '0' && currentChar <= '9') == false){
                return false;
            }
        }

        return true;
    }

    public static boolean checkPhoneNumberByCountry(String countryCode, String phoneNumber){
        if(countryCode == "" || phoneNumber == ""){
            return false;
        }
        String countryPrefix;
        int countryIndex = -1;
        int countryNumberLength;



        for(int i = 0; i < countryCodes.length; i++)
        {
            if(countryCodes[i] == countryCode){
                countryIndex = i;
            }
        }

        if(countryIndex == -1){
            return false;
        }

        countryNumberLength = countryPhoneNumberLengths[countryIndex];

        if(phoneNumber.startsWith("+")){
            countryPrefix = countryInternationalPrefixes[countryIndex];
        }
        else{
            countryPrefix = countryMobilePrefixes[countryIndex];
        }

        if(phoneNumber.startsWith(countryPrefix)){
            if(phoneNumber.length() == countryPrefix.length() + countryNumberLength){
                return true;
            }
        }

        return false;
    }

    public static String changePrefix(String phoneNumber){
        String newNumber;
        String newPrefix;
        String oldPrefix;
        int countryNumberLength;
        int countryIndex = -1;

        for(int i = 0; i < countryMobilePrefixes.length; i++){
            if(phoneNumber.startsWith(countryMobilePrefixes[i])) {
                countryIndex = i;
                break;
            }
        }

        if(countryIndex == -1){
            return phoneNumber;
        }

        countryNumberLength = countryPhoneNumberLengths[countryIndex];
        newPrefix = countryInternationalPrefixes[countryIndex];
        oldPrefix = countryMobilePrefixes[countryIndex];

        if(phoneNumber.length() -  oldPrefix.length() != countryNumberLength){
            return phoneNumber;
        }

        newNumber = newPrefix + phoneNumber.substring(oldPrefix.length());

        return newNumber;
    }

}
