package com.petcare.petcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.petcare.petcare.model.Pet;
import com.petcare.petcare.service.PetService;

import java.util.List;

@RestController
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping("/pets/listarpets/")
    public List<Pet> getAllPets() {
        return petService.findAll();
    }

    @GetMapping("/pets/consultarpet/{id}/")
    public ResponseEntity<Pet> listarPet(@PathVariable Long id) {
        Pet pet = petService.listarPet(id);
        return ResponseEntity.ok(pet);
    }

    @PostMapping("/pets/incluirpet/")
    public ResponseEntity<Pet> criarPet(@RequestBody Pet pet) {
        Pet novoPet = petService.criarPet(pet);
        return new ResponseEntity<>(novoPet, HttpStatus.CREATED);
    }

    @PutMapping("/pets/atualizarpet/{id}/")
public ResponseEntity<Pet> atualizarPet(@PathVariable Long id, @RequestBody Pet petAtualizado) {
    if (petService.existePet(id) == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    Pet pet = petService.atualizarPet(id, petAtualizado);

    // Atualizar responsáveis do pet
    if (petAtualizado.getResponsavel() != null) {
        pet.setResponsavel(petAtualizado.getResponsavel());
    }

    return new ResponseEntity<>(pet, HttpStatus.OK);
}

    @DeleteMapping("/pets/deletepet/{id}/")
    public ResponseEntity<Pet> delPet(@PathVariable Long id) {
        if (petService.existePet(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        petService.excluirPet(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
