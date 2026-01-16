package service;
import model.Status;
import model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class TaskService {
    private List<Task> taskList;

    public TaskService(List<Task> loadedTasks) {
        this.taskList = loadedTasks;
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
        System.out.println("Task added successfully (ID: " + taskList.size());
    }

    // Create a task and add it to the list
    public void createTask(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description value cannot be empty.");
        } else {
            Task taskToCreate = new Task(description);
            taskToCreate.setId(taskList.size() + 1);
            taskToCreate.setStatus(Status.TODO);
            addTask(taskToCreate);
        }

    }

    public void updateTask(int id, String newDescription) {

        // Validate if id exists
        if (taskList.stream()
                .noneMatch(task -> task.getId() == id)){
            throw new IllegalArgumentException("ID Cannot be updated because doesn't exist");
        } else {
            taskList.get(id).setDescription(newDescription);

            // Update timestamp
            taskList.get(id).updateTimeStamp();

        }
    }

    public void deleteTask(int id) {

        // Constraint check: ID doesn't exists in list of tasks
        if (taskList.stream()
                .noneMatch(task -> task.getId() == id)){
            throw new IllegalArgumentException("ID Doesn't exist");
        }
        taskList.remove(id);
        updateTasksId(taskList);
    }

    // Method to update ID's from list once a task has been eliminated
    public void updateTasksId(List<Task> listTask) {

        // Constraint check: Invalid list
        if (listTask.isEmpty()) {
            System.out.println("Can't update task list, is empty!!");
        }

        int count = 1;

        for (Task task : listTask) {
            task.setId(count);
            count++;
        }
    }

    public List<Task> getTasksByStatus (Status status) {

        if (taskList.isEmpty()) {
            return taskList;
        } else {
            return taskList.stream()
                    .filter(t -> t.getStatus() == status) // Filter by status
                    .toList();// Filter by state
        }

    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public boolean taskExists(int id) {

        // Any match of a task in list
        return taskList.stream()
                .anyMatch(task -> task.getId() == id);

    }

    public Task getTask(int id) {
        if (taskExists(id)) {
            return taskList.get(id);
        } else {
            throw new NoSuchElementException("Task not found");
        }
    }

    public void updateStatusOfTask(int id, Status status) {
        if (taskExists(id)) {
            taskList.get(id).setStatus(status);
        } else {
            throw new NoSuchElementException("Task not found, couldn't update the status.");
        }
    }

    public void printTasksByStatus(Optional<Status> status) {

        // Print all elements if no status provided
        if (status.isEmpty()) {
            System.out.println(getTaskList());
        } else {
            System.out.println(getTasksByStatus(status.get()));
        }

    }
}
