package com.petcare.petcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.petcare.petcare.model.Responsavel;
import com.petcare.petcare.repository.ResponsavelRepository;
import java.util.List;

@Service
public class ResponsavelService {

    @Autowired
    private ResponsavelRepository ResponsavelRepository;

    public List<Responsavel> findAll() {
        return ResponsavelRepository.findAll();
    }

    public Responsavel criarResponsavel(Responsavel Responsavel){
        Responsavel novoResponsavel = ResponsavelRepository.save(Responsavel);
        return novoResponsavel;
    }

    public Responsavel existeResponsavel(Long id){
        Responsavel responsavel = ResponsavelRepository.findById(id).orElse(null);

        return responsavel;
    }

    public void delResponsavel(Long id){
        Responsavel responsavel = ResponsavelRepository.findById(id).orElse(null);

        ResponsavelRepository.delete(responsavel);
    }

    public Responsavel atualizarResponsavel(Long id, Responsavel responsavelAtualizado){
        Responsavel responsavel = ResponsavelRepository.findById(id).orElse(null);

        responsavel.setNome(responsavelAtualizado.getNome()); // Com as lógicas de baixo, não retornou o novo nome atualizado!
    

        if(responsavel.getEmail() != null && responsavel.getEmail() != responsavel.getEmail()){
            responsavel.setEmail(responsavelAtualizado.getEmail());
        }

        if(responsavel.getEndereco() != null && responsavel.getEndereco() != responsavel.getEndereco()){
            responsavel.setEndereco(responsavelAtualizado.getEndereco());
        }

        if(responsavel.getTelefone1() != null && responsavel.getTelefone1() != responsavel.getTelefone1()){
            responsavel.setTelefone1(responsavelAtualizado.getTelefone1());
        }

        if(responsavel.getTelefone2() != null && responsavel.getTelefone2() != responsavel.getTelefone2()){
            responsavel.setTelefone2(responsavelAtualizado.getTelefone2());
        }

        return ResponsavelRepository.save(responsavel);
    }
}
