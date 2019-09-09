package pl.coderslab.warsztat3katw03.model;

import java.time.LocalDateTime;

public class Solution {
    private int id;
    private int exercise_id;
    private int user_id;
    private String created;
    private String updated;
    private String description;
    private String title;
    private String authorName;

    public Solution() {
    }

    public Solution(int exercise_id, int user_id, String created, String updated, String description) {
        this.exercise_id = exercise_id;
        this.user_id = user_id;
        this.created = created;
        this.updated = updated;
        this.description = description;
    }

    public Solution(int id, int exercise_id, int user_id, String created, String updated, String description) {
        this.id = id;
        this.exercise_id = exercise_id;
        this.user_id = user_id;
        this.created = created;
        this.updated = updated;
        this.description = description;
    }

    public Solution(int id, String title, String created) {
        this.id = id;
        this.created = created;
        this.title = title;
    }

    public Solution(int id, String title, String authorName, String created) {
        this.id = id;
        this.title = title;
        this.authorName = authorName;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", exercise_id=" + exercise_id +
                ", user_id=" + user_id +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
