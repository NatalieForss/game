package com.example.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.validation.Valid;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SpelController {
    @Autowired
    SpelRepository spelRepository;
    @Autowired
    Metods metods;



    @GetMapping("/spel")
    public String spel(){
        return "startpage";
    }

    @GetMapping("/fragesport")
    public String fragesport(){
        // model.addAttribute("fragesport")
        return "fragesport";
    }

    @GetMapping("/musik")
    public String musik(){
        // model.addAttribute("musik")
        return "musik";
    }

    @GetMapping("/pussel")
    public String pussel(){
        // model.addAttribute("pussel")
        return "pussel";
    }

    @GetMapping("/strategispel")
    public String strategispel(){
        // model.addAttribute("strategispel")
        return "strategispel";
    }

    @GetMapping("/barnspel")
    public String barnspel(){
        // model.addAttribute("barnspel")
        return "barnspel";
    }

    @GetMapping("/familjespel")
    public String familjespel(){
        // model.addAttribute("familjespel")
        return "familjespel";
    }

   @GetMapping("/addSpel")
  String form(HttpSession session, Model model) {
       model.addAttribute("spel", new Spel());
      if(session.getAttribute("userName")!=null){



        return "login";
       }
       return "addSpel";
   }


//    @GetMapping("/addSpel")
//    String form(Model model) {
//        model.addAttribute("spel", new Spel());
//
//        return "addSpel";
//    }

   /* @PostMapping("/addSpel")
    String addSpel (HttpSession session, Model model, @ModelAttribute Spel spel) {
        model.addAttribute("spel", spel);

        List<Spel> spels = (List<Spel>)session.getAttribute("spels");
        if (spels == null) {
            spels = new ArrayList<>();
            session.setAttribute("spels", spels);
        }

        metods.addSpel(spel);

        return "confirmation";
    }*/

   @PostMapping("/addSpel")
    public String addSpel( HttpSession session, Model model,@ModelAttribute Spel spel, BindingResult bindingResult) {

       if (bindingResult.hasErrors()) {

           return "addSpel";
       }
        model.addAttribute("spel", spel);

        metods.addSpel(spel);

       List<Spel> spels = (List<Spel>)session.getAttribute("spels");
       if (spels == null) {
           spels = new ArrayList<>();
           session.setAttribute("spels", spels);
       }

       metods.addSpel(spel);

       return "confirmation";
   }


    @GetMapping("/confirmation")
    public String confirm(){
        return "confirmation";
    }



    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "startpage";
    }



}
