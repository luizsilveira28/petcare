package com.petcare.petcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/pets")
    public List<Pet> getAllPets() {
        return petService.findAll();
    }

    public ResponseEntity<Pet> criarPet(@RequestBody Pet pet) {
        Pet novoPet = new Pet();

        return new ResponseEntity<>(novoPet, HttpStatus.CREATED);
    }

    @PutMapping("/pets/{id}")
    public ResponseEntity<Pet> atualizarPet(@PathVariable Long id, @RequestBody Pet petAtualizado) {
        if (petService.existePet(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Pet pet = petService.atualizarPet(id, petAtualizado);

        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @DeleteMapping("/pets/{id}")
    public ResponseEntity<Pet> delPet(@PathVariable Long id) {
        if (petService.existePet(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        petService.excluirPet(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
