package com.example.task1.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    void testUserCreation() {
        User tempUser = new User("Alfred", "Hitchcock", "+37061234567", "alfred.hitchcock@email.com", "12, Random st. Universe", "abcdEFGH?1");
        assertAll("Testing constructor of User class",
                () -> assertEquals("Alfred", tempUser.getForename()),
                () -> assertEquals("Hitchcock", tempUser.getSurname()),
                () -> assertEquals("+37061234567", tempUser.getPhoneNumber()),
                () -> assertEquals("alfred.hitchcock@email.com", tempUser.getEmail()),
                () -> assertEquals("12, Random st. Universe", tempUser.getAddress()),
                () -> assertEquals("abcdEFGH?1", tempUser.getPassword())
                );
    }

    @Test
    void testUserToString() {
        User tempUser = new User("Alfred", "Hitchcock", "+37061234567", "alfred.hitchcock@email.com", "12, Random st. Universe", "abcdEFGH?1");
        assertEquals("User{" +
                "userId=" + tempUser.getUserId() +
                ", forename='" + tempUser.getForename() + '\'' +
                ", surname='" + tempUser.getSurname() + '\'' +
                ", phoneNumber='" + tempUser.getPhoneNumber() + '\'' +
                ", email='" + tempUser.getEmail() + '\'' +
                ", address='" + tempUser.getAddress() + '\'' +
                ", password='" + tempUser.getPassword() + '\'' +
                '}',
                tempUser.toString());
    }
}