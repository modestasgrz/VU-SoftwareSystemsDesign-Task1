package UnitTests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneValidator_Tests {
    @ParameterizedTest
    @ValueSource(strings = {
            "869935951",
            "861234567"
    })
    public void testPhoneNumber_Digits(String number) {

        PhoneValidator phoneValidator = new PhoneValidator();

        boolean result = phoneValidator.validatePhone_Digits(number);

        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "+37069935951",
            "",
            "Justiniskiu 7"
    })
    public void testFalsePhoneNumber_Digits(String number) {

        PhoneValidator phoneValidator = new PhoneValidator();

        boolean result = phoneValidator.validatePhone_Digits(number);

        assertFalse(result);
    }

    @Test
    public void testPhoneNumber_Change1() {
        String testCase = "869935951";
        String expectedResult = "+37069935951";

        PhoneValidator phoneValidator = new PhoneValidator();

        String result = phoneValidator.changePhoneNumber(testCase);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testPhoneNumber_Change2() {
        String testCase = "069935951";
        String expectedResult = "069935951";

        PhoneValidator phoneValidator = new PhoneValidator();

        String result = phoneValidator.changePhoneNumber(testCase);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testPhoneNumber_Change3() {
        String testCase = "069935951";
        String expectedResult = "+37069935951";

        PhoneValidator phoneValidator = new PhoneValidator();

        String result = phoneValidator.changePhoneNumber(testCase);

        assertNotEquals(expectedResult, result);
    }

    @Test
    public void testRulesAddition1() {

        PhoneValidator phoneValidator = new PhoneValidator();
        PhoneRules phoneRules = new PhoneRules();

        phoneRules.setCountry("Lithuania");
        phoneRules.setLengthOfPhoneNumber(12);
        phoneRules.setPrefix("+370");

        String country = "Lithuania";
        int lengthOfPhoneNumber = 12;
        String prefix = "+370";

        PhoneRules result = phoneValidator.addPhoneRules(country, lengthOfPhoneNumber, prefix);

        assertEquals(phoneRules, result);
    }

    @Test
    public void testRulesAddition2() {

        PhoneValidator phoneValidator = new PhoneValidator();
        PhoneRules phoneRules = new PhoneRules("Sweden", 13, "+46");

        String country = "Sweden";
        int lengthOfPhoneNumber = 13;
        String prefix = "+46";

        PhoneRules result = phoneValidator.addPhoneRules(country, lengthOfPhoneNumber, prefix);

        assertEquals(phoneRules, result);
    }

    @Test
    public void testRulesAddition2() {

        PhoneValidator phoneValidator = new PhoneValidator();
        PhoneRules phoneRules = new PhoneRules("Iceland", 11, "+354");

        String country = "Iceland";
        int lengthOfPhoneNumber = 10;
        String prefix = "+350";

        PhoneRules result = phoneValidator.addPhoneRules(country, lengthOfPhoneNumber, prefix);

        assertNotEquals(phoneRules, result);
    }
}
