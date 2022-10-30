package com.smportfolio.myproject.repository;

import com.smportfolio.myproject.entity.Educacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEducacionRepository extends JpaRepository<Educacion, Long>{
    
    //Metodos para implementar
    public Optional<Educacion> findByNombreEdu(String nombreEdu);
    
    public boolean existsByNombreEdu(String nombreEdu);
}
