# Task Tracker CLI
Java Command Linke Interface application to track tasks, manage their statuses and persist data using JSON-buil as a solution for:

[Backend Roadmap](https://roadmap.sh/projects/task-tracker)

## Features

> ***CRUD Operations:*** Add, Update and Delete tasks.
> 
>  ***Status Managment:*** Mark tasks as 'todo', 'in-progress' or 'done'.
>
> ***Filtering:*** List all tasks or filter them specifically by their current status
>
> ***Data persistance:*** Automatically saves your tasks to a 'tasks.json' file.
>
> ***Dependencies:*** GSON Library added to handle serialization and deserialization of task objects.

## Prerequisites

- ***Java SDK:*** 24 or higher recommended
- ***Build tool:*** Maven (standard for this structure).

## Installation & Setup

1. Clone the repository:

```bash
git clone https://github.com/alan-villarreal-dev/Task-Tracker-CLI.git
cd Task-Tracker-CLI
```

2. Build the project:
```bash
mvn clean package
```

## Usage

Run the program using the following commands:

1. Adding a new task
```bash
java -jar target/task-tracker-cli.jar add "Buy Groceries"
# Output: Task added successfully (ID:1)
```

2. Updating a task
```bash
java -jar target/task-tracker-cli.jar update 1 "Buy groceries and cook dinner"
```

3. Deleting a task
```bash
java -jar target/task-tracker-cli.jar delete 1
```

4. Changing tasks status:
```bash
java -jar target/task-tracker-cli.jar mark-in-progress 1
java -jar target/task-tracker-cli.jar mark-done 1
```

5. Listing tasks
```bash
# List all tasks
java -jar target/task-tracker-cli.jar list

# List by status
java -jar target/task-tracker-cli.jar list done
java -jar target/task-tracker-cli.jar list todo
java -jar target/task-tracker-cli.jar list in-progress
```

## ***Project Structure*** 

Task-Tracker-CLI/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/tasktracker/
│   │           ├── Main.java        # Entry point & CLI logic
│   │           ├── Task.java        # Task Model (Record/Class)
│   │           └── TaskManager.java # Logic for File I/O and CRUD
├── tasks.json                       # Data storage (auto-generated)
└── pom.xml                          # Maven configuration

# License
This project is licensed under the MIT License - see the LICENSE file for details.






