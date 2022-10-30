package com.smportfolio.myproject.repository;

import com.smportfolio.myproject.entity.Experiencia;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExperienciaRepository extends JpaRepository<Experiencia, Long>{
    
    public Optional<Experiencia> findByNombreExp(String nombreExp);
    
    public boolean existsByNombreExp(String nombreExp);
    
}
