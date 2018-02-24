package controller;

import com.quickaccount.service.CurrencyService;
import com.quickaccount.entity.Currency;
import config.ApplicationContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web. bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class CurrencyController {
    @ModelAttribute("currencies")
    public List<Currency> currencies() {
        CurrencyService currencyService = ApplicationContextHolder.getBean(CurrencyService.class);
        return currencyService.getAll();
    }

    @GetMapping("/currency")
    public String openPageCurrency() {
        return "currency";
    }
}
