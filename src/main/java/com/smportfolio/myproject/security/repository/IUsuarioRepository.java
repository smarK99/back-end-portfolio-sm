package com.smportfolio.myproject.security.repository;

import com.smportfolio.myproject.security.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{
    public Optional<Usuario> findByUsername(String username);
    public boolean existsByUsername(String username);
    public boolean existsByEmail(String email);
}
