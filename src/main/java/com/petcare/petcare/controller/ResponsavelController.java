package com.petcare.petcare.controller;

import com.petcare.petcare.service.ResponsavelService;
import com.petcare.petcare.model.Responsavel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/responsaveis")
public class ResponsavelController {

    @Autowired
    private ResponsavelService ResponsavelService;

    @GetMapping("/listarresponsaveis")
    public List<Responsavel> getAllResponsavels() {
        return ResponsavelService.findAll();
    }

    @PostMapping("/incluirresponsavel")
    public ResponseEntity<Responsavel> criarResponsavel(@RequestBody Responsavel Responsavel) {
        Responsavel novoResponsavel = ResponsavelService.criarResponsavel(Responsavel);

        return new ResponseEntity<>(novoResponsavel, HttpStatus.CREATED);
    }

    @PutMapping("/atualizarresponsavel/{id}")
    public ResponseEntity<Responsavel> atualizarResponsavel(@PathVariable Long id,
            @RequestBody Responsavel responsavelAtualizado) {
        if (ResponsavelService.existeResponsavel(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Responsavel responsavel = ResponsavelService.atualizarResponsavel(id, responsavelAtualizado);
        return new ResponseEntity<>(responsavel, HttpStatus.OK);

    }

    @DeleteMapping("/delresponsavel/{id}")
    public ResponseEntity<Responsavel> delResponsavel(@PathVariable Long id) {
        if (ResponsavelService.existeResponsavel(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ResponsavelService.excluirResponsavel(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

    @GetMapping("/obterresponsavel/{id}")
    public ResponseEntity<Responsavel> obterResponsavel(@PathVariable Long id) {
        Responsavel responsavel = ResponsavelService.existeResponsavel(id);

        if (responsavel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(responsavel, HttpStatus.OK);
    }

}