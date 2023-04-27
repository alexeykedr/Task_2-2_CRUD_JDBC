package repository;

import controller.DBUtils;
import model.Developer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcDeveloperRepositoryImpl implements DeveloperRepository{
    @Override
    public List<Developer> getDeveloperData(String query) {
        List<Developer> developers = new ArrayList<>();
        List<String> skillsToString = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                // to get List<Skill> from database
                skillsToString.add(resultSet.getString("skill"));
                //to get Specialty from database
                String specialtyToString = resultSet.getString("specialty");

                developers.add(new Developer(id, firstName, lastName,  skillsToString, specialtyToString));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return developers;
    }
}
