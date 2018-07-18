package com.example.employeeapi.controller;

import com.example.employeeapi.EmployeeAPIApplication;
import com.example.employeeapi.datasource.EmployeeRepository;
import com.example.employeeapi.model.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EmployeeAPIApplication.class)
@WebAppConfiguration
public class EmployeeControllerTestWithContext {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Before
    public void setUpMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                //.apply(springSecurity()
                .build();
    }

    @Test
    public void shouldReturnEmployeeInPage() throws Exception {
        Employee employee = new Employee(1, "test", 20, "男");
        given(employeeRepository.getEmployee(1)).willReturn(employee);
        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.age").value(20))
                .andExpect(jsonPath("$.gender").value("男"));
    }

    @Test
    public void shouldShowEmployees() throws Exception {
        List<Employee> employeeList = new ArrayList<Employee>(){{
            add(new Employee(1, "test", 20, "男"));
        }};
        given(employeeRepository.getAllEmployees()).willReturn(employeeList);
        mockMvc.perform(get("/employees"))
                .andExpect(view().name("employees"))
                .andExpect(model().attributeExists("employeeList"))
                .andExpect(model().attribute("employeeList", employeeList));

    }
}
