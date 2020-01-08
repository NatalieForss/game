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
    private UserInfo userInfo;


    @GetMapping("/")
    public String redirect(){
        return "redirect:/login";
    }

    @GetMapping("/signup")
    public String signup(HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("userkey");

        if (user != null && user.isLoggedIn()) {
            return "redirect:/startpage";
        } else {

            return "signup";
        }
    }

    @PostMapping("/signup")
    public String signupPost(@Valid UserInfo user, BindingResult result, HttpSession session, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("user", user);
            return "signup";
        }

        repository.saveUser(user);

        return "login";
    }

    @GetMapping("/login")
    String getLogin() {
        return "login";
    }

    @PostMapping("/login")
    String postLogin(HttpSession session, Model model, @RequestParam String username, @RequestParam String password) {

        UserInfo userInDatabase = userInfo.getUser(username);

        if(userInDatabase != null){
            if(userInDatabase.getPassword().equals(password)){
                session.setAttribute("username", username);
                System.out.println("Du är nu inloggad");
                return "redirect:/";
            }
            System.out.println("Fel lösenord");
            model.addAttribute("wrongPassword", true);
            return "login";
        }
        System.out.println("Det finns ingen sådan användare");
        model.addAttribute("noSuchMember", true);
        return "login";
    }

//    @GetMapping("/login")
//    public String login(HttpSession session) {
//        UserInfo user = (UserInfo) session.getAttribute("userkey");
//
//        if (user != null && user.isLoggedIn()) {
//            return "redirect:/startpage";
//        } else {
//
//            return "login";
//        }
//    }
//
//    @PostMapping("/login")
//    public String loginPost(HttpSession session, @RequestParam String username, @RequestParam String password) {
////       UserInfo userInfo = repository.getUser();
//        UserInfo user = repository.checkLogin(username, password);
//        if (user != null && user.isLoggedIn()) {
//            session.setAttribute("userkey", user);
//            return "redirect:/startpage";
//        } else {
//            return "login";
//        }
//    }
}

