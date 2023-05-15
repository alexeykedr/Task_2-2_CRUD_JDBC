package service;

import model.Developer;
import repository.GenericRepository;
import repository.DeveloperRepository;
import repository.jdbc.JdbcDeveloperRepositoryImpl;

import java.util.List;

public class DeveloperService implements GenericRepository<Developer, Long> {
    private final DeveloperRepository developerRepository;



    public DeveloperService() {
        this.developerRepository = new JdbcDeveloperRepositoryImpl();
    }

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }


    @Override
    public Developer save(Developer entity) {
        return developerRepository.save(entity);
    }

    @Override
    public Developer update(Developer entity) {
        return developerRepository.update(entity);
    }

    @Override
    public Developer getById(Long id) {
        return developerRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        developerRepository.deleteById(id);

    }

    @Override
    public List<Developer> getAll() {
        return developerRepository.getAll();
    }

}
