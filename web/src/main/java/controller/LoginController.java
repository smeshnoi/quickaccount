package controller;

import com.quickaccount.entity.Currency;
import com.quickaccount.entity.User;
import com.quickaccount.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LoginController {
    private CurrencyService currencyService;

    @Autowired
    public LoginController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @ModelAttribute("user")
    public User initUser() {
        return new User();
    }

    @ModelAttribute("currencies")
    public List<Currency> currencies() {
        return currencyService.findAll();
    }

    @GetMapping(value = "/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String getUser(User user) {
        System.out.println(" 111 " + user);
        return "login";
    }

    @GetMapping("/logout")
    public String showLogoutPage() {
        return "logout";
    }

    @GetMapping(value = "/registry")
    public String showRegistryPage() {
        return "adduser";
    }
}
