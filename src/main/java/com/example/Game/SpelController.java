package com.example.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SpelController {

    @Autowired
    SpelRepository spelRepository;

    @Autowired
    Metods metods;

    @Autowired
    userRepository repository;

    @GetMapping("/spel")
    public String spel() {
        return "startpage";
    }

    @GetMapping("/fragesport")
    public String fragesport(Model model) {
        model.addAttribute("fragesport", spelRepository.getGamesByCategory("'Frågesportsspel'"));
        return "fragesport";
    }

    @GetMapping("/musik")
    public String musik(Model model) {
        model.addAttribute("musikspel", spelRepository.getGamesByCategory("'Musikspel'"));
        return "musik";
    }

    @GetMapping("/pussel")
    public String pussel(Model model) {
        model.addAttribute("pussel", spelRepository.getGamesByCategory("'Pussel'"));
        return "pussel";
    }

    @GetMapping("/strategispel")
    public String strategispel(Model model) {
        model.addAttribute("strategispel", spelRepository.getGamesByCategory("'Strategispel'"));
        return "strategispel";
    }


    @GetMapping("/barnspel")
    public String barnspel(Model model) {
        model.addAttribute("barnspel", spelRepository.getGamesByCategory("'Barnspel'"));
        return "barnspel";
    }

    @GetMapping("/familjespel")
    public String familjespel(Model model) {
        model.addAttribute("familjespel", spelRepository.getGamesByCategory("'Familjespel'"));
        return "familjespel";
    }


    @GetMapping("/addSpel")
    String form(HttpSession session, Model model) {
        model.addAttribute("spel", new Spel());
        UserInfo user = (UserInfo) session.getAttribute("user");
        if (user != null && user.getLoggedIn()) {
            return "addspel";
//        if (session.getAttribute("userName") != null) {
//
//            return "login";
        }
        return "login";
    }

    @PostMapping("/addSpel")
    public String addSpel(HttpSession session, Model model, @ModelAttribute Spel spel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addSpel";
        }

        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        metods.addSpel(spel, userInfo.getId());

        return "confirmation";
    }


    @GetMapping("/confirmation")
    public String confirm() {
        return "confirmation";
    }

    @GetMapping("/meddelande")
    String message() {
        return "message";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "startpage";
    }

    @GetMapping("/")
    String goToIndexPage(Model model, @RequestParam(required = false, defaultValue = "0") String page) {

        int pageNr = Integer.parseInt(page);

        List<Spel> selectedSpel = metods.getSortedSpelList(pageNr, 20, false);


        model.addAttribute("spel", selectedSpel);

        return "startpage";
    }

    @PostMapping("/filter_spel")
    String filterSpel(Model model, @RequestParam(required = false, defaultValue = "false") String available) {
        boolean onlyAvailable = Boolean.parseBoolean(available);

        List<Spel> selectedSpel = metods.getSortedSpelList(0, 20, onlyAvailable);

        model.addAttribute("spel", selectedSpel);

        return "startpage";
    }

    @GetMapping("/omoss")
    public String about(){

        return "aboutUs";
    }

    @GetMapping("/skickat")
    public String sentmessage(){

        return "messageSent";
    }

    @GetMapping("/search")
    public String searchForGames(@RequestParam String search, Model model) {
        System.out.println(search);
        model.addAttribute("gamename", spelRepository.getGameByGamename("'search'"));

        return "searchFunction";
    }



}
