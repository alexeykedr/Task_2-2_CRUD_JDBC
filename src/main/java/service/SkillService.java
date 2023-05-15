package service;

import repository.GenericRepository;
import model.Skill;
import repository.SkillRepository;

import repository.jdbc.JdbcSkillRepositoryImpl;

import java.util.List;

public class SkillService implements GenericRepository<Skill, Long> {
    private final SkillRepository skillRepository;

    public SkillService() {
        this.skillRepository = new JdbcSkillRepositoryImpl();
    }

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

