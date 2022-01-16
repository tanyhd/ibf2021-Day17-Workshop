package nus.edu.workshop17.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.lettuce.core.ValueScanCursor;
import nus.edu.workshop17.services.CurrencyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping
public class CurrencyController {

    @Autowired
    CurrencyService currencySerivce;

    @GetMapping(path ="/currency")
    public String getallCurrency (Model model) {
        model.addAttribute("currency", currencySerivce.getAllCurrency());
        return "currency";
    }

    @PostMapping(path = "/currency")
    public String getResult(@RequestBody MultiValueMap<String, String> form,  Model model) {
        String amountToChange = form.getFirst("amountToChange");
        String currency_1 = form.getFirst("currency_1");
        String currency_2 = form.getFirst("currency_2");
        
        model.addAttribute("convertedValue", currencySerivce.convert(currency_1, currency_2, amountToChange));
        model.addAttribute("currency_1", currency_1);
        model.addAttribute("currency_2", currency_2);
        model.addAttribute("amountToChange", amountToChange);
        return "result";
    }
    
    
}
