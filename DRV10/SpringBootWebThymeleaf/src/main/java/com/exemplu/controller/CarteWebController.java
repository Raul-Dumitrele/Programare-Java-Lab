package com.exemplu.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.exemplu.repository.CarteRepository;
@Controller
public class CarteWebController {
    @Autowired
    CarteRepository repository;
    @GetMapping("/lista-carti")
    public String getListaCarti(Model model) {
        String s="Lista cartilor preluate prin repository";
        model.addAttribute("str",s);
        model.addAttribute("lista",repository.findAll());
        return "carti";
    }
}