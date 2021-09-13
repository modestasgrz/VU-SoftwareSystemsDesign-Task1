package UnitTests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordChecker_Tests {

    @ParameterizedTest
    @ValueSource(strings = {
            "?Thisonepasses",
            "thisOnePasses.AsWell",
            "thisonenot",
            "Andthisone",
            "andthisone!",
            "~Also",
            "",
    })
    public void testSomePasswords(String password) {

        PasswordChecker passwordChecker = new PasswordChecker();

        boolean result = passwordChecker.validatePassword(password, passwordLength, specialSymbols);

        assertTrue(result);
    }

    private int passwordLength = 8;
    private char [] specialSymbols = {'?', '!', '~', '-', '.'};
}
