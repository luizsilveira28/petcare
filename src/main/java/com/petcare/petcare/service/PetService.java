package com.petcare.petcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.petcare.petcare.model.Pet;
import com.petcare.petcare.model.Responsavel;
import com.petcare.petcare.repository.PetRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private ResponsavelService responsavelService; // Injeção do serviço ResponsavelService


    @Autowired
    private PetRepository petRepository;

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public Pet atualizarPet(Long id, Pet petAtualizado) {
        Pet petExistente = petRepository.findById(id).orElse(null);

        if (petExistente == null) {
            throw new EntityNotFoundException("Pet não encontrado com ID: " + id);
        }

        if (petAtualizado.getNome() != null) {
            petExistente.setNome(petAtualizado.getNome());
        }

        if (petAtualizado.getCor() != null) {
            petExistente.setCor(petAtualizado.getCor());
        }

        if (petAtualizado.getEspecie() != null) {
            petExistente.setEspecie(petAtualizado.getEspecie());
        }

        if (petAtualizado.getIdade() != 0 && petAtualizado.getIdade() != petExistente.getIdade()) {
            petExistente.setIdade(petAtualizado.getIdade());
        }

        return petRepository.save(petExistente);
    }

    public Pet existePet(Long id) {
        Pet pet = petRepository.findById(id).orElse(null);

        return pet;
    }

    public void excluirPet(Long id) {
        Pet pet = petRepository.findById(id).orElse(null);
        if (pet == null) {
            throw new EntityNotFoundException("Pet não encontrado com ID: " + id);
        }

        petRepository.delete(pet);
    }

    public Pet criarPet(Pet pet) {
        // Verifica se o responsável fornecido existe
        Responsavel responsavel = responsavelService.existeResponsavel(pet.getResponsavel().getId());
        if (responsavel == null) {
            throw new EntityNotFoundException("Responsável não encontrado com ID: " + pet.getResponsavel().getId());
        }
        
        pet.setResponsavel(responsavel); // Associa o responsável ao pet
        
        // Adiciona o pet à lista de pets do responsável
        responsavel.getPets().add(pet); 
        responsavelService.atualizarResponsavel(responsavel.getId(), responsavel);
        
        // Salva o pet no banco de dados
        Pet novoPet = petRepository.save(pet);
        return novoPet;
    }

    public Pet listarPet(Long id) {
        Pet pet = petRepository.findById(id).orElse(null);
        if (pet == null) {
            throw new EntityNotFoundException("Pet não encontrado com ID: " + id);
        }

        return pet;
    }

}
