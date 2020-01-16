package com.example.Game;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SpelController {

    private static final int PAGE_SIZE = 8;

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
    public String fragesport(Model model,@RequestParam(value="page", required=false, defaultValue="1") int page) {

        //List<Spel> result =  spelRepository.getGamesByCategory("'Frågesportsspel'");
        List<Spel> spels = spelRepository.getPage(page-1, PAGE_SIZE, "'Frågesportsspel'", "'");
        int pageCount = spelRepository.numberOfPages(PAGE_SIZE, "'Frågesportsspel'", "'");


        model.addAttribute("fragesport", spelRepository.getGameOwner(spels));
        model.addAttribute("pages", page);
        model.addAttribute("currentPage", page);
        model.addAttribute("showPrev", page > 1);
        model.addAttribute("showNext", page < pageCount);
        return "fragesport";
    }



    @GetMapping("/musik")
    public String musik(Model model,@RequestParam(value="page", required=false, defaultValue="1") int page) {
        List<Spel> spels = spelRepository.getPage(page-1, PAGE_SIZE, "'Musikspel'", "'");
        int pageCount = spelRepository.numberOfPages(PAGE_SIZE, "'Musikspel'", "'");

        model.addAttribute("musik", spelRepository.getGameOwner(spels));
        model.addAttribute("pages", page);
        model.addAttribute("currentPage", page);
        model.addAttribute("showPrev", page > 1);
        model.addAttribute("showNext", page < pageCount);
        return "musik";
    }

    @GetMapping("/pussel")
    public String pussel(Model model,@RequestParam(value="page", required=false, defaultValue="1") int page) {
        List<Spel> spels = spelRepository.getPage(page-1, PAGE_SIZE, "'Pussel'", "'");
        int pageCount = spelRepository.numberOfPages(PAGE_SIZE, "'Pussel'", "'");

        model.addAttribute("pussel", spelRepository.getGameOwner(spels));
        model.addAttribute("pages", page);
        model.addAttribute("currentPage", page);
        model.addAttribute("showPrev", page > 1);
        model.addAttribute("showNext", page < pageCount);
        return "pussel";
    }

    @GetMapping("/strategispel")
    public String strategispel(Model model,@RequestParam(value="page", required=false, defaultValue="1") int page) {
       // List <Spel> result = spelRepository.getGamesByCategory("'Strategispel'");
        List<Spel> spels = spelRepository.getPage(page-1, PAGE_SIZE, "'Strategispel'", "'");
        int pageCount = spelRepository.numberOfPages(PAGE_SIZE, "'Strategispel'", "'");


        model.addAttribute("strategispel", spelRepository.getGameOwner(spels));
        model.addAttribute("pages", page);
        model.addAttribute("currentPage", page);
        model.addAttribute("showPrev", page > 1);
        model.addAttribute("showNext", page < pageCount);
        return "strategispel";
    }


    @GetMapping("/barnspel")
    public String barnspel(Model model,@RequestParam(value="page", required=false, defaultValue="1") int page) {
        //List <Spel> result = spelRepository.getGamesByCategory("'Barnspel'");
        List<Spel> spels = spelRepository.getPage(page-1, PAGE_SIZE, "'Barnspel'", "'");
        int pageCount = spelRepository.numberOfPages(PAGE_SIZE, "'Barnspel'", "'");

        model.addAttribute("barnspel", spelRepository.getGameOwner(spels));
        model.addAttribute("pages", page);
        model.addAttribute("currentPage", page);
        model.addAttribute("showPrev", page > 1);
        model.addAttribute("showNext", page < pageCount);
        return "barnspel";
    }

    @GetMapping("/familjespel")
    public String familjespel(Model model,@RequestParam(value="page", required=false, defaultValue="1") int page) {
       //List <Spel> result =  spelRepository.getGamesByCategory("'Familjespel'");
        List<Spel> spels = spelRepository.getPage(page-1, PAGE_SIZE, "'Familjespel'", "'");
        int pageCount = spelRepository.numberOfPages(PAGE_SIZE, "'Familjespel'", "'");

        model.addAttribute("familjespel", spelRepository.getGameOwner(spels));
        model.addAttribute("pages", page);
        model.addAttribute("currentPage", page);
        model.addAttribute("showPrev", page > 1);
        model.addAttribute("showNext", page < pageCount);


        return "familjespel";
    }


    @GetMapping("/addSpel")
    String form(HttpSession session, Model model) {
        model.addAttribute("spel", new Spel());
        UserInfo user = (UserInfo) session.getAttribute("user");
        if (user != null && user.getLoggedIn()) {
            return "addSpel";

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

    @GetMapping("/delete/{gameId}")
    public String delete(@PathVariable Integer gameId) {
        spelRepository.deleteGameByGameId(gameId);
        return "redirect:/minasidor";
    }


}
