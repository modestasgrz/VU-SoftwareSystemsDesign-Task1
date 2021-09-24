package com.company;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordCheckerTest {
    PasswordChecker passwordChecker;

    @BeforeEach
    public void beforeEach() {
        passwordChecker = new PasswordChecker(6, new char[]{'@', '$', '%'}); // (min length, special character list)
    }

    @Test
    void validatePassword_receivedNull_throwException() {
        String password = null;
        assertThrows(InvalidPasswordException.class, () -> passwordChecker.validate(password));
    }

    @Test
    void validatePassword_tooShort_throwException() {
        String password = "Asdfg";
        assertThrows(InvalidPasswordException.class, () -> passwordChecker.validate(password));
    }

    @Test
    void validatePassword_doesNotHaveUppercaseLetters_throwException() {
        String password = "as@fff";
        assertThrows(InvalidPasswordException.class, () -> passwordChecker.validate(password));
    }

    @Test
    void validatePassword_doesNotHaveSpecialCharacter_throwException() {
        String password = "Asdffd";
        assertThrows(InvalidPasswordException.class, () -> passwordChecker.validate(password));
    }

    @Test
    void validatePassword_hasSpaces_throwException() {
        String password = "As@d df";
        assertThrows(InvalidPasswordException.class, () -> passwordChecker.validate(password));
    }

    @Test
    void validatePassword_meetsRequirements_doesNotThrowException() throws InvalidPasswordException {
        String password = "Asdfg@";
        passwordChecker.validate(password);
    }
}