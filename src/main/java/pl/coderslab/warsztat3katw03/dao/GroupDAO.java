package pl.coderslab.warsztat3katw03.dao;

import pl.coderslab.warsztat3katw03.db.DbUtil;
import pl.coderslab.warsztat3katw03.model.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDAO {
//    private static final String CREATE_GROUP_QUERY = "INSERT INTO user_group(user_id, name) VALUES (?, ?)";
    private static final String CREATE_NEW_GROUP_QUERY = "INSERT INTO user_group(name) VALUES (?)";
    private static final String READ_GROUP_QUERY = "SELECT id, user_id, name FROM user_group where id = ?";
    private static final String UPDATE_GROUP_QUERY = "UPDATE user_group SET user_id = ?, name = ? where id = ?";
    private static final String UPDATE_GROUP_NAME_QUERY = "UPDATE user_group SET name = ? where id = ?";
    private static final String DELETE_GROUP_QUERY = "DELETE FROM user_group WHERE id = ?";
    private static final String DELETE_GROUP_BY_USERID_QUERY = "DELETE FROM user_group WHERE user_id = ?";
    private static final String FIND_ALL_GROUPS_QUERY = "SELECT id, name FROM user_group GROUP BY name ORDER BY name ASC";

    public static GroupDAO getInstance() {
        return new GroupDAO();
    }
//    public Group create(Group group) throws SQLException {
//        Connection conn = DbUtil.getConnection();
//        int idx = 0;
//        PreparedStatement statement = conn.prepareStatement(CREATE_GROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
//        statement.setInt(++idx, group.getUserId());
//        statement.setString(++idx, group.getName());
//        statement.executeUpdate();
//        ResultSet resultSet = statement.getGeneratedKeys();
//        if (resultSet.next()) {
//            group.setId(resultSet.getInt(1));
//        }
//        return group;
//    }

    public Group addGroup(Group group) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(CREATE_NEW_GROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(++idx, group.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            int idx2 = 0;
            if (resultSet.next()) {
                group.setName(resultSet.getString(++idx2));
            }
            return group;
        }
    }

    public Group read(int id) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(READ_GROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(++idx, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Group group = new Group();
                group.setId(id);
                group.setUserId(rs.getInt("user_id"));
                group.setName(rs.getString("name"));
                return group;
            } else {
                return null;
            }
        }
    }

    public void update(Group group) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(UPDATE_GROUP_QUERY);
            statement.setInt(++idx, group.getUserId());
            statement.setString(++idx, group.getName());
            statement.setInt(++idx, group.getId());
            statement.executeUpdate();
        }
    }

    public void updateName(Group group, int id) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(UPDATE_GROUP_NAME_QUERY);
            statement.setString(++idx, group.getName());
            statement.setInt(++idx, id);
            statement.executeUpdate();
        }
    }

    public boolean delete(int id) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(DELETE_GROUP_QUERY);
            statement.setInt(++idx, id);
            return statement.executeUpdate() == 1;
        }
    }

    public boolean deleteByUserId(int userId) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(DELETE_GROUP_BY_USERID_QUERY);
            statement.setInt(++idx, userId);
            return statement.executeUpdate() == 1;
        }
    }

    public List<Group> findAll() throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            List<Group> groups = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_GROUPS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                groups.add(group);
            }
            return groups;
        }
    }
}