package repository.jdbc;

import model.Developer;
import repository.DeveloperRepository;
import sql.JdbcOperator;
import util.CheckCommand;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeveloperRepositoryJdbcImpl implements DeveloperRepository {
    @Override
    public Developer save(Developer developer) {
        if (developer == null) {
            return null;
        }
        if (developer.getId() == null) {
            JdbcOperator.execute("INSERT INTO developer (first_name, last_name, skill, specialty) VALUES (?,?,?,?)", preparedStatement -> {
                try {
                    preparedStatement.setString(1, developer.getFirstName());
                    preparedStatement.setString(2, developer.getLastName());
                    preparedStatement.setString(3, developer.getSkills().toString());
                    preparedStatement.setString(4, developer.getSpecialty().toString());
                    preparedStatement.executeUpdate();
                    return null;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            });

        } else {
            System.out.println("Developer isn't new. Cannot be saved.");
        }
        return developer;
    }

    @Override
    public Developer update(Developer developer) {
        var updateDeveloper = JdbcOperator.transactionalExecute(connection -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE developer SET first_name =?, last_name =? WHERE id =?")) {
                preparedStatement.setString(1, developer.getFirstName());
                preparedStatement.setString(2, developer.getLastName());
                preparedStatement.setLong(3, developer.getId());
                return preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return updateDeveloper == 1 ? developer : null;
    }

    @Override
    public Developer getById(Long id) {
        return JdbcOperator.execute("SELECT * FROM developer WHERE id =?", preparedStatement -> {
            try {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    return null;
                }
                return getDeveloper(id, resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void deleteById(Long id) {
        JdbcOperator.execute("DELETE FROM developer WHERE id =?", preparedStatement -> {
            try {
                preparedStatement.setLong(1, id);
                if (preparedStatement.executeUpdate() > 0) {
                    System.out.println("Developer deleted successfully.");
                } else {
                    System.out.println(CheckCommand.ERR_ID);
                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        });

    }

    @Override
    public List<Developer> getAll() {
        return JdbcOperator.transactionalExecute(connection -> {
            Map<Long, Developer> developerMap = new HashMap<>();
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM developer " +
                            "LEFT JOIN skill ON developer.id = skill.developer_id " +
                            "LEFT JOIN specialty ON skill.id = specialty.skill_id " +
                            "ORDER BY developer.id;")) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    //Developers
                    Long developerId = resultSet.getLong("id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    Developer developer = new Developer(developerId, firstName, lastName, null, null, null);
                    developerMap.putIfAbsent(developerId, developer);


                }



            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private Developer getDeveloper(Long id, ResultSet resultSet) throws SQLException {
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        return new Developer(id, firstName, lastName, null, null, null);

    }
}
