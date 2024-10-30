package com.tugas5.apotek.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.tugas5.apotek.models.Login;
import com.tugas5.apotek.service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping("registrasi")
    public String registrasi(Model model) {
        Login login = new Login();
        model.addAttribute("regis", login);
        return "registrasi";
    }

    @PostMapping("save-regis")
    public String saveRegis(@ModelAttribute("regis") Login login) {
        loginService.save(login);
        return "redirect:/login";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping("home")
    public String login(@RequestParam("username") String username,
            @RequestParam("password") String password, Model model) {
        Login login = loginService.findByUsernameAndPassword(username, password);
        if (login != null) {
            model.addAttribute("login", login);
            return "home";
        } else {
            return "redirect:/login";
        }
    }
}