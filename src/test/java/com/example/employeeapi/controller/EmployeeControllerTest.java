package com.example.employeeapi.controller;

import com.example.employeeapi.EmployeeAPIApplication;
import com.example.employeeapi.datasource.EmployeeRepository;
import com.example.employeeapi.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = EmployeeAPIApplication.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void shouldRedirectToLogin() throws Exception {
        mvc.perform(get("/employees"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "http://localhost/login"));

    }

    @Test
    @WithMockUser(username = "admin",
                  password = "123",
                  roles = "ADMIN")
    public void shouldShowEmployees() throws Exception {
        List<Employee> employeeList = new ArrayList<Employee>(){{
            add(new Employee(1, "test", 20, "男"));
        }};
        given(employeeRepository.getAllEmployees()).willReturn(employeeList);
        mvc.perform(get("/employees"))
                .andExpect(view().name("employees"))
                .andExpect(model().attributeExists("employeeList"))
                .andExpect(model().attribute("employeeList", employeeList));

    }
}