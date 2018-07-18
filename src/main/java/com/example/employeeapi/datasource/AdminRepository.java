package com.example.employeeapi.datasource;

import com.example.employeeapi.model.Admin;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminRepository {
    public Admin findOne(String username) {
        if (username.equals("admin")) {
            return new Admin().setUsername(username).setPassword(new BCryptPasswordEncoder().encode("123")).setFullname("NIAN");
        }
        return new Admin().setUsername(username).setPassword("111");
    }
}
