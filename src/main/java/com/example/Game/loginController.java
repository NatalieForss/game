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
import java.util.ArrayList;
import java.util.List;

@Controller
public class loginController {

    @Autowired
    private Metods metods;

    @Autowired
    userRepository repository;


    @GetMapping("/signup")
    public String usersignup(HttpSession session, Model model) {
        model.addAttribute("user", new UserInfo());
//        if (session.getAttribute("userName")!= null) {
        //   UserInfo user = (UserInfo) session.getAttribute("userkey");
//        if (user != null && user.getLoggedIn()) {
//            return "startpage";
//        } else {

        return "signup";
    }


    @PostMapping("/signup")
    public String signup(@Valid UserInfo user, BindingResult result, HttpSession session, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "signup";
        }
        metods.addUser(user);

        return "login";
    }

    @GetMapping("/login")
    public String login(HttpSession session) {
        if (session.getAttribute("userName") != null) {
            // UserInfo user = (UserInfo) session.getAttribute("userkey");

            // if (user != null && user.getLoggedIn()) {
            return "mypage";
        } else {
            return "login";
        }

    }

    @PostMapping("/login")
    public String loginP(HttpSession session, @RequestParam String username, @RequestParam String password) {
        UserInfo user = repository.ifUserIsLoggedIn(username, password);

        if (user != null && user.getLoggedIn()) {
            session.setAttribute("userkey", user);
            return "mypage";
        } else {
            return "login";
        }
    }

    @GetMapping("/minasidor")
    public String mypages() {
        return "mypage";
    }
}


////    @GetMapping("/")
////    public String redirect(){
////        return "redirect:/login";
////    }
//
//
//    @GetMapping("/login")
//    String getLogin() {
//        return "login";
//    }
//
//    @PostMapping("/login")
//    String postLogin(HttpSession session, Model model, @RequestParam String username, @RequestParam String password) {
//
//        UserInfo userInDatabase = metods.getUser(username);
//
//        if(userInDatabase != null){
//            if(userInDatabase.getPassword().equals(password)){
//                session.setAttribute("username", username);
//                System.out.println("Du är nu inloggad");
//                return "redirect:/startpage";
//            }
//            System.out.println("Fel lösenord");
//            model.addAttribute("wrongPassword", true);
//            return "login";
//        }
//        System.out.println("Det finns ingen sådan användare");
//        model.addAttribute("noSuchMember", true);
//        return "login";
//    }
//    @GetMapping("/signup")
//    String addUser(HttpSession session, Model model){
//        model.addAttribute("user", new UserInfo());
//
//        return "signup";
//    }
//
//    @PostMapping("/signup")
//    String addUser(HttpSession session, Model model, @Valid UserInfo user, BindingResult result) {
//        if (result.hasErrors()) {
//            return "signup";
//
//            if (metods.getUser(user.getUserName()) != null) {
//                model.addAttribute("userExists", true);
//                return "login";
//            }
//
//            model.addAttribute("user", user);
//            metods.addUser(user);
//
//            List<UserInfo> users = (List<UserInfo>) session.getAttribute("users");
//            if (users == null) {
//                users = new ArrayList<>();
//                session.setAttribute("users", users);
//            }
//            users.add(user);
//            model.addAttribute("noErrors", true);
//
//            return "login";
//        }




