package service;

import model.*;
import repository.SpecialtyRepository;

import java.util.List;

public class SpecialtyService implements GenericRepository<Specialty, Long> {
    SpecialtyRepository specialtyRepository;

    public SpecialtyService(SpecialtyRepository repository) {
        this.specialtyRepository = repository;
    }
    @Override
    public Specialty save(Specialty entity) {
        return specialtyRepository.save(entity);
    }

    @Override
    public Specialty update(Specialty entity) {
        return specialtyRepository.update(entity);
    }

    @Override
    public Specialty getById(Long id) {
        return specialtyRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        specialtyRepository.deleteById(id);

    }

    @Override
    public List<Specialty> getAll() {
        return specialtyRepository.getAll();
    }
}
