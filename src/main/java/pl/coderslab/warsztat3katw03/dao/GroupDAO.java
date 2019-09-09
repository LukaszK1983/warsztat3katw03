package pl.coderslab.warsztat3katw03.dao;

import pl.coderslab.warsztat3katw03.db.DbUtil;
import pl.coderslab.warsztat3katw03.model.Group;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDAO {
    private static final String CREATE_GROUP_QUERY = "INSERT INTO user_group(user_id, name) VALUES (?, ?)";
    private static final String CREATE_NEW_GROUP_QUERY = "INSERT INTO user_group(id, group_id, name) VALUES (?, ?, ?)";
    private static final String READ_GROUP_QUERY = "SELECT id, group_id, user_id, name FROM user_group where id = ?";
    private static final String READ_ONE_GROUP_QUERY = "SELECT user_id, name FROM user_group where group_id = ?";
    private static final String UPDATE_GROUP_QUERY = "UPDATE user_group SET user_id = ?, name = ? where id = ?";
    private static final String UPDATE_GROUP_NAME_QUERY = "UPDATE user_group SET name = ? where group_id = ?";
    private static final String DELETE_GROUP_QUERY = "DELETE FROM user_group WHERE group_id = ?";
    private static final String FIND_LAST_GROUPID_QUERY = "SELECT * FROM user_group ORDER BY group_id DESC LIMIT 1 ";
    private static final String FIND_ALL_GROUPS_QUERY = "SELECT group_id, name FROM user_group GROUP BY name ORDER BY name ASC";

    public Group create(Group group) {
        try  {
            Connection conn = DbUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(CREATE_GROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, group.getUser_id());
            statement.setString(2, group.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                group.setId(resultSet.getInt(1));
            }
            return group;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Group addGroup(Group group) {
        try  {
            Connection conn = DbUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(CREATE_NEW_GROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, group.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                group.setId(resultSet.getInt(1));
                group.setGroup_id(resultSet.getInt(2));
                group.setName(resultSet.getString(3));
            }
            return group;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Group read(int groupID) {
        try {
            Connection conn = DbUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(READ_GROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, groupID);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Group group = new Group();
                group.setId(groupID);
                group.setGroup_id(rs.getInt("group_id"));
                group.setUser_id(rs.getInt("user_id"));
                group.setName(rs.getString("name"));
                return group;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Group getLastGroupId() {
        try {
            Connection conn = DbUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(FIND_LAST_GROUPID_QUERY, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Group group = new Group();
                group.setId(rs.getInt("id"));
                group.setGroup_id(rs.getInt("group_id"));
                group.setUser_id(rs.getInt("user_id"));
                group.setName(rs.getString("name"));
                return group;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Group loadAllByGrupId(int groupID) {
        try {
            Connection conn = DbUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(READ_ONE_GROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, groupID);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Group group = new Group();
                group.setId(groupID);
                group.setUser_id(rs.getInt("user_id"));
                group.setName(rs.getString("name"));
                return group;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void update(Group group) {
        try {
            Connection conn = DbUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(UPDATE_GROUP_QUERY);
            statement.setInt(1, group.getUser_id());
            statement.setString(2, group.getName());
            statement.setInt(3, group.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateName(Group group, int group_id) {
        try {
            Connection conn = DbUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(UPDATE_GROUP_NAME_QUERY);
            statement.setString(1, group.getName());
            statement.setInt(2, group_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean delete(int groupId) {
        try {
            Connection conn = DbUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(DELETE_GROUP_QUERY);
            statement.setInt(1, groupId);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Group> findAll() {
        try {
            Connection conn = DbUtil.getConnection();
            List<Group> groups = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_GROUPS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Group group = new Group();
                group.setGroup_id(resultSet.getInt("group_id"));
                group.setName(resultSet.getString("name"));

                groups.add(group);
            }
            return groups;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
