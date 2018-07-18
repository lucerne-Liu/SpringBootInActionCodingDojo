package com.example.employeeapi.controller;

import com.example.employeeapi.datasource.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private AdminRepository adminRepository;

    @GetMapping
    String show() {
        return "/login";
    }

//    @PostMapping
//    String login(HttpServletRequest request) {
//        if (adminRepository.findOne(request.getParameter("username")).getPassword().equals(request.getParameter("password"))) {
//            return "/employees";
//        }
//        return "redirect:/login?error=true";
//    }
}
