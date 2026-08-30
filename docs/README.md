# Jiji User Guide

**Jiji** is a lightweight, friendly command-line personal assistant chatbot that helps you organize and manage tasks (ToDos, Deadlines, and Events) with feline charm ₍^._.^₎ 𐒡.

---

## Features Summary

| Command | Syntax | Description |
| :--- | :--- | :--- |
| **`todo`** | `todo <description>` | Adds a to-do task. |
| **`deadline`** | `deadline <description> /by <time>` | Adds a task with a deadline. |
| **`event`** | `event <description> /from <start> /to <end>` | Adds an event with start and end times. |
| **`list`** | `list` | Lists all tasks with their type, status, and indices. |
| **`mark`** | `mark <task_number>` | Marks a task as completed (`[X]`). |
| **`unmark`** | `unmark <task_number>` | Marks a task as not completed (`[ ]`). |
| **`delete`** | `delete <task_number>` | Removes a task from the list and re-indexes remaining tasks. |
| **`find`** | `find <keyword>` | Finds tasks matching a search keyword. |
| **`bye`** | `bye` | Exits the Jiji application. |

---

## Command Details

### 1. Adding a ToDo Task: `todo`
Adds a task without any date or time constraints.

* **Format**: `todo <description>`
* **Example**:
  ```text
  todo read book
  ```
* **Expected Output**:
  ```text
      ____________________________________________________________
       Got it. I've added this task:
         [T][ ] read book
       Now you have 1 tasks in the list.
      ____________________________________________________________
  ```

---

### 2. Adding a Deadline Task: `deadline`
Adds a task that must be completed by a specific date or time. Jiji understands standard date and time formats (e.g. `yyyy-MM-dd`, `d/M/yyyy HHmm`) and displays them in a friendly format (`MMM dd yyyy, h:mma`).

* **Format**: `deadline <description> /by <date/time>`
* **Accepted Formats**:
  * Date: `yyyy-MM-dd` (e.g. `2026-08-30`), `d/M/yyyy` (e.g. `2/12/2026`)
  * Date-Time: `yyyy-MM-dd HHmm` (e.g. `2026-08-30 1800`), `d/M/yyyy HHmm` (e.g. `2/12/2026 1800`)
  * Text descriptions (e.g. `Sunday`) are also supported.
* **Example (Date)**:
  ```text
  deadline return book /by 2026-08-30
  ```
* **Expected Output**:
  ```text
      ____________________________________________________________
       Got it. I've added this task:
         [D][ ] return book (by: Aug 30 2026)
       Now you have 2 tasks in the list.
      ____________________________________________________________
  ```
* **Example (Date and Time)**:
  ```text
  deadline submit project /by 2/12/2026 1800
  ```
* **Expected Output**:
  ```text
      ____________________________________________________________
       Got it. I've added this task:
         [D][ ] submit project (by: Dec 02 2026, 6:00PM)
       Now you have 3 tasks in the list.
      ____________________________________________________________
  ```

---

### 3. Adding an Event Task: `event`
Adds an event that occurs over a specific time interval. Both dates and times are formatted for clarity.

* **Format**: `event <description> /from <start> /to <end>`
* **Example**:
  ```text
  event orientation camp /from 2026-09-01 0900 /to 2026-09-03 1700
  ```
* **Expected Output**:
  ```text
      ____________________________________________________________
       Got it. I've added this task:
         [E][ ] orientation camp (from: Sep 01 2026, 9:00AM to: Sep 03 2026, 5:00PM)
       Now you have 4 tasks in the list.
      ____________________________________________________________
  ```

---

### 4. Listing All Tasks: `list`
Displays all current tasks along with their 1-based index, type tag (`[T]`, `[D]`, `[E]`), completion status (`[ ]` or `[X]`), and any associated times.

* **Format**: `list`
* **Expected Output**:
  ```text
      ____________________________________________________________
       Here are the tasks in your list:
       1.[T][ ] read book
       2.[D][ ] return book (by: Sunday)
       3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
      ____________________________________________________________
  ```

---

### 5. Marking a Task as Done: `mark`
Marks the task at the specified 1-based index as completed.

* **Format**: `mark <task_number>`
* **Example**:
  ```text
  mark 2
  ```
* **Expected Output**:
  ```text
      ____________________________________________________________
       Nice! I've marked this task as done:
         [D][X] return book (by: Sunday)
      ____________________________________________________________
  ```

---

### 6. Marking a Task as Not Done: `unmark`
Marks a previously completed task back as incomplete.

* **Format**: `unmark <task_number>`
* **Example**:
  ```text
  unmark 2
  ```
* **Expected Output**:
  ```text
      ____________________________________________________________
       OK, I've marked this task as not done yet:
         [D][ ] return book (by: Sunday)
      ____________________________________________________________
  ```

---

### 7. Deleting a Task: `delete`
Removes a task from the list at the specified 1-based index and automatically shifts the indices of subsequent tasks.

* **Format**: `delete <task_number>`
* **Example**:
  ```text
  delete 2
  ```
* **Expected Output**:
  ```text
      ____________________________________________________________
       Noted. I've removed this task:
         [D][ ] return book (by: Sunday)
       Now you have 2 tasks in the list.
      ____________________________________________________________
  ```

---

### 8. Finding Tasks by Keyword: `find`
Searches for tasks whose descriptions contain the given keyword (case-insensitive) and lists all matches.

* **Format**: `find <keyword>`
* **Example**:
  ```text
  find book
  ```
* **Expected Output**:
  ```text
      ____________________________________________________________
       Here are the matching tasks in your list:
       1.[T][ ] read book
       2.[D][ ] return book (by: Sunday)
      ____________________________________________________________
  ```

---

### 9. Exiting the Application: `bye`
Exits Jiji with a farewell message.

* **Format**: `bye`
* **Expected Output**:
  ```text
      ____________________________________________________________
       Bye. Hope to see you again soon!
      ____________________________________________________________
  ```

---

## Error Handling & Feline Feedback

Jiji validates your input and provides helpful, friendly error messages using custom feline emoticons:

* **Missing Task Description**:
  ```text
  todo
  ```
  ```text
      ____________________________________________________________
       OOPS! ₍^._.^₎ 𐒡 The description of a todo cannot be empty.
      ____________________________________________________________
  ```

* **Missing Deadline/Event Parameter**:
  ```text
  deadline return book
  ```
  ```text
      ____________________________________________________________
       OOPS! ^๑_๑^ ੭ A deadline task requires a description and a '/by' time.
      ____________________________________________________________
  ```

* **Invalid or Out-of-Bounds Task Number**:
  ```text
  mark 10
  ```
  ```text
      ____________________________________________________________
       OOPS! ₍^› ꘍ ‹ ^₎⟆ Please provide a valid task number.
      ____________________________________________________________
  ```

* **Unrecognized Command**:
  ```text
  blah
  ```
  ```text
      ____________________________________________________________
       OOPS! ₍^› ꘍ ‹ ^₎⟆ I'm sorry, but I don't know what that means.
      ____________________________________________________________
  ```

---

## Data Persistence

Jiji automatically persists your task list so you never lose track of your items:

* **Automatic Saving**: Every time you add, delete, mark, or unmark a task, Jiji immediately updates the storage file on disk.
* **Storage Location**: Tasks are stored in `data/jiji.txt` relative to the application's root directory. The directory and file are created automatically if they do not exist.
* **Automatic Loading**: When Jiji starts up, it automatically reads `data/jiji.txt` and populates your task list.
* **Storage Format**: Pipe-separated text format:
  ```text
  T | 1 | read book
  D | 0 | return book | Sunday
  E | 0 | project meeting | Mon 2pm | 4pm
  ```

---

## Building and Running with Gradle

Jiji uses Gradle for build automation, testing, and packaging:

* **Run Jiji interactively**:
  ```bash
  ./gradlew run --console=plain --quiet
  ```
* **Run automated unit tests**:
  ```bash
  ./gradlew test
  ```
* **Run Checkstyle code style analysis**:
  ```bash
  ./gradlew checkstyleMain checkstyleTest
  ```
* **Build project & assemble distribution**:
  ```bash
  ./gradlew build
  ```

---

## Standalone Executable JAR

You can package and distribute Jiji as a standalone executable fat JAR:

### 1. Generating the JAR File
Run the following Gradle task:
```bash
./gradlew shadowJar
```
The generated executable JAR will be located at:
```text
build/libs/jiji.jar
```

### 2. Running the JAR File
You can run the JAR file on any system with Java 25 installed:
1. Copy `jiji.jar` into your desired working directory.
2. Open a terminal in that folder and run:
   ```bash
   java -jar "jiji.jar"
   ```
3. Jiji will automatically create and persist tasks in `data/jiji.txt` in that folder.