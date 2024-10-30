package com.tugas5.apotek.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.tugas5.apotek.models.Obat;
import com.tugas5.apotek.service.ObatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ObatController {
    @Autowired
    private ObatService obatService;

    @GetMapping("home")
    public String home(Model model) {
        List<Obat> obat=obatService.getAllObat();
        model.addAttribute("obat", obat);
        return ("home");
    }
    

    @GetMapping("delete-obat/{id}")
    public String deleteObat(@PathVariable(value="id") Long id) {
        obatService.deleteById(id);
        return ("redirect:/home");
    }

    @GetMapping("add-obat")
    public String addObat(Model model) {
        Obat obat=new Obat();
        model.addAttribute("obat", obat);
        return ("add-obat");
    }
    
    @PostMapping("save-obat")
    public String saveObat(@ModelAttribute("obat") Obat obat) {
        obatService.save(obat);
        return "redirect:add-obat";
    }
    
}
