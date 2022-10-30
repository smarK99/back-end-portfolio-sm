package com.smportfolio.myproject.security.repository;

import com.smportfolio.myproject.security.entity.Rol;
import com.smportfolio.myproject.security.enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Long>{
    public Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
