package com.petcare.petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.petcare.petcare.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
    // Métodos do repositório PetRepository
}
