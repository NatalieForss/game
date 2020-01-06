package com.example.Game;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

}
