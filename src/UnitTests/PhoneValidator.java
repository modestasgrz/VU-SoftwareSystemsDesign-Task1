package UnitTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PhoneValidator {
    private String phoneNumber1 = "869312564";
    private String phoneNumber2 = "+461234567890";
    private String swedensPhoneNumberPrefix = "+46";
    private int swedensPhoneNumberLength = 13;

    @Test
    public void testPhoneNumber1() {
        assertTrue(areOnlyNumbersPresent(phoneNumber1));
    }

    @Test
    public void changePhoneNumber() {
        System.out.println(changeIfStartsWithEight(phoneNumber1));
    }

    @Test
    public void isPhoneNumberValidInSweden() {
        assertTrue(isValidInChosenCountry(phoneNumber2, swedensPhoneNumberLength, swedensPhoneNumberPrefix));
    }


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
}
