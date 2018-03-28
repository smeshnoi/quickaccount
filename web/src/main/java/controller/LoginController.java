package controller;

import com.quickaccount.entity.Currency;
import com.quickaccount.entity.User;
import com.quickaccount.service.CurrencyService;
import com.quickaccount.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class LoginController {
    private CurrencyService currencyService;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public LoginController(CurrencyService currencyService, UserService userService, PasswordEncoder passwordEncoder) {
        this.currencyService = currencyService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
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

    @PostMapping(value = "/registry")
    public String addUser(User user) {
        System.out.println(user.getCurrency().getCurrency());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user);
        //$2a$10$LwpL7e/dxFDk8ynbwvISqOKgD.ANKv7SAQWmkjfN3z9uQoPjE2CW.
//        Set<Role> roleSet = r
//        user.setRoles();
        userService.save(user);
        return "login";
    }

    @GetMapping("/users")
    public String showUserPages(Model model, Principal principal) {
        User userbyLogin = userService.getUserbyLogin(principal.getName());
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }
}
