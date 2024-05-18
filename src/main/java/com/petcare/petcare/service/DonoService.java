package com.petcare.petcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.petcare.petcare.model.Dono;
import com.petcare.petcare.repository.DonoRepository;
import java.util.List;

@Service
public class DonoService {

    @Autowired
    private DonoRepository donoRepository;

    public List<Dono> findAll() {
        return donoRepository.findAll();
    }

    // Outros métodos conforme necessário
}
