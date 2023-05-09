package controller;

import model.Developer;
import repository.GenericRepository;
import repository.jdbc.JdbcDeveloperRepositoryImpl;
import service.DeveloperService;
import util.CheckCommandUtils;

import java.util.List;

public class DeveloperController implements GenericRepository<Developer, Long> {
    private final DeveloperService developerService;

    public DeveloperController() {
        developerService = new DeveloperService(new JdbcDeveloperRepositoryImpl());
    }

    @Override
    public Developer save(Developer developer) {
        return developerService.save(developer);

    }

    @Override
    public Developer update(Developer developer) {
        if (developer == null){
            return null;
        }
        if (developer.isNew()){
            System.out.println("Developer is new, cannot be updated");
            return null;
        }
        Developer developerUpdated = developerService.update(developer);
        if (developerUpdated != null) {
            System.out.println("Developer is updated");
            System.out.println(developerUpdated);
        } else {
            System.out.println(CheckCommandUtils.ERR_ID);
            System.out.println("Developer is not updated");
        }
        return developerUpdated;
    }

    @Override
    public Developer getById(Long id) {
        Developer developer = developerService.getById(id);
        if (developerService == null) {
            System.out.println(CheckCommandUtils.splitter);
            System.out.println("!!! Developer not found!!!");
            System.out.println(CheckCommandUtils.splitter);
        }
        return developer;

    }

    @Override
    public void deleteById(Long id) {
        developerService.deleteById(id);
    }

    @Override
    public List<Developer> getAll() {
        return developerService.getAll();
    }
}
//TODO: to make Controllers - done
