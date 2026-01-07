package model;

import java.time.LocalDateTime;

public class Task {

    private int id;
    private String description;
    private Status status = Status.TODO;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(int id,
                String description) {

        this.id = id;
        this.description = description;
        createdAt = LocalDateTime.now();
    }

    public int getId() {
         return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void updateTimeStamp() {
        this.updatedAt = LocalDateTime.now();
    }

    public void setStatus(Status statusToUpdate) {
        this.status = statusToUpdate;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
