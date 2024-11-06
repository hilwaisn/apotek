package com.tugas5.apotek.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.tugas5.apotek.models.Category;
import com.tugas5.apotek.models.Obat;
import com.tugas5.apotek.models.Supplier;
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
        List<Obat> obat = obatService.getAllObat();
        model.addAttribute("obat", obat);
        List<Category> category = obatService.getAllCategory();
        model.addAttribute("category", category);
        List<Supplier> supplier = obatService.getAllSupplier();
        model.addAttribute("supplier", supplier);

        return "home";
    }

    @GetMapping("/delete-obat/{id}")
    public String deleteObat(@PathVariable("id") Integer id) {
        obatService.deleteById(id);
        return "redirect:/home";
    }

    @GetMapping("add-obat")
    public String addObat(Model model) {
        Obat obat = new Obat();
        model.addAttribute("obat", obat);
        model.addAttribute("category", obatService.getAllCategory());
        model.addAttribute("supplier", obatService.getAllSupplier());

        return "add-obat";
    }

    @PostMapping("save-obat")
    public String saveObat(@ModelAttribute("obat") Obat obat) {
        obatService.save(obat);
        return "redirect:/home";
    }

    @GetMapping("/update-obat/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Obat obat = obatService.findById(id);
        model.addAttribute("obat", obat);
        model.addAttribute("category", obatService.getAllCategory());
        model.addAttribute("supplier", obatService.getAllSupplier());
        return "update-obat";
    }

    @PostMapping("/update-obat/{id}")
    public String updateObat(@PathVariable("id") Integer id, @ModelAttribute("obat") Obat obat) {
        Obat update = obatService.findById(id);
        if (update != null) {
            update.setNamaObat(obat.getNamaObat());
            update.setPrice(obat.getPrice());
            update.setDescription(obat.getDescription());
            update.setCategory(obat.getCategory());
            update.setSupplier(obat.getSupplier());
            obatService.save(update);
        }
        return "redirect:/home";
    }
}