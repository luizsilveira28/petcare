package com.petcare.petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.petcare.petcare.model.Dono;

public interface DonoRepository extends JpaRepository<Dono, Long> {
    // Métodos do repositório DonoRepository
}
