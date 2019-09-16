package pl.coderslab.warsztat3katw03.model;

public class Group {
    private int id;
    private int userId;
    private String name;

    public Group() {
    }

    public Group(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public Group(String name) {
        this.name = name;
    }

    public Group(int id, int userId, String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }
}
