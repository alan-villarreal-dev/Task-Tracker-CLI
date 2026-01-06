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
}
