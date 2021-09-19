package GivenUnitTests;

import Validators.PhoneValidator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneValidatorTest
{
    @BeforeAll
    static void setUp() {
        CountryRule lithuania = new CountryRule("LT", 12, "+370");
        PhoneValidator.addCountryRule(lithuania);
    }

    @Test
    public void numberContainsNonDigits() {
       assertFalse(PhoneValidator.validate("(+370)6 455 555 9"));
    }

    @Test
    public void changeToCountryCode() {
        assertEquals("+37061234567", PhoneValidator.formatNumberToString("LT", "861234567"));
    }

    @Test
    public void NumberOk() {
assertTrue(PhoneValidator.validate("+37054444444"));
        assertEquals("+37054444444", PhoneValidator.formatNumberToString("LT", "+37054444444"));
    }

    @Test
    public void unknownRuleOrIncorrectNumber()
    {
        assertFalse(PhoneValidator.validate("+12399999999"));
    }

    @Test
    public void newRule() {
        CountryRule egzotineSala = new CountryRule("egzotine_sala", 10, "+25");
        PhoneValidator.addNewCountryRule(egzotineSala);

        assertEquals("+254455666", PhoneValidator.formatNumberToString("egzotine_sala", "+254455666"));
    }



}