package controller;

import model.Developer;
import model.GenericRepository;
import repository.DeveloperRepository;
import service.DeveloperService;
import view.ConsoleDeveloperView;

import java.util.List;

public class DeveloperController implements GenericRepository<Developer, Long> {
    DeveloperService developerService;

    public DeveloperController() {
        developerService = new DeveloperService(new DeveloperRepositoryJdbcImpl());
    }

    @Override
    public Developer save(Developer entity) {
        return null;
    }

    @Override
    public Developer update(Developer entity) {
        return null;
    }

    @Override
    public Developer getById(Long aLong) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public List<Developer> getAll() {
        return null;
    }


    // Методы для обработки запросов пользователя и взаимодействия с сервисным слоем
}
//TODO: to make Controllers
