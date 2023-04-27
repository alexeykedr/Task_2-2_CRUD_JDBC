package repository;

import model.Developer;

import java.util.List;

public interface DeveloperRepository extends GenericRepository<Developer, Long> {
    public List<Developer> getDeveloperData(String query);

}
