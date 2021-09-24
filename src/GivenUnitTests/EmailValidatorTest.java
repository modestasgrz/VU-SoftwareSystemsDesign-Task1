package com.company;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {
    EmailValidator emailValidator;

    @BeforeEach
    public void beforeEach() {
        emailValidator = new EmailValidator();
    }

    @Test
    void validateEmail_receivedNull_throwException() {
        assertThrows(InvalidEmailException.class, () -> emailValidator.validate(null));
    }

    @Test
    void validateEmail_containsSpaces_throwException() {
        String email = "em ail@test.com";
        assertThrows(InvalidEmailException.class, () -> emailValidator.validate(email));
    }

    @Test
    void validateEmail_doesNotHaveAtSign_throwException() {
        String email = "emailtest.com";
        assertThrows(InvalidEmailException.class, () -> emailValidator.validate(email));
    }

    @Test
    void validateEmail_usernameContainsUnsupportedCharacter_throwException() {
        String email = "emai£l@test.com";
        assertThrows(InvalidEmailException.class, () -> emailValidator.validate(email));
    }

    @Test
    void validateEmail_usernameTooLong_throwException() {
        String email = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@test.com";
        assertThrows(InvalidEmailException.class, () -> emailValidator.validate(email));
    }

    @Test
    void validateEmail_domainNameTooLong_throwException() {
        String email = "email@bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb.com";
        assertThrows(InvalidEmailException.class, () -> emailValidator.validate(email));
    }

    @Test
    void validateEmail_emptyDomainName_throwException() {
        String email = "email@.com";
        assertThrows(InvalidEmailException.class, () -> emailValidator.validate(email));
    }

    @Test
    void validateEmail_domainNameHasUnsupportedCharacters_throwException() {
        String email = "email@tes&.com";
        assertThrows(InvalidEmailException.class, () -> emailValidator.validate(email));
    }

    @Test
    void validateEmail_TLDTooShort_throwException() {
        String email = "email@test.c";
        assertThrows(InvalidEmailException.class, () -> emailValidator.validate(email));
    }

    @Test
    void validateEmail_TLDStartsOrEndsWithHyphen_throwException() {
        String email = "email@test.-com-";
        assertThrows(InvalidEmailException.class, () -> emailValidator.validate(email));
    }

    @Test
    void validateEmail_TLDDoesNotStartWithALetter_throwException() {
        String email = "email@test.5om";
        assertThrows(InvalidEmailException.class, () -> emailValidator.validate(email));
    }

    @Test
    void validateEmail_unsupportedTLDCharacters_throwException() {
        String email = "email@test.c%om";
        assertThrows(InvalidEmailException.class, () -> emailValidator.validate(email));
    }

    @Test
    void validateEmail_meetsRequirements_doesNotThrowException() throws InvalidEmailException {
        String email = "email!#$%&'*+-/=?^_`@test.mail.com";
        emailValidator.validate(email);
    }
}