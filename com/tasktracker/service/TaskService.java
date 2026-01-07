package service;
import model.Status;
import model.Task;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private List<Task> taskList = new ArrayList<>();

    public void addTask(Task newTask) {
        if(taskList.add(newTask)){
            System.out.println("Task added successfully (ID: " + taskList.size());
        } else {
            System.out.println("We couldn't add your new task :( ");
        }
    }

    public void updateTask(int id, Task task, String newDescription) {

        // Task list empty
        if (taskList.isEmpty()) {
            System.out.println("Task list empty, please add one task.");
        }

        // Verify if the task exists
        if (taskList.contains(task)) {
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
    public void listAllTasks() {

        if (taskList.isEmpty()) {
            System.out.println("Task list is empty, add a task please");
        } else {
            for (Task task : taskList) {
                System.out.println(task);
            }
        }

    }
}
