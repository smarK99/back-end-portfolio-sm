package com.smportfolio.myproject.service;

import com.smportfolio.myproject.entity.Skill;
import com.smportfolio.myproject.entity.Skill;
import com.smportfolio.myproject.repository.ISkillRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpSkillService {
    
    @Autowired
    ISkillRepository iSkillRep;
    
    public List<Skill> List(){
        return iSkillRep.findAll();
    }
    
    //Puede o no contener un valor no nulo
    public Optional<Skill> getOne(Long idSkill){
        return iSkillRep.findById(idSkill);
    }
    
    //Puede o no contener un valor no nulo
    //isPresent()?
    public Optional<Skill> getByNombreSkill(String nombreSkill){
        return iSkillRep.findByNombreSkill(nombreSkill);
    }
    
    public void save(Skill skill){
        iSkillRep.save(skill);
    }
    
    public void delete(Long idSkill){
        iSkillRep.deleteById(idSkill);
    }
    
    public boolean existsById(Long idSkill){
        return iSkillRep.existsById(idSkill);
    }
    
    public boolean existsByNombreSkill(String nombreSkill){
        return iSkillRep.existsByNombreSkill(nombreSkill);
    }
    
}
