package com.smportfolio.myproject.repository;

import com.smportfolio.myproject.entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Long>{
    public Optional<Persona> findByName(String name);
    public boolean existsByName(String name);
}
