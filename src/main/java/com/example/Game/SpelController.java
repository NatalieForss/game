package com.example.Game;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @GetMapping("/addSpel")
    String form(Model model) {
        model.addAttribute("spel", new Spel());

        return "addSpel";
    }
    @PostMapping("/addSpel")
    String addSpel (HttpSession session, Model model, @ModelAttribute Spel spel) {
        model.addAttribute("spel", spel);

        List<Spel> spels = (List<Spel>)session.getAttribute("spels");
        if (spels == null) {
            spels = new ArrayList<>();
            session.setAttribute("spels", spels);
        }

        spels.add(spel);

        return "addRestaurant";
    }

}
