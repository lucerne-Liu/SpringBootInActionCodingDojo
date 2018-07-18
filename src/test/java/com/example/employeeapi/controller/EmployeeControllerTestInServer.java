package com.example.employeeapi.controller;

import com.example.employeeapi.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTestInServer {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldGetEmployeeById() {
        Employee employee = restTemplate.getForObject("/employees/0", Employee.class);
        assertEquals("小明", employee.getName());
        assertEquals("男", employee.getGender());
    }

//    @Test(expected = HttpClientErrorException.class)
//    public void pageNotFound() {
//        try {
//            restTemplate.getForEntity("http://localhost:{port}/notExist", String.class, port);
//            fail("Should fail in HTTP 404");
//        } catch (HttpClientErrorException e) {
//            assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
//            throw e;
//        }
//    }
}
