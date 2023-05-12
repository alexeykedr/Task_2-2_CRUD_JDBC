

import model.Developer;
import repository.jdbc.JdbcDeveloperRepositoryImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        JdbcDeveloperRepositoryImpl jdbc = new JdbcDeveloperRepositoryImpl();
        List <Developer> developer = jdbc.getAll();
        System.out.println(developer);
    }
}