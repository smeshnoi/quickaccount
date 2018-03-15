package controller;

import com.quickaccount.entity.Rate;
import com.quickaccount.service.CurrencyService;
import com.quickaccount.service.RateService;
import com.quickaccount.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class RateController {
    private RateService rateService;
    private UserService userService;
    private CurrencyService currencyService;

    @Autowired
    public RateController(RateService rateService, UserService userService, CurrencyService currencyService) {
        this.rateService = rateService;
        this.userService = userService;
        this.currencyService = currencyService;
    }

    @GetMapping("/rates")
    public String getRatesPage (Model model, Principal principal) {
        model.addAttribute("currencies", currencyService.findAll());
        model.addAttribute("rates");
        return "rates";
    }

    @PostMapping("/rates")
    public String addRate (Rate rate, Principal principal) {
        System.out.println(rate);
        return "rates";
    }

    @GetMapping("/addrate")
    public String addRatePage (Model model, Principal principal) {
        model.addAttribute("rate", new Rate());
        model.addAttribute("currencies", currencyService.findAll());
        model.addAttribute("rates");
        model.addAttribute("user", userService.getUserbyLogin(principal.getName()));
        return "addrate";
    }
}
