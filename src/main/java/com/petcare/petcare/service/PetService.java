package com.petcare.petcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.petcare.petcare.model.Pet;
import com.petcare.petcare.repository.PetRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public List<Pet> findAll() {
        return petRepository.findAll();
    }



    public Pet atualizarPet(Long id, Pet petAtualizado){
        Pet petExistente = petRepository.findById(id).orElse(null);

        if(petExistente == null){
                throw new EntityNotFoundException("Pet não encontrado com ID: " + id); 
        }

        petExistente.setNome(petAtualizado.getNome());
        petExistente.setEspecie(petAtualizado.getEspecie());
        petExistente.setCor(petAtualizado.getCor());
        petExistente.setIdade(petAtualizado.getIdade());

        return petRepository.save(petExistente);
    }

    public Pet existePet(Long id){
        Pet pet = petRepository.findById(id).orElse(null);

        return pet;
    }

    public void excluirPet(Long id){
        Pet pet = petRepository.findById(id).orElse(null);
        if(pet == null){
            throw new EntityNotFoundException("Ped não encontrado com ID: " + id);
        }

        petRepository.delete(pet);
    }
    

}
