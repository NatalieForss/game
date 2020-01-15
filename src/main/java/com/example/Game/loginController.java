package com.example.Game;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;



@Controller
public class loginController {

    @Autowired
    private Metods metods;

    @Autowired
    userRepository repository;

    @GetMapping("/signup")
    public String signup(HttpSession session, Model model) {
        model.addAttribute("user", new UserInfo());
        UserInfo user = (UserInfo) session.getAttribute("user");
        if (user != null && user.getLoggedIn()) {
            return "startpage";
        } else {
            return "signup";
        }
    }

    @PostMapping("/signup")
    public String signupPost(@Valid UserInfo user, BindingResult result, HttpSession session, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "signup";
        }


        repository.addUser(user);

        return "login";
    }

    @GetMapping("/login")
    public String login(HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("user");
        if (user != null && user.getLoggedIn()) {
            return "mypage";
        } else {
            return "login";
        }
    }

    @PostMapping("/login")
    public String loginPost(HttpSession session, @RequestParam String username, @RequestParam String password) {
        UserInfo user = repository.ifUserIsLoggedIn(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/minasidor";
        } else {
            return "login";
        }
    }

    @PostMapping("/login2")
    public String login2Post(HttpSession session, @RequestParam String username, @RequestParam String password) {
        UserInfo user = repository.ifUserIsLoggedIn(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/addSpel";
        } else {
            return "login2";
        }
    }

    @GetMapping("/minasidor")
    public String mypages(HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("user");
        if (user != null && user.getLoggedIn()) {
            return "mypage";
        } else {
            return "login";
        }
    }

    @PostMapping("/minasidor")
    public String loginMypage(HttpSession session, @RequestParam String username, @RequestParam String password) {
        UserInfo user = repository.ifUserIsLoggedIn(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "mypage";
        } else {
            return "login";
        }
    }

    @GetMapping("/login2")
    public String login2(HttpSession session) {

            return "redirect:/addSpel";
        }



}
