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

---

## Test Case 4: Isolated Error Handling with Custom Cat Messages
- **Aim**: Verify that invalid commands, empty task descriptions, and invalid task indices produce friendly feline error messages without crashing the application.
- **Inputs**:
```text
blah
todo
deadline return book
event project meeting /from Mon 2pm
mark 10
todo borrow book
mark abc
mark
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
     OOPS! ₍^› ꘍ ‹ ^₎⟆ I'm sorry, but I don't know what that means.
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ₍^._.^₎ 𐒡 The description of a todo cannot be empty.
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ^๑_๑^ ੭ A deadline task requires a description and a '/by' time.
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ^๑_๑^ ੭ An event task requires a description, '/from', and '/to' times.
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ₍^› ꘍ ‹ ^₎⟆ Please provide a valid task number.
    ____________________________________________________________
    ____________________________________________________________
     Got it. I've added this task:
       [T][ ] borrow book
     Now you have 1 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ₍^› ꘍ ‹ ^₎⟆ Please provide a valid task number.
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ₍^› ꘍ ‹ ^₎⟆ Please specify a task number to mark.
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

---

## Test Case 5: Interleaved Invalid Inputs and State Integrity
- **Aim**: Verify that invalid commands, malformed parameters, and out-of-bounds operations do not corrupt the task list, alter indices, or leave partial state.
- **Inputs**:
```text
list
mark 1
unmark 1
todo read book
todo   
deadline
deadline /by Sunday
deadline return book /by 
event
event meeting /from 2pm
event meeting /to 4pm
event /from 2pm /to 4pm
event meeting /from  /to 4pm
list
deadline return book /by Sunday
event project meeting /from Mon 2pm /to 4pm
list
mark 0
mark -1
mark 4
mark abc
mark
unmark 0
unmark 99
unmark xyz
unmark
list
mark 2
unknown_cmd
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
     Here are the tasks in your list:
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ₍^› ꘍ ‹ ^₎⟆ Please provide a valid task number.
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ₍^› ꘍ ‹ ^₎⟆ Please provide a valid task number.
    ____________________________________________________________
    ____________________________________________________________
     Got it. I've added this task:
       [T][ ] read book
     Now you have 1 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ₍^._.^₎ 𐒡 The description of a todo cannot be empty.
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ^๑_๑^ ੭ A deadline task requires a description and a '/by' time.
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ^๑_๑^ ੭ A deadline task requires a description and a '/by' time.
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ^๑_๑^ ੭ A deadline task requires a description and a '/by' time.
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ^๑_๑^ ੭ An event task requires a description, '/from', and '/to' times.
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ^๑_๑^ ੭ An event task requires a description, '/from', and '/to' times.
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ^๑_๑^ ੭ An event task requires a description, '/from', and '/to' times.
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ^๑_๑^ ੭ An event task requires a description, '/from', and '/to' times.
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ^๑_๑^ ੭ An event task requires a description, '/from', and '/to' times.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] read book
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
     OOPS! ₍^› ꘍ ‹ ^₎⟆ Please provide a valid task number.
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ₍^› ꘍ ‹ ^₎⟆ Please provide a valid task number.
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ₍^› ꘍ ‹ ^₎⟆ Please provide a valid task number.
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ₍^› ꘍ ‹ ^₎⟆ Please provide a valid task number.
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ₍^› ꘍ ‹ ^₎⟆ Please specify a task number to mark.
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ₍^› ꘍ ‹ ^₎⟆ Please provide a valid task number.
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ₍^› ꘍ ‹ ^₎⟆ Please provide a valid task number.
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ₍^› ꘍ ‹ ^₎⟆ Please provide a valid task number.
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ₍^› ꘍ ‹ ^₎⟆ Please specify a task number to unmark.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] read book
     2.[D][ ] return book (by: Sunday)
     3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
    ____________________________________________________________
    ____________________________________________________________
     Nice! I've marked this task as done:
       [D][X] return book (by: Sunday)
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ₍^› ꘍ ‹ ^₎⟆ I'm sorry, but I don't know what that means.
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
