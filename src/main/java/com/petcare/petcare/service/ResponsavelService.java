package com.petcare.petcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petcare.petcare.model.Pet;
import com.petcare.petcare.model.Responsavel;
import com.petcare.petcare.repository.ResponsavelRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class ResponsavelService {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    public List<Responsavel> findAll() {
        return responsavelRepository.findAll();
    }

    public Responsavel criarResponsavel(Responsavel responsavel) {
        return responsavelRepository.save(responsavel);
    }

    public Responsavel existeResponsavel(Long id) {
        return responsavelRepository.findById(id).orElse(null);
    }

    public void excluirResponsavel(Long id) {
        Responsavel responsavel = responsavelRepository.findById(id).orElse(null);
        if (responsavel == null) {
            throw new EntityNotFoundException("Responsável não encontrado com ID: " + id);
        }
    
        // Remover a referência ao responsável dos pets associados
        List<Pet> petsDoResponsavel = responsavel.getPets();
        if (petsDoResponsavel != null && !petsDoResponsavel.isEmpty()) {
            for (Pet pet : petsDoResponsavel) {
                pet.setResponsavel(null); // Remover referência ao responsável
            }
        }
    
        // Excluir o responsável
        responsavelRepository.delete(responsavel);
    }
    

    public Responsavel atualizarResponsavel(Long id, Responsavel responsavelAtualizado) {
        Responsavel responsavel = responsavelRepository.findById(id).orElse(null);
        if (responsavel != null) {
            if (responsavelAtualizado.getNome() != null) {
                responsavel.setNome(responsavelAtualizado.getNome());
            }
            if (responsavelAtualizado.getEmail() != null) {
                responsavel.setEmail(responsavelAtualizado.getEmail());
            }
            if (responsavelAtualizado.getEndereco() != null) {
                responsavel.setEndereco(responsavelAtualizado.getEndereco());
            }
            if (responsavelAtualizado.getTelefone1() != null) {
                responsavel.setTelefone1(responsavelAtualizado.getTelefone1());
            }
            if (responsavelAtualizado.getTelefone2() != null) {
                responsavel.setTelefone2(responsavelAtualizado.getTelefone2());
            }
            return responsavelRepository.save(responsavel);
        } else {
            throw new EntityNotFoundException("Responsável não encontrado com ID: " + id);
        }
    }
}

