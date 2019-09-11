package pl.coderslab.warsztat3katw03.model;

import java.time.LocalDateTime;

public class Solution {
    private int id;
    private int exerciseId;
    private int userId;
    private String created;
    private String updated;
    private String description;
    private String title;
    private String authorName;

    public Solution() {
    }

    public Solution(int exerciseId, int userId, String created, String updated, String description) {
        this.exerciseId = exerciseId;
        this.userId = userId;
        this.created = created;
        this.updated = updated;
        this.description = description;
    }

    public Solution(int id, int exerciseId, int userId, String created, String updated, String description) {
        this.id = id;
        this.exerciseId = exerciseId;
        this.userId = userId;
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

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
                ", exerciseId=" + exerciseId +
                ", userId=" + userId +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}
