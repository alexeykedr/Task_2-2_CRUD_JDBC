package controller;

import model.Developer;
import model.Skill;
import repository.GenericRepository;
import repository.jdbc.JdbcDeveloperRepositoryImpl;
import repository.jdbc.JdbcSkillRepositoryImpl;
import service.DeveloperService;
import service.SkillService;

import java.util.List;

public class SkillController implements GenericRepository<Skill, Long> {
    private final SkillService skillService;
    private final DeveloperService developerService;

    public SkillController() {
        skillService = new SkillService(new JdbcSkillRepositoryImpl());
        developerService = new DeveloperService(new JdbcDeveloperRepositoryImpl());
    }


    @Override
    public Skill save(Skill skill) {
        if (!skill.isNew()) {
            System.out.println("Skill already exists. Cannot be saved.");
            return null;
        }
        Long developerid = skill.getDeveloper().getId();
        Developer developer = developerService.getById(developerid);
        if (developer == null) {
            System.out.println("Developer with id " + developerid + " does not exist. Skill cannot be saved.");
            return null;
        }
        Skill savedSkill = skillService.save(skill);
        if (savedSkill != null) {
            System.out.println("Skill successfully saved.");
        }
        return savedSkill;
    }

    @Override
    public Skill update(Skill skill) {
        return skillService.update(skill);
    }

    @Override
    public Skill getById(Long id) {
        return skillService.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        skillService.deleteById(id);
    }

    @Override
    public List<Skill> getAll() {
        return skillService.getAll();
    }
}
