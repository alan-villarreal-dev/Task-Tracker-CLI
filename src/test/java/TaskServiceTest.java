import model.Status;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.TaskService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {
    private TaskService taskService;

    @BeforeEach
    void setupTaskService() {
        // This is executed before each test
        taskService = new TaskService(new ArrayList<>());
    }

    @Test
    void shouldCreateTaskWithCorrectDefaults() {
        // Arrange: Prepare the environment
        // TaskService taskService = new TaskService(new ArrayList<>());

        // Act: Execute the action to create a task
        taskService.createTask("Go to the mall");

        // Assert: Verify if we have our default values
        List<Task> tasks = taskService.getTaskList();

        assertEquals(1, tasks.size());
        assertEquals(Status.TODO, tasks.getFirst().getStatus());
        assertEquals(1, tasks.getFirst().getId());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsInvalid() {
        // Arrange
        // TaskService taskService = new TaskService(new ArrayList<>());

        // Act: Capture the exception
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            taskService.createTask("");
        });

        // Assert: Extract the message and compare it
        String errorMessage = exception.getMessage();
        String expectedMessage = "Description value cannot be empty.";

        assertEquals(expectedMessage, errorMessage);
    }

    @Test
    void shouldThrowExceptionWhenIdDoesNotExist() {
        // Arrange : List has 1 task
        taskService.createTask("Task test");

        // Act & Assert: Verify that searching a wrong id throw the exception.
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> taskService.deleteTask(3));
        assertEquals("ID Doesn't exist", exception.getMessage());
    }

    @Test
    void shouldUpdateStatusOfTask(){
        // Arrange: Add 1 task
        taskService.createTask("Status test Task");
        Status newStatus = Status.IN_PROGRESS;
        int id = 1;

        // Act: Extract the task to update
        taskService.updateStatusOfTask(id, Status.IN_PROGRESS);

        // Assert: Status reflected after updating it
        assertAll("Status update verification",
                () -> assertEquals(Status.IN_PROGRESS, taskService.getTask(id).getStatus()),
                () -> assertEquals(id, taskService.getTask(id).getId(), "ID Should be the same"));

    }

    @Test
    void shouldFilterTaskByStatusDone() {
        // Arrange: Prepare tasks
        taskService.createTask("Task 1");
        taskService.createTask("Task 2");
        taskService.createTask("Task 3");

        // Change status of task 3 to DONE (ID 3)
        List<Task> completedTasks = taskService.getTasksByStatus(Status.DONE);


        assertAll("Test of DONE filter",
                () -> assertEquals(1, completedTasks.size()),
                () -> assertEquals(Status.DONE, completedTasks.getFirst().getStatus()),
                () -> assertEquals("Task 3", completedTasks.getFirst().getDescription()));
    }

    @Test
    void shouldReturnEmptyListWhenNoTasksMatchStatus() {
        taskService.createTask("Test task 1.1");
        taskService.createTask("Test task 1.2");


    }
}
