package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;


import static org.junit.jupiter.api.Assertions.*;

class PhoneValidatorTest {
    PhoneValidator phoneValidator;
    HashMap<String, Integer> countries = new HashMap<String, Integer>();

    @BeforeEach
    public void beforeEach() {
        countries.put("+370", 8); // (number prefix, number length)
        phoneValidator = new PhoneValidator(countries);
    }

    @Test
    void validatePhoneNumber_receivedNull_throwException() {
        assertThrows(InvalidPhoneNumberException.class, () -> phoneValidator.validate(null));
    }

    @Test
    void validatePhoneNumber_doesNotContainsOnlyNumbers_throwException() {
        String number = "+37061111#1f";
        assertThrows(InvalidPhoneNumberException.class, () -> phoneValidator.validate(number));
    }

    @Test
    void validatePhoneNumber_tooLong_throwException() {
        String number = "+370611111111";
        assertThrows(InvalidPhoneNumberException.class, () -> phoneValidator.validate(number));
    }

    @Test
    void validatePhoneNumber_tooShort_throwException() {
        String number = "+370611111";
        assertThrows(InvalidPhoneNumberException.class, () -> phoneValidator.validate(number));
    }

    @Test
    void validatePhoneNumber_unsupportedPrefix_throwException() {
        String number = "+37161111111";
        assertThrows(InvalidPhoneNumberException.class, () -> phoneValidator.validate(number));
    }

    @Test
    void validatePhoneNumber_changePrefix_returnsChangedNumber() throws InvalidPhoneNumberException {
        String number = "861111111";
        assertEquals("+37061111111", phoneValidator.validate(number));
    }

    @Test
    void validatePhoneNumber_meetsRequirements_returnsNumberBack() throws InvalidPhoneNumberException {
        String number = "+37061111111";
        assertEquals("+37061111111", phoneValidator.validate(number));
    }

}