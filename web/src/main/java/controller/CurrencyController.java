package controller;

import com.quickaccount.service.CurrencyService;
import com.quickaccount.entity.Currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web. bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class CurrencyController {
    private CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @ModelAttribute("currencies")
    public List<Currency> currencies() {
        return currencyService.findAll();
    }

    @GetMapping("/currency")
    public String openPageCurrency() {
        return "currency";
    }
}
