package com.example.task1.repository;

import com.example.task1.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void testFindAll() {
        User c = new User("", "", "", "", "", "");
        repository.save(c);
        Iterable<User> users = repository.findAll();

        assertNotNull(users);

        List<User> result = new ArrayList<>();
        users.forEach(result::add);

        assertEquals(1, result.size());
    }

    @Test
    public void testSave() {
        User c = new User("A", "A", "123", "A", "A", "A");
        repository.save(c);
        List<User> users = (List<User>) repository.findAll();
        assertEquals(1, users.size());
        assertEquals("123", users.get(0).getPhoneNumber());
    }

    @Test
    public void testDelete() {
        User c = new User("A", "A", "123", "A", "A", "A");
        repository.save(c);
        List<User> users = (List<User>) repository.findAll();
        assertEquals(1, users.size());

        repository.delete(c);
        users = (List<User>) repository.findAll();
        assertEquals(0, users.size());
    }
}
