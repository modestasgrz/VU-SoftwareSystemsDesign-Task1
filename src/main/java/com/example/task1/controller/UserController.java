package com.example.task1.controller;

import com.example.task1.additional.GeneralValidator;
import com.example.task1.model.User;
import com.example.task1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/user")
    public List<User> getUsers() {
        return (List<User>) repository.findAll();
    }
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id) {
        return repository.findById(id).get();
    }
    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        if (GeneralValidator.getInstance().validateUser(user)) {
            return repository.save(user);
        }
        else throw new IllegalStateException();
    }

    @PutMapping("/user/{id}")
    public User updateUser(@RequestBody User user, @PathVariable int id) {
        if (GeneralValidator.getInstance().validateUser(user)) {
            return repository.findById(id).map(oldUser ->
            {
                oldUser.setForename(user.getForename());
                oldUser.setSurname(user.getSurname());
                oldUser.setAddress(user.getAddress());
                oldUser.setEmail(user.getEmail());
                oldUser.setPhoneNumber(user.getPhoneNumber());
                oldUser.setPassword(user.getPassword());
                return repository.save(oldUser);
            }).get();
        }
        throw new IllegalStateException();
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id) {
        repository.deleteById(id);
    }
}
