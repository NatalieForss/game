package com.example.Game;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SpelController {

    @GetMapping("/spel")
    public String spel(){
        return "startpage";
    }


    @GetMapping("/familjespel")
    public String familjespel(){
        // model.addAttribute("familjespel")
        return "familjespel";
    }

    @PostMapping("/addSpel")
    String addSpel (HttpSession session, Model model, @Valid Spel spel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "addSpel";
        }
        model.addAttribute("spel", spel);

        List<Spel> spels = (List<Spel>)session.getAttribute("spel");
        if (spels == null) {
            spels = new ArrayList<>();
            session.setAttribute("spels", spels);
        }

        spels.add(spel);

        return "addSpel";
    }

    @GetMapping("/logout")
    public String logout(){
        return "startpage";
    }
}
