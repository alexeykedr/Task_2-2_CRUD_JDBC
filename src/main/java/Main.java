

import model.Developer;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        JdbcDeveloperRepositoryImpl jdbc = new JdbcDeveloperRepositoryImpl();
        List <Developer> developers = jdbc.getDeveloperData("select * from developer");
        System.out.println(developers);
    }
}