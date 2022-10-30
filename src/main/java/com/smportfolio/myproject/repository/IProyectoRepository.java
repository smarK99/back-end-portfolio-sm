package com.smportfolio.myproject.repository;

import com.smportfolio.myproject.entity.Proyecto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProyectoRepository extends JpaRepository<Proyecto, Long>{
    
    //El Optional puede o no tener un valor no nulo
    public Optional<Proyecto> findByNombreProyecto(String nombreProyecto);
    
    public boolean existsByNombreProyecto(String nombreProyecto);
    
}
