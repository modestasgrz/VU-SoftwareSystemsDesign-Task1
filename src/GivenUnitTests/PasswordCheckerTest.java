package GivenUnitTests;

import Validators.PasswordChecker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordCheckerTest {
    @BeforeAll
    static void setUp() {
        List<Character> specialCharacters = new ArrayList<>();
        specialCharacters.add('/');
specialCharacters.add(' ');
specialCharacters.add('?');
specialCharacters.add('_');
specialCharacters.add('%');
        PasswordChecker.setSpecialCharacters(specialCharacters);
    }

    @Test
public void passwordNull()
    {
        assertFalse(PasswordChecker.validate(null));
    }

    @Test
    public void passwordEmpty()
    {
        assertFalse(PasswordChecker.validate(""));
    }

    @Test
    public void passwordTooWeak()
    {
        assertFalse(PasswordChecker.validate("LAbas 1"));
    }

    @Test
    public void missingUppercaseLetter()
    {
        assertFalse(PasswordChecker.validate("vie?nas_123"));
    }

@Test
public void missingLowercaseLetter()
{
    assertFalse(PasswordChecker.validate("VIE_     NAS123"));
}

@Test
    public void missingSymbol()
    {
        assertFalse(PasswordChecker.validate("VIENasdu3"));
    }

@Test
public void missingNumber()
{
    assertFalse(PasswordChecker.validate("VIEn__%as"));
}

@Test
    public void passwordOk()
    {
        assertTrue(PasswordChecker.validate("LAbAS R1"));
    }

    @Test
    public void passwordLongerOk()
    {
        assertTrue(PasswordChecker.validate("VVVVV   ?___ieieeejfVl555EAa./4422513''"));
    }

    @Test
    public void canContainUnicodeCharacters()
    {
        assertTrue(PasswordChecker.validate("MANO_??/labai1556ĮšššmanūūūūųžžsisSLAptAžŽŽžžžžodis"));
    }

    @Test
    public void addSpecialCharacter()
    {
        PasswordChecker.addSpecialCharacter('\t');
        assertTrue(PasswordChecker.validate("EEEEjfjfjf\t123456"));
    }

    @Test
    public void removeSpecialCharacter()
    {
        PasswordChecker.remove('?');
        assertFalse(PasswordChecker.validate("FFFFffff?456"));
    }

}