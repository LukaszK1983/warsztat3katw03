package pl.coderslab.warsztat3katw03.dao;

import pl.coderslab.warsztat3katw03.db.DbUtil;
import pl.coderslab.warsztat3katw03.model.Solution;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolutionDAO {
    private static final String CREATE_SOLUTION_QUERY = "INSERT INTO solution(exercise_id, user_id, created, updated, description) VALUES (?, ?, ?, ?, ?)";
    private static final String READ_SOLUTION_QUERY = "SELECT exercise_id, user_id, created, updated, description FROM solution where id = ?";
    private static final String READ_DESC_SOLUTION_QUERY = "SELECT description FROM solution where id = ?";
    private static final String UPDATE_SOLUTION_QUERY = "UPDATE solution SET exercise_id = ?, user_id = ?, created = ?, updated = ?, description = ? where id = ?";
    private static final String DELETE_SOLUTION_QUERY = "DELETE FROM solution WHERE id = ?";
    private static final String DELETE_SOLUTION_WITH_EXERCISEID_QUERY = "DELETE FROM solution WHERE exercise_id = ?";
    private static final String DELETE_SOLUTION_WITH_USERID_QUERY = "DELETE FROM solution WHERE user_id = ?";
    private static final String FIND_ALL_SOLUTIONS_QUERY = "SELECT id, exercise_id, user_id, created, updated, description FROM solution";
    private static final String FIND_ALL_USER_SOLUTIONS_QUERY = "SELECT id, exercise_id, created, updated, description FROM solution WHERE user_id = ?";
    private static final String FIND_ALL_EXERCISE_SOLUTIONS_QUERY = "SELECT id, user_id, created, updated, description FROM solution WHERE exercise_id = ? ORDER BY created ASC";
    private static final String FIND_RECENT_SOLUTIONS_QUERY = "SELECT id, exercise_id, user_id, created, updated, description FROM solution ORDER BY created DESC LIMIT ?";
    private static final String FIND_LIMIT_SOLUTIONS = "SELECT s.id, e.title, u.name, s.created FROM solution s JOIN exercise e ON s.exercise_id = e.id JOIN users u ON s.user_id = u.id ORDER BY created DESC LIMIT ?";

    public static SolutionDAO getInstance() {
        return new SolutionDAO();
    }

    public Solution create(Solution solution) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(CREATE_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(++idx, solution.getExerciseId());
            statement.setInt(++idx, solution.getUserId());
            statement.setString(++idx, solution.getCreated());
            statement.setString(++idx, solution.getUpdated());
            statement.setString(++idx, solution.getDescription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                solution.setId(resultSet.getInt(1));
            }
            return solution;
        }
    }

    public Solution read(int solutionID) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(READ_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(++idx, solutionID);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Solution solution = new Solution();
                solution.setId(solutionID);
                solution.setExerciseId(rs.getInt("exercise_id"));
                solution.setUserId(rs.getInt("user_id"));
                solution.setCreated(rs.getString("created"));
                solution.setUpdated(rs.getString("updated"));
                solution.setDescription(rs.getString("description"));
                return solution;
            } else {
                return null;
            }
        }
    }

    public Solution loadById(int solutionID) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(READ_DESC_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(++idx, solutionID);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Solution solution = new Solution();
                solution.setId(solutionID);
                solution.setDescription(rs.getString("description"));
                return solution;
            } else {
                return null;
            }
        }
    }

    public void update(Solution solution) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(UPDATE_SOLUTION_QUERY);
            statement.setInt(++idx, solution.getExerciseId());
            statement.setInt(++idx, solution.getUserId());
            statement.setString(++idx, solution.getCreated());
            statement.setString(++idx, solution.getUpdated());
            statement.setString(++idx, solution.getDescription());
            statement.setInt(++idx, solution.getId());
            statement.executeUpdate();
        }
    }

    public boolean delete(int solutionId) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_QUERY);
            statement.setInt(++idx, solutionId);
            return statement.executeUpdate() == 1;
        }
    }

    public boolean deleteSolutionByExerciseId(int exerciseId) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_WITH_EXERCISEID_QUERY);
            statement.setInt(++idx, exerciseId);
            return statement.executeUpdate() == 1;
        }
    }

    public boolean deleteSolutionByUserId(int userId) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_WITH_USERID_QUERY);
            statement.setInt(++idx, userId);
            return statement.executeUpdate() == 1;
        }
    }

    public List<Solution> findAll() throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            List<Solution> solutions = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setExerciseId(resultSet.getInt("exercise_id"));
                solution.setUserId(resultSet.getInt("user_id"));
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));

                solutions.add(solution);
            }
            return solutions;
        }
    }

    public List<Solution> findAllByUserId(int userID) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            List<Solution> solutions = new ArrayList<>();
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USER_SOLUTIONS_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(++idx, userID);
            ResultSet resultSet = statement.executeQuery();
            Solution solution = new Solution();
            while (resultSet.next()) {
                solution.setId(resultSet.getInt("id"));
                solution.setExerciseId(resultSet.getInt("exercise_id"));
                solution.setUserId(userID);
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
                solutions.add(solution);
            }
            return solutions;
        }
    }

    public List<Solution> findAllByExerciseId(int exerciseID) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            List<Solution> solutions = new ArrayList<>();
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_EXERCISE_SOLUTIONS_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(++idx, exerciseID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setExerciseId(exerciseID);
                solution.setUserId(resultSet.getInt("user_id"));
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));

                solutions.add(solution);
            }
            return solutions;
        }
    }

    public List<Solution> findRecent(int limit) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            final PreparedStatement ps = conn.prepareStatement(FIND_RECENT_SOLUTIONS_QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, limit);

            final ResultSet rs = ps.executeQuery();

            List<Solution> solutions = new ArrayList<>();
            int idx = 0;
            while (rs.next()) {
                int id = rs.getInt(++idx);
                int exercisesId = rs.getInt(++idx);
                int usersId = rs.getInt(++idx);
                String created = rs.getString(++idx);
                String updated = rs.getString(++idx);
                String description = rs.getString(++idx);

                Solution solution = new Solution();
                solution.setId(id);
                solution.setExerciseId(exercisesId);
                solution.setUserId(usersId);
                solution.setCreated(created);
                solution.setUpdated(updated);
                solution.setDescription(description);

                solutions.add(solution);
            }
            return solutions;
        }
    }
}