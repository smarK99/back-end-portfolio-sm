package com.smportfolio.myproject.repository;

import com.smportfolio.myproject.entity.Skill;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISkillRepository extends JpaRepository<Skill, Long>{
    //Metodos
    public Optional<Skill> findByNombreSkill(String nombreSkill);
    
    public boolean existsByNombreSkill(String nombreSkill);
}
