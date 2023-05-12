package repository.jdbc;

import model.Skill;
import processor.JdbcExecutor;
import repository.SkillRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcSkillRepositoryImpl implements SkillRepository {

    @Override
    public Skill save(Skill skill) {
        JdbcExecutor.transactionalExecute(connection -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO skills (id, name) VALUES (?,?)")) {
                preparedStatement.setString(1, String.valueOf(skill.getId()));
                preparedStatement.setString(2, skill.getName());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            return null;
        });
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        int updateSkills = JdbcExecutor.transactionalExecute(connection -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE skills SET name =? WHERE id =?")) {
                preparedStatement.setString(1, skill.getName());
                preparedStatement.setLong(2, skill.getId());
                return preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return updateSkills > 0 ? skill : null;
    }

    @Override
    public Skill getById(Long id) {
        final Skill[] skills = {null};

        JdbcExecutor.transactionalExecute(connection -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM skills WHERE id =?")) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                Long id2 = resultSet.getLong("id");
                String name = resultSet.getString("name");
                skills[0] = new Skill(id, name);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
        return skills[0];
    }

    @Override
    public void deleteById(Long id) {
        JdbcExecutor.execute("DELETE FROM skills WHERE id =?", preparedStatement -> {
            try {
                preparedStatement.setLong(1, id);
                if (preparedStatement.executeUpdate() > 0) {
                    System.out.println("Success delete!");
                } else {
                    System.out.println("Label not deleted!");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
    }

    @Override
    public List<Skill> getAll() {
        return JdbcExecutor.execute("SELECT * FROM skills", preparedStatement -> {
            List<Skill> skills = new ArrayList<>();
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    skills.add(new Skill(id, name));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return skills;
        });
    }
    //TODO: to make Jdbc*Impl's - done
}
