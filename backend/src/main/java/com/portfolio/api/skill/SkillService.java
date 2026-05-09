package com.portfolio.api.skill;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.api.config.ResourceNotFoundException;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Transactional(readOnly = true)
    public List<Skill> findAll() {
        return skillRepository.findAllByOrderByCategoryAscSkillNameAscIdAsc();
    }

    @Transactional
    public Skill create(SkillRequest request) {
        Skill skill = new Skill();
        apply(request, skill);
        return skillRepository.save(skill);
    }

    @Transactional
    public Skill update(Long id, SkillRequest request) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found: " + id));
        apply(request, skill);
        return skill;
    }

    @Transactional
    public void delete(Long id) {
        if (!skillRepository.existsById(id)) {
            throw new ResourceNotFoundException("Skill not found: " + id);
        }
        skillRepository.deleteById(id);
    }

    private void apply(SkillRequest request, Skill skill) {
        if (request.proficiencyLevel() != null
                && (request.proficiencyLevel() < 1 || request.proficiencyLevel() > 100)) {
            throw new IllegalArgumentException("proficiencyLevel must be between 1 and 100");
        }
        skill.setSkillName(request.skillName());
        skill.setCategory(request.category());
        skill.setProficiencyLevel(request.proficiencyLevel());
    }
}
