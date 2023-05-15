package repository.jdbc;

import model.Developer;
import model.Skill;
import model.Specialty;
import repository.DeveloperRepository;
import processor.JdbcExecutor;
import util.CheckCommandUtils;
import util.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

//import static util.JdbcUtils.connection;
import static util.JdbcUtils.prepareStatement;


public class JdbcDeveloperRepositoryImpl implements DeveloperRepository {
    @Override
    public Developer save(Developer developer) {
        if (developer == null) {
            return null;
        }
        if (developer.getId() == null) {
            // TODO: handle save specialty and skills flow - done
            // вероятнее всего это будут отдельные приватные методы


            JdbcExecutor.execute("INSERT INTO developer (first_name, last_name) VALUES (?,?)", preparedStatement -> {
                try {
                    preparedStatement.setString(1, developer.getFirstName());
                    preparedStatement.setString(2, developer.getLastName());
                    preparedStatement.executeUpdate();

                    saveSkill(developer.getSkills());
                    saveSpecialty(developer.getSpecialty());

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
        var updateDeveloper = JdbcExecutor.transactionalExecute(connection -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE developer SET first_name =?, last_name =? WHERE id =?")) {
                preparedStatement.setString(1, developer.getFirstName());
                preparedStatement.setString(2, developer.getLastName());

                // ? тоже необходимо вынести в приватный метод?
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
        // TODO: use complex JOIN to get all data - done?
        return JdbcExecutor.execute("" +
                "SELECT d.id, d.first_name, d.last_name, d.status, s.name as skill_name, sp.name as specialty_name " +
                "FROM developers d" +
                "LEFT JOIN developer_skills ds ON d.id = ds.developer_id " +
                "LEFT JOIN skills s ON ds.skill_id = s.id " +
                "LEFT JOIN specialties sp ON d.specialty_id = sp.id; ", preparedStatement -> {

            try {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    return null;
                }
                return getDeveloper(resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void deleteById(Long id) {
        JdbcExecutor.execute("DELETE FROM developer WHERE id =?", preparedStatement -> {
            try {
                preparedStatement.setLong(1, id);
                if (preparedStatement.executeUpdate() > 0) {
                    System.out.println("Developer deleted successfully.");
                } else {
                    System.out.println(CheckCommandUtils.ERR_ID);
                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        });

    }

    @Override
    public List<Developer> getAll() {
        return JdbcExecutor.transactionalExecute(connection -> {
            Map<Long, Developer> developerMap = new HashMap<>();
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT d.id, d.first_name, d.last_name, d.status, s.name as skill_name, s.id as skill_id, sp.name as specialty_name " +
                            "FROM developers d " +
                            "LEFT JOIN developer_skills ds ON d.id = ds.developer_id " +
                            "LEFT JOIN skills s ON ds.skill_id = s.id " +
                            "LEFT JOIN specialties sp ON d.specialty_id = sp.id; " +
                            "")) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {

                    //DEVELOPERS
                    Long developerId = resultSet.getLong("id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    Developer developer = new Developer(developerId, firstName, lastName, null, null, null);
                    developerMap.putIfAbsent(developerId, developer);

                    //SKILLS
                    String skillIdString = resultSet.getString("skill_id");
                    if (skillIdString == null) {
                        continue;
                    }
                    long skillId = Long.parseLong(skillIdString);
                    long skillDeveloperId = resultSet.getLong("developer_id");
                    Developer developerRelatedToSkill = developerMap.get(skillDeveloperId);
                    List<Skill> skillsFromMap = developerRelatedToSkill.getSkills();
                    Skill skillFromMapPerDeveloper = null;
                    if (skillsFromMap != null) {
                        skillFromMapPerDeveloper = skillsFromMap.stream().parallel()
                                .filter(skillFromMap -> skillFromMap.getId() == skillId)
                                .findAny().orElse(null);
                    }
                    if (skillFromMapPerDeveloper == null) {
                        String name = resultSet.getString("name");
                        Skill skill = new Skill(skillId, name);
                        if (developerRelatedToSkill.getSkills() == null) {
                            developerRelatedToSkill.setSkills(new ArrayList<>());
                        }
                        developerRelatedToSkill.addSkill(skill);

                    }

                    // SPECIALTY
                    String specialtyIdString = resultSet.getString("id");
                    if (specialtyIdString == null) {
                        continue;
                    }
                    long specialtyId = Long.parseLong(specialtyIdString);
                    String specialtyName = resultSet.getString("name");
                    long developerSkillId = resultSet.getLong("skill_id");
                    Specialty specialty = new Specialty(specialtyId, specialtyName, developerSkillId);


                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return developerMap.values().stream().toList();
        });
    }

    private Developer getDeveloper(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        return new Developer(id, firstName, lastName, null, null, null);

    }


    private void saveSkill(List<Skill> skills) throws SQLException {
        try (PreparedStatement preparedStatement = JdbcUtils.prepareStatement("INSERT INTO skill (id, name) VALUES (?,?)")) {
            for (Skill skill : skills) {
                preparedStatement.setLong(1, skill.getId());
                preparedStatement.setString(2, skill.getName());
                preparedStatement.executeUpdate();

            }

        }
    }

    private static void saveSpecialty(Specialty specialty) throws SQLException {
        try (PreparedStatement prepareStatement = JdbcUtils.prepareStatement("INSERT INTO specialty (id, name) VALUES (?,?)")) {
            prepareStatement.setLong(1, specialty.getId());
            prepareStatement.setString(2, specialty.getName());

        }
    }


}
