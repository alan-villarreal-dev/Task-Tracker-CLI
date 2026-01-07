import model.Status;
import service.TaskService;

void main(String[] args) {

    // Constraint check: No option selected
    if (args.length == 0) {
        IO.println("Incompatible option, exiting...");
        System.exit(0);
    }


    String taskToPerform = args[0].toLowerCase();

    TaskService taskService = new TaskService();

    switch (taskToPerform) {
        case "add" -> taskService.createTask(args[1]);

        case "update" -> taskService.updateTask(Integer.parseInt(args[1]), args[2]);

        case "delete" -> taskService.deleteTask(Integer.parseInt(args[1]));

        case "mark-in-progress" ->  taskService.updateStatus("mark-in-progress", Integer.parseInt(args[1]));

        case "mark-done" ->  taskService.updateStatus("mark-done", Integer.parseInt(args[1]));

        case "list" -> {

            if (args.length == 1) {
                taskService.showTasks(Optional.empty());
            }

            if (args[1] == "done") {
                taskService.showTasks(Optional.of(Status.DONE));
            }

            if (args[1] == "todo") {
                taskService.showTasks(Optional.of(Status.TODO));
            }

            if (args[1] == "in-progress") {
                taskService.showTasks(Optional.of(Status.IN_PROGRESS));
            }

        }

        default -> throw new IllegalStateException("Unexpected value: " + taskToPerform);
    }

}
