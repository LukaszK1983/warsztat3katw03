package pl.coderslab.warsztat3katw03.dao;

import pl.coderslab.warsztat3katw03.db.DbUtil;
import pl.coderslab.warsztat3katw03.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final String CREATE_USER_QUERY = "INSERT INTO users(name, email, password, passwordSalt) VALUES (?, ?, ?, ?)";
    private static final String CREATE_USER_QUERY2 = "INSERT INTO users(name, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY = "SELECT name, email, password, passwordSalt FROM users WHERE id = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET name = ?, email = ?, password = ?, passwordSalt = ? WHERE id = ?";
    private static final String UPDATE_USER_QUERY2 = "UPDATE users SET name = ?, email = ?, password = ? WHERE id = ?";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";
    private static final String FIND_ALL_USERS_QUERY = "SELECT id, name, email, password, passwordSalt FROM users";
    //    private static final String FIND_ALL_GROUP_USERS_QUERY = "SELECT u.id, u.name, email, password, passwordSalt FROM users u JOIN user_group ug ON u.id = ug.user_id WHERE ug.id = ?";
    private static final String FIND_ALL_GROUP_USERS_QUERY = "SELECT u.id, u.name FROM users u JOIN user_group ug ON u.id = ug.user_id WHERE ug.group_id = ?";
    private static final String FIND_LAST_USERID_QUERY = "SELECT id, name, email, password FROM users ORDER BY id DESC LIMIT 1";

    public static UserDAO getInstance() {
        return new UserDAO();
    }

    public User create(User user) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(++idx, user.getName());
            statement.setString(++idx, user.getEmail());
            statement.setString(++idx, user.getPassword());
            statement.setString(++idx, user.getPasswordSalt());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        }
    }

    public User createUser(User user) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(CREATE_USER_QUERY2, Statement.RETURN_GENERATED_KEYS);
            statement.setString(++idx, user.getName());
            statement.setString(++idx, user.getEmail());
            statement.setString(++idx, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        }
    }

    public User read(int userID) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(++idx, userID);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(userID);
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPasswordSalt(rs.getString("passwordSalt"));
                return user;
            } else {
                User user = new User();
                return user;
            }
        }
    }

    public void update(User user) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(++idx, user.getName());
            statement.setString(++idx, user.getEmail());
            statement.setString(++idx, user.getPassword());
            statement.setString(++idx, user.getPasswordSalt());
            statement.setInt(++idx, user.getId());
            statement.executeUpdate();
        }
    }

    public void updateUser(User user, int id) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY2);
            statement.setString(++idx, user.getName());
            statement.setString(++idx, user.getEmail());
            statement.setString(++idx, user.getPassword());
            statement.setInt(++idx, user.getId());
            statement.executeUpdate();
        }
    }

//    public boolean delete(int userId) throws SQLException {
//        Connection conn = DbUtil.getConnection();
//        int idx = 0;
//        PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
//        statement.setInt(++idx, userId);
//        return statement.executeUpdate() == 1;
//    }

    public void delete(int userId) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        }
    }

    public List<User> findAll() throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            List<User> users = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = setUser(resultSet);
                users.add(user);
            }
            return users;
        }
    }

    public User getLastUserId() throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_LAST_USERID_QUERY);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
            return user;
        }
    }

    public List<User> findAllByGroupId(int groupID) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            List<User> users = new ArrayList<>();
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_GROUP_USERS_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(++idx, groupID);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                users.add(user);
            }
            return users;
        }
    }

//    public static void closeConnection(Connection connection) {
//        if (connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                catchCode(e);
//            }
//        }
//    }

//    public static void catchCode(SQLException e) {
//        e.getMessage();
//        e.printStackTrace();
//    }

    public User setUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setPasswordSalt(rs.getString("passwordSalt"));
        return user;
    }
}