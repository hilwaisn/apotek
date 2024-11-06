package com.tugas5.apotek.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.tugas5.apotek.models.Supplier;
import com.tugas5.apotek.service.SupplierService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping("/supplier")
    public String supplier(Model model) {
        List<Supplier> supplier = supplierService.getAllSupplier();
        model.addAttribute("supplier", supplier);
        return "supplier";
    }

    @GetMapping("/delete-supplier/{id}")
    public String deleteSupplier(@PathVariable("id") Integer id) {
        supplierService.deleteById(id);
        return "redirect:/supplier";
    }

    @GetMapping("/add-supplier")
    public String addSupplier(Model model) {
        Supplier supplier = new Supplier();
        model.addAttribute("supplier", supplier);
        return "add-supplier";
    }

    @PostMapping("/save-supplier")
    public String saveSupplier(@ModelAttribute("supplier") Supplier supplier) {
        supplierService.save(supplier);
        return "redirect:/supplier";
    }

    @GetMapping("/update-supplier/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Supplier supplier = supplierService.findById(id);
        model.addAttribute("supplier", supplier);
        return "update-supplier";
    }

    @PostMapping("/update-supplier/{id}")
    public String updateSupplier(@PathVariable("id") Integer id, @ModelAttribute("supplier") Supplier supplier) {
        Supplier update = supplierService.findById(id);
        if (update != null) {
            update.setName(supplier.getName());
            update.setAddress(supplier.getAddress());
            update.setPhone(supplier.getPhone());
            supplierService.save(update);
        }
        return "redirect:/supplier";
    }
}