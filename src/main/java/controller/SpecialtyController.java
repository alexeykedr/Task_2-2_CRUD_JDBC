package controller;

import model.Specialty;
import repository.GenericRepository;
import repository.SpecialtyRepository;
import repository.jdbc.JdbcSpecialtyRepositoryImpl;
import service.SpecialtyService;

import java.util.List;

public class SpecialtyController implements GenericRepository<Specialty, Long> {
    private final SpecialtyService specialtyService;


    public SpecialtyController() {
        specialtyService = new SpecialtyService(new JdbcSpecialtyRepositoryImpl());
    }

    @Override
    public Specialty save(Specialty specialty) {
        return specialtyService.save(specialty);
    }

    @Override
    public Specialty update(Specialty specialty) {
        return specialtyService.update(specialty);
    }

    @Override
    public Specialty getById(Long id) {
        return specialtyService.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        specialtyService.deleteById(id);
    }

    @Override
    public List<Specialty> getAll() {
        return specialtyService.getAll();
    }
}
