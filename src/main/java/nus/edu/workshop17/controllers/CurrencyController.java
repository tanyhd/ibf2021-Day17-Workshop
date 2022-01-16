package nus.edu.workshop17.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nus.edu.workshop17.services.CurrencyService;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping
public class CurrencyController {

    @Autowired
    CurrencyService currencySerivce;

    @GetMapping("/currency")
    public String getallCurrency (Model model) {
        model.addAttribute("currency", currencySerivce.getAllCurrency());
        return "currency";
    }
    
    
}
