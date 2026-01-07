package service;
import model.Status;
import model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskService {
    private List<Task> taskList = new ArrayList<>();

    public void addTask(Task newTask) {
        if(taskList.add(newTask)){
            System.out.println("Task added successfully (ID: " + taskList.size());
        } else {
            System.out.println("We couldn't add your new task :( ");
        }
    }


    // Create a task and add it to the list
    public void createTask(String description) {
        Task taskToCreate = new Task(description);
        taskToCreate.setId(taskList.size() + 1);
        taskToCreate.setStatus(Status.TODO);
        addTask(taskToCreate);
    }

    public void updateTask(int id, String newDescription) {

        // Task list empty
        if (taskList.isEmpty()) {
            System.out.println("Task list empty, please add one task.");
        }

        // Verify if the task exists
        if (taskList.contains(id)) {
            Task taskToUpdate = taskList.get(id);
            taskToUpdate.setDescription(newDescription);

            // Update timeStamp
            taskToUpdate.updateTimeStamp();

        } else {
            System.out.println("Task with id [" + id + "] doesn't exist");
        }

        System.out.println("Update for task [ " + id + "]" + " completed");
    }

    public void deleteTask(int id) {
        taskList.remove(id);
        updateTasksId(taskList);
    }

    // Method to update ID's from list once a task has been eliminated
    public void updateTasksId(List<Task> listTask) {

        // Constraint check: Invalid list
        if (listTask.isEmpty() || listTask == null) {
            System.out.println("Can't update task list, is empty!!");
        }

        int count = 1;

        for (Task task : listTask) {
            task.setId(count);
            count++;
        }
    }

    public void updateStatus(String arg, int id) {
        if (arg.isEmpty()) {
            System.out.println("I can't update the status, your argument is empty");
        }

        // Retrive task from list
        Task taskToUpdate = taskList.get(id);

        // Mark task "in progress"
        if (arg == "mark-in-progress") {
            taskToUpdate.updateTimeStamp();
            taskToUpdate.setStatus(Status.IN_PROGRESS);

            System.out.println("Task status updated to: 'In progress'");
        }

        // Mark task "Done"
        if (arg == "mark-done") {
            taskToUpdate.updateTimeStamp();
            taskToUpdate.setStatus(Status.DONE);

            System.out.println("Task status updated to: 'Done':");
        }
    }

    // Listing all tasks
    public void showTasks(Optional<Status> status) {

        if (taskList.isEmpty()) {
            System.out.println("Task list is empty, add a task please");
        }

        // Show all
        if(!(status.isPresent())) {
            for (Task task : taskList) {
                System.out.println(task);
            }
        }

        // Show tasks only with done status
        if(status.isPresent() == status.equals(Status.DONE)) {
            for (Task task : taskList) {
                if(task.getStatus() == Status.DONE) {
                    System.out.println(task);
                }
            }
        }

        // Show tasks with in progress status
        if(status.isPresent() == status.equals(Status.IN_PROGRESS)) {
            for (Task task : taskList) {
                if(task.getStatus() == Status.IN_PROGRESS) {
                    System.out.println(task);
                }
            }
        }

        // Show tasks with todo status

        if(status.isPresent() == status.equals(Status.TODO)) {
            for (Task task : taskList) {
                if(task.getStatus() == Status.TODO) {
                    System.out.println(task);
                }
            }
        }

    }
}
