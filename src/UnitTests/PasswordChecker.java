package UnitTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordChecker {

    private String password = "Bebras?";
    private char[] specialSymbols = {'!', '?', '~', '_', '-'};
    private int minLength = 6;

    @Test
    public void testPasswordLength() {
        assertTrue(isPasswordLengthValid(password, minLength));
    }

    @Test
    public void testPasswordUppercaseLetters() {
        assertTrue(areUppercaseSymbolsPresentInPassword(password));
    }

    @Test
    public void testPasswordSpecialCharacters() {
        assertTrue(areSpecialSymbolsPresentInPassword(password, specialSymbols));
    }

    public boolean isPasswordLengthValid(String password, int minLength){
        return password.length() >= minLength;
    }
    public boolean areUppercaseSymbolsPresentInPassword(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) >= 65 && password.charAt(i) <= 90) {
                return true;
            }
        }
        return false;
    }
    public boolean areSpecialSymbolsPresentInPassword(String password, char[] specialSymbolsList) {
        for (int i = 0; i < password.length(); i++) {
            for (char symbol : specialSymbolsList) {
                if (password.charAt(i) == symbol) return true;
            }
        }
        return false;
    }
}
