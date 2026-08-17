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
Adds a task that must be completed by a specific date or time.

* **Format**: `deadline <description> /by <date/time>`
* **Example**:
  ```text
  deadline return book /by Sunday
  ```
* **Expected Output**:
  ```text
      ____________________________________________________________
       Got it. I've added this task:
         [D][ ] return book (by: Sunday)
       Now you have 2 tasks in the list.
      ____________________________________________________________
  ```

---

### 3. Adding an Event Task: `event`
Adds an event that occurs over a specific time interval.

* **Format**: `event <description> /from <start> /to <end>`
* **Example**:
  ```text
  event project meeting /from Mon 2pm /to 4pm
  ```
* **Expected Output**:
  ```text
      ____________________________________________________________
       Got it. I've added this task:
         [E][ ] project meeting (from: Mon 2pm to: 4pm)
       Now you have 3 tasks in the list.
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

### 8. Exiting the Application: `bye`
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