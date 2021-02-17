package com.getulus.charondor.gamebody.repository;

import com.getulus.charondor.gamebody.model.character.Skills.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {

}
