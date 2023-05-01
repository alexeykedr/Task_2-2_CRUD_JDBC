package service;

import model.GenericRepository;
import model.Skill;
import repository.SkillRepository;

import java.util.List;

public class SkillService implements GenericRepository<Skill, Long> {
    SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Skill save(Skill entity) {
        return skillRepository.save(entity);
    }

    @Override
    public Skill update(Skill entity) {
        return skillRepository.update(entity);
    }

    @Override
    public Skill getById(Long id) {
        return skillRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        skillRepository.deleteById(id);
    }

    @Override
    public List<Skill> getAll() {
        return skillRepository.getAll();
    }
}

