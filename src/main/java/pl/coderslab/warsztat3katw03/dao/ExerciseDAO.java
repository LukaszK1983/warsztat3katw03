package pl.coderslab.warsztat3katw03.dao;

import pl.coderslab.warsztat3katw03.db.DbUtil;
import pl.coderslab.warsztat3katw03.model.Exercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDAO {
    private static final String CREATE_EXERCISE_QUERY = "INSERT INTO exercise(title, description) VALUES (?, ?)";
    private static final String READ_EXERCISE_QUERY = "SELECT title, description FROM exercise where id = ?";
    private static final String UPDATE_EXERCISE_QUERY = "UPDATE exercise SET title = ?, description = ? where id = ?";
    private static final String DELETE_EXERCISE_QUERY = "DELETE FROM exercise WHERE id = ?";
    private static final String FIND_ALL_EXERCISES_QUERY = "SELECT id, title, description FROM exercise";
    private static final String FIND_ALL_EXERCISES_WITHOUT_SOLUTION_QUERY = "SELECT e.id, title, e.description FROM exercise e LEFT JOIN solution s ON e.id = s.exercise_id WHERE s.exercise_id != (SELECT s.exercise_id FROM solution WHERE s.user_id = ? GROUP BY s.exercise_id) AND s.user_id != ? OR S.exercise_id IS NULL";
    private static final String FIND_EXERCISE_WITH_SOLUTION_OF_USER_QUERY = "SELECT e.id, title, e.description FROM exercise e LEFT JOIN solution s ON e.id = s.exercise_id WHERE s.user_id = ? AND s.exercise_id = ?";
    private static final String FIND_LAST_EXERCISEID_QUERY = "SELECT id, title, description FROM exercise ORDER BY id DESC LIMIT 1";

    public static Exercise create(Exercise exercise) throws SQLException {
        Connection conn = DbUtil.getConnection();
        int idx = 0;
        PreparedStatement statement = conn.prepareStatement(CREATE_EXERCISE_QUERY, Statement.RETURN_GENERATED_KEYS);
        statement.setString(++idx, exercise.getTitle());
        statement.setString(++idx, exercise.getDescription());
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            exercise.setId(resultSet.getInt(1));
        }
        return exercise;
    }

    public static Exercise read(int exerciseID) throws SQLException {
        Connection conn = DbUtil.getConnection();
        int idx = 0;
        PreparedStatement statement = conn.prepareStatement(READ_EXERCISE_QUERY, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(++idx, exerciseID);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            Exercise exercise = new Exercise();
            exercise.setId(exerciseID);
            exercise.setTitle(rs.getString("title"));
            exercise.setDescription(rs.getString("description"));
            return exercise;
        } else {
            Exercise exercise = new Exercise();
            return exercise;
        }
    }

    public static void update(Exercise exercise, int id) throws SQLException {
        Connection conn = DbUtil.getConnection();
        int idx = 0;
        PreparedStatement statement = conn.prepareStatement(UPDATE_EXERCISE_QUERY);
        statement.setString(++idx, exercise.getTitle());
        statement.setString(++idx, exercise.getDescription());
        statement.setInt(++idx, exercise.getId());
        statement.executeUpdate();
    }

    public static boolean delete(int exerciseId) throws SQLException {
        Connection conn = DbUtil.getConnection();
        int idx = 0;
        PreparedStatement statement = conn.prepareStatement(DELETE_EXERCISE_QUERY);
        statement.setInt(++idx, exerciseId);
        return statement.executeUpdate() == 1;
    }

    public static List<Exercise> findAll() throws SQLException {
        Connection conn = DbUtil.getConnection();
        List<Exercise> exercises = new ArrayList<>();
        PreparedStatement statement = conn.prepareStatement(FIND_ALL_EXERCISES_QUERY);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Exercise exercise = new Exercise();
            exercise.setId(resultSet.getInt("id"));
            exercise.setTitle(resultSet.getString("title"));
            exercise.setDescription(resultSet.getString("description"));

            exercises.add(exercise);
        }
        return exercises;
    }

    public static Exercise getLastExerciseId() throws SQLException {
        Connection conn = DbUtil.getConnection();
        PreparedStatement statement = conn.prepareStatement(FIND_LAST_EXERCISEID_QUERY);
        ResultSet resultSet = statement.executeQuery();
        Exercise exercise = new Exercise();
        while (resultSet.next()) {
            exercise.setId(resultSet.getInt("id"));
            exercise.setTitle(resultSet.getString("title"));
            exercise.setDescription(resultSet.getString("description"));
        }
        return exercise;
    }

    public List<Exercise> findExercisesWithoutSolution(int userID) throws SQLException {
        Connection conn = DbUtil.getConnection();
        List<Exercise> exercises = new ArrayList<>();
        int idx = 0;
        PreparedStatement statement = conn.prepareStatement(FIND_ALL_EXERCISES_WITHOUT_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(++idx, userID);
        statement.setInt(++idx, userID);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Exercise exercise = new Exercise();
            exercise.setId(resultSet.getInt("id"));
            exercise.setTitle(resultSet.getString("title"));
            exercise.setDescription(resultSet.getString("description"));

            exercises.add(exercise);
        }
        return exercises;
    }

    public boolean findExercisesWithSolutionOfUser(int userID, int exerciseID) throws SQLException {
        Connection conn = DbUtil.getConnection();
        int idx = 0;
        PreparedStatement statement = conn.prepareStatement(FIND_EXERCISE_WITH_SOLUTION_OF_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(++idx, userID);
        statement.setInt(++idx, exerciseID);
        ResultSet rs = statement.executeQuery();

        return rs.next();
    }
}