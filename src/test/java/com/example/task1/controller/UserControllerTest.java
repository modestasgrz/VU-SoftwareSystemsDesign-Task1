package com.example.task1.controller;

import com.example.task1.model.User;
import com.example.task1.repository.UserRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(value = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserRepository repository;

    @Test
    public void givenWAC_whenServletContext_thenItProvidesController() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean("userController"));
    }

    @Test
    public void testAdd() throws Exception {
        User c = new User("A", "A", "123", "A", "A", "A");
        when(repository.save(Mockito.any(User.class))).thenReturn(c);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .param("forename", "A")
                .param("surname", "A")
                .param("phoneNumber", "123")
                .param("email", "A")
                .param("address", "A")
                .param("password", "A")
                .flashAttr("user", new User("A", "A", "123", "A", "A", "A"));

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        verify(repository).save(Mockito.any(User.class));
    }

    @Test
    public void testUpdate() throws Exception {
        User c = new User("A", "A", "123", "A", "A", "A");
        when(repository.save(Mockito.any(User.class))).thenReturn(c);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("forename", "A")
                .param("surname", "A")
                .param("phoneNumber", "123")
                .param("email", "A")
                .param("address", "A")
                .param("password", "A")
                .flashAttr("user", new User("A", "A", "123", "A", "A", "A"));

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        verify(repository).save(Mockito.any(User.class));
    }

    @Test
    public void testGet() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User("A", "A", "123", "A", "A", "A"));
        users.add(new User("B", "B", "456", "B", "B", "B"));
        users.add(new User("C", "C", "789", "C", "C", "C"));

        when(repository.findAll()).thenReturn(users);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void testGetById() throws Exception {
        User c = new User("A", "A", "123", "A", "A", "A");
        when(repository.findById(Mockito.anyInt())).thenReturn(java.util.Optional.of(c));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/1");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void testDelete() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/delete-citizen/1");

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        verify(repository).deleteById(Mockito.anyInt());
    }
}
