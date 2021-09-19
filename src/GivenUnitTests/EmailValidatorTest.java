package GivenUnitTests;

import Validators.EmailValidator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class EmailValidatorTest {
@Test
    public void missingAtSymbol()
    {
        assertFalse(EmailValidator.validate("petras.petraitismif.stud.vu.lt"));
    }

    @Test
    public void invalidCharacter()
    {
        assertFalse(EmailValidator.validate("jonas jon'<?php/>@gmail.com"));
    }

    @Test
    public void specialCharacterFirst()
    {
        assertFalse(EmailValidator.validate("$klausk@lrt.lt"));
    }

    @Test
    public void specialCharacterLast()
    {
        assertFalse(EmailValidator.validate("klausk@lrt.lt!"));
    }

    @Test
    public void specialCharactersConsecutive()
    {
        assertFalse(EmailValidator.validate("klau&&sk@lr%*t.lt"));
    }

    @Test
    public void mixedCaseInRecipiantName()
    {
assertTrue(EmailValidator.validate("PAgaLbA@topocentras.org"));
    }

    @Test
    public void hyphenInDomainName()
    {
        assertTrue(EmailValidator.validate("keturi@penki-skaicius.lt"));
    }

    @Test
    public void unicodeCharactersInRecipientName()
    {
        assertFalse(EmailValidator.validate("ramūnas.bėgikas@sportas.eu"));
    }

}