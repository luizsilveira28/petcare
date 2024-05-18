package com.petcare.petcare.controller;
import com.petcare.petcare.service.DonoService;
import com.petcare.petcare.model.Dono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

public class DonoController {

    @Autowired
    private DonoService donoService;

    @GetMapping("/donos")
    public List<Dono> getAllDonos() {
        return donoService.findAll();
    }

    // Outros endpoints conforme necessário
}