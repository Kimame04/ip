# UI Test Plan for Jiji Chatbot

This document contains test cases for automated UI and CLI verification of the Jiji chatbot application.

---

## Test Case 1: Startup and Immediate Exit
- **Aim**: Verify that the application displays the startup banner, greeting message, and terminates gracefully when given the `bye` command.
- **Inputs**:
```text
bye
```
- **Expected Output**:
```text
    ____________________________________________________________
         _     _          _     _ 
        | |   (_)        (_)   (_)
        | |    _          _     _ 
     _  | |   | |        | |   | |
    | |_| |   | |     _  | |   | |
     \___/    |_|    | |_| |   |_|
                      \___/       

     Hello! I'm Jiji.
     What can I do for you?
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

---

## Test Case 2: Add Todo, Deadline, Event, and List
- **Aim**: Verify adding different task types (Todo, Deadline, Event) and viewing them via the `list` command.
- **Inputs**:
```text
todo read book
deadline return book /by Sunday
event project meeting /from Mon 2pm /to 4pm
list
bye
```
- **Expected Output**:
```text
    ____________________________________________________________
         _     _          _     _ 
        | |   (_)        (_)   (_)
        | |    _          _     _ 
     _  | |   | |        | |   | |
    | |_| |   | |     _  | |   | |
     \___/    |_|    | |_| |   |_|
                      \___/       

     Hello! I'm Jiji.
     What can I do for you?
    ____________________________________________________________
    ____________________________________________________________
     Got it. I've added this task:
       [T][ ] read book
     Now you have 1 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Got it. I've added this task:
       [D][ ] return book (by: Sunday)
     Now you have 2 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Got it. I've added this task:
       [E][ ] project meeting (from: Mon 2pm to: 4pm)
     Now you have 3 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] read book
     2.[D][ ] return book (by: Sunday)
     3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

---

## Test Case 3: Mark and Unmark Polymorphic Tasks
- **Aim**: Verify marking a Deadline task as done and unmarking an Event task.
- **Inputs**:
```text
todo read book
deadline return book /by Sunday
event project meeting /from Mon 2pm /to 4pm
mark 2
list
unmark 2
list
bye
```
- **Expected Output**:
```text
    ____________________________________________________________
         _     _          _     _ 
        | |   (_)        (_)   (_)
        | |    _          _     _ 
     _  | |   | |        | |   | |
    | |_| |   | |     _  | |   | |
     \___/    |_|    | |_| |   |_|
                      \___/       

     Hello! I'm Jiji.
     What can I do for you?
    ____________________________________________________________
    ____________________________________________________________
     Got it. I've added this task:
       [T][ ] read book
     Now you have 1 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Got it. I've added this task:
       [D][ ] return book (by: Sunday)
     Now you have 2 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Got it. I've added this task:
       [E][ ] project meeting (from: Mon 2pm to: 4pm)
     Now you have 3 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Nice! I've marked this task as done:
       [D][X] return book (by: Sunday)
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] read book
     2.[D][X] return book (by: Sunday)
     3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
    ____________________________________________________________
    ____________________________________________________________
     OK, I've marked this task as not done yet:
       [D][ ] return book (by: Sunday)
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] read book
     2.[D][ ] return book (by: Sunday)
     3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```
