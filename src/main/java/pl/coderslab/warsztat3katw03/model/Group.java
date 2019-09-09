package pl.coderslab.warsztat3katw03.model;

public class Group {
    private int id;
    private int group_id;
    private int user_id;
    private String name;

    public Group() {
    }

    public Group(int user_id, String name) {
        this.user_id = user_id;
        this.name = name;
    }

    public Group(String name) {
        this.name = name;
    }

    public Group(int group_id) {
        this.group_id = group_id;
    }

    public Group(int id, int group_id, String name) {
        this.id = id;
        this.group_id = group_id;
        this.name = name;
    }

    public Group(int id, int group_id, int user_id, String name) {
        this.id = id;
        this.group_id = group_id;
        this.user_id = user_id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", group_id=" + group_id +
                ", user_id=" + user_id +
                ", name='" + name + '\'' +
                '}';
    }
}
