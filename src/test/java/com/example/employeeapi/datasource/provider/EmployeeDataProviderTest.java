package com.example.employeeapi.datasource.provider;

import com.example.employeeapi.EmployeeAPIApplication;
import com.example.employeeapi.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
//1.
//@ContextConfiguration(classes = EmployeeAPIApplication.class)
//2.
//@SpringApplicationConfiguration()
@SpringBootTest(classes = EmployeeAPIApplication.class)
public class EmployeeDataProviderTest {

    @Autowired
    private EmployeeDataProvider employeeDataProvider;

    @Test
    public void shouldGetEmployeeById() {
        Employee employee = employeeDataProvider.getEmployee(0);
        assertEquals("小明", employee.getName());
        assertEquals("男", employee.getGender());
    }
}