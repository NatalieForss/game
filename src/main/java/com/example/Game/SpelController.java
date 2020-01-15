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
      List<Spel> result =  spelRepository.getGamesByCategory("'Frågesportsspel'");
        model.addAttribute("fragesport", spelRepository.getGameOwner(result));
        return "fragesport";
    }


    @GetMapping("/musik")
    public String musik(Model model) {
       List <Spel> result = spelRepository.getGamesByCategory("'Musikspel'");
       model.addAttribute("musik", spelRepository.getGameOwner(result));
        return "musik";
    }

    @GetMapping("/pussel")
    public String pussel(Model model) {
        List <Spel> result =  spelRepository.getGamesByCategory("'Pussel'");
        model.addAttribute("pussel", spelRepository.getGameOwner(result));
        return "pussel";
    }

    @GetMapping("/strategispel")
    public String strategispel(Model model) {
        List <Spel> result = spelRepository.getGamesByCategory("'Strategispel'");
        model.addAttribute("strategispel", spelRepository.getGameOwner(result));
        return "strategispel";
    }


    @GetMapping("/barnspel")
    public String barnspel(Model model) {
        List <Spel> result = spelRepository.getGamesByCategory("'Barnspel'");
        model.addAttribute("barnspel", spelRepository.getGameOwner(result));
        return "barnspel";
    }

    @GetMapping("/familjespel")
    public String familjespel(Model model) {
       List <Spel> result =  spelRepository.getGamesByCategory("'Familjespel'");
       model.addAttribute("familjespel", spelRepository.getGameOwner(result));
        return "familjespel";
    }


    @GetMapping("/addSpel")
    String form(HttpSession session, Model model) {
        model.addAttribute("spel", new Spel());
        UserInfo user = (UserInfo) session.getAttribute("user");
        if (user != null && user.getLoggedIn()) {
            return "addSpel";
//        if (session.getAttribute("userName") != null) {
//
//            return "login";
        }
        return "login2";
    }

    @PostMapping("/addSpel")
    public String addSpel(HttpSession session, Model model, @ModelAttribute Spel spel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addSpel";
        }

        UserInfo user = (UserInfo) session.getAttribute("user");
        if (user == null ) {
            return "login2";
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
        List<Spel> result = spelRepository.getGamesByGameName(search);
        if (result.isEmpty()) {
            return "ingatraffar"; //TODO
        }
        model.addAttribute("searchResult", spelRepository.getGameOwner(result));
        return "searchResult";
    }

    @GetMapping("/ingatraffar")
    public String ingatraffar() {
        return "startpage";
    }


}
