package com.example.task1.controller;

import com.example.task1.model.User;
import com.example.task1.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
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
        User c = new User("Clint", "Eastwood", "+37061234567", "idontknowwhattodo@inbox.com", "37, Baker's st. London", "ABCDefgh?1");
        when(repository.save(Mockito.any(User.class))).thenReturn(c);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user")
                .accept(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"forename\":\"Clint\"," +
                        "\"surname\":\"Eastwood\"," +
                        "\"phoneNumber\":\"+37061234567\"," +
                        "\"email\":\"idontknowwhattodo@inbox.com\"," +
                        "\"address\":\"37, Baker's st. London\"," +
                        "\"password\":\"ABCDefgh?1\"" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON);

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

        String expected = "[{userId:0, forename:\"A\", surname:\"A\", phoneNumber:\"123\", email:\"A\", password:\"A\" }," +
                "{userId:0, forename:\"B\", surname:\"B\", phoneNumber:\"456\", email:\"B\", password:\"B\" }," +
                "{userId:0, forename:\"C\", surname:\"C\", phoneNumber:\"789\", email:\"C\", password:\"C\" }]";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testGetById() throws Exception {
        User c = new User("A", "A", "123", "A", "A", "A");
        when(repository.findById(Mockito.anyInt())).thenReturn(java.util.Optional.of(c));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/1");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String expected = "{userId:0, forename:\"A\", surname:\"A\", phoneNumber:\"123\", email:\"A\", password:\"A\" }";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}
