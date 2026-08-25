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

## Test Case 4: Delete Tasks and Re-indexing (Level-6 & A-Collections)
- **Aim**: Verify deleting tasks from the middle, beginning, and end of the list, verifying re-indexing and task count decrement.
- **Inputs**:
```text
todo read book
deadline return book /by Sunday
event project meeting /from Mon 2pm /to 4pm
list
delete 2
list
delete 1
list
delete 1
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
     Noted. I've removed this task:
       [D][ ] return book (by: Sunday)
     Now you have 2 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] read book
     2.[E][ ] project meeting (from: Mon 2pm to: 4pm)
    ____________________________________________________________
    ____________________________________________________________
     Noted. I've removed this task:
       [T][ ] read book
     Now you have 1 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[E][ ] project meeting (from: Mon 2pm to: 4pm)
    ____________________________________________________________
    ____________________________________________________________
     Noted. I've removed this task:
       [E][ ] project meeting (from: Mon 2pm to: 4pm)
     Now you have 0 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

---

## Test Case 5: Error Handling and Interleaved Operations
- **Aim**: Verify that invalid commands, malformed parameters, and out-of-bounds operations (including delete) do not corrupt the task list or alter state.
- **Inputs**:
```text
list
delete 1
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
delete 0
delete -1
delete 4
delete abc
delete
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
delete 2
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
     OOPS! ₍^› ꘍ ‹ ^₎⟆ Please specify a task number to delete.
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
     Noted. I've removed this task:
       [D][X] return book (by: Sunday)
     Now you have 2 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] read book
     2.[E][ ] project meeting (from: Mon 2pm to: 4pm)
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

---

## Test Case 6: Data Persistence - Save Operations (Level-7)
- **Aim**: Verify adding, marking, and deleting tasks in an initial session, persisting them to data storage upon modification.
- **Inputs**:
```text
todo read textbook
deadline submit homework /by tonight
event team sync /from 3pm /to 4pm
mark 1
delete 2
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
       [T][ ] read textbook
     Now you have 1 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Got it. I've added this task:
       [D][ ] submit homework (by: tonight)
     Now you have 2 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Got it. I've added this task:
       [E][ ] team sync (from: 3pm to: 4pm)
     Now you have 3 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Nice! I've marked this task as done:
       [T][X] read textbook
    ____________________________________________________________
    ____________________________________________________________
     Noted. I've removed this task:
       [D][ ] submit homework (by: tonight)
     Now you have 2 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][X] read textbook
     2.[E][ ] team sync (from: 3pm to: 4pm)
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

---

## Test Case 7: Data Persistence - Load Saved Tasks in New Session (Level-7)
- **Aim**: Verify that launching a new session loads previously saved tasks and preserves their completion state.
- **Preserve Data**: true
- **Inputs**:
```text
list
todo sleep early
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
     1.[T][X] read textbook
     2.[E][ ] team sync (from: 3pm to: 4pm)
    ____________________________________________________________
    ____________________________________________________________
     Got it. I've added this task:
       [T][ ] sleep early
     Now you have 3 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][X] read textbook
     2.[E][ ] team sync (from: 3pm to: 4pm)
     3.[T][ ] sleep early
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

---

## Test Case 8: Semantic Date and Time Handling (Level-8)
- **Aim**: Verify parsing of standard date formats (yyyy-MM-dd, d/M/yyyy HHmm) and formatted display output (MMM dd yyyy, h:mma).
- **Inputs**:
```text
deadline return book /by 2026-08-30
deadline submit project /by 2/12/2026 1800
event orientation camp /from 2026-09-01 0900 /to 2026-09-03 1700
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
       [D][ ] return book (by: Aug 30 2026)
     Now you have 1 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Got it. I've added this task:
       [D][ ] submit project (by: Dec 02 2026, 6:00PM)
     Now you have 2 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Got it. I've added this task:
       [E][ ] orientation camp (from: Sep 01 2026, 9:00AM to: Sep 03 2026, 5:00PM)
     Now you have 3 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[D][ ] return book (by: Aug 30 2026)
     2.[D][ ] submit project (by: Dec 02 2026, 6:00PM)
     3.[E][ ] orientation camp (from: Sep 01 2026, 9:00AM to: Sep 03 2026, 5:00PM)
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

---

## Test Case 9: Persistence of Date/Time Objects (Level-8)
- **Aim**: Verify that dates/times saved in storage are reloaded across sessions and properly formatted upon restart.
- **Preserve Data**: true
- **Inputs**:
```text
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
     1.[D][ ] return book (by: Aug 30 2026)
     2.[D][ ] submit project (by: Dec 02 2026, 6:00PM)
     3.[E][ ] orientation camp (from: Sep 01 2026, 9:00AM to: Sep 03 2026, 5:00PM)
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

---

## Test Case 10: Find Tasks by Keyword (Level-9)
- **Aim**: Verify searching for matching tasks by keyword, empty result handling, and error handling for missing search keyword.
- **Inputs**:
```text
todo read book
deadline return book /by Sunday
event project meeting /from Mon 2pm /to 4pm
todo write book review
find book
find meeting
find swimming
find
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
     Got it. I've added this task:
       [T][ ] write book review
     Now you have 4 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Here are the matching tasks in your list:
     1.[T][ ] read book
     2.[D][ ] return book (by: Sunday)
     3.[T][ ] write book review
    ____________________________________________________________
    ____________________________________________________________
     Here are the matching tasks in your list:
     1.[E][ ] project meeting (from: Mon 2pm to: 4pm)
    ____________________________________________________________
    ____________________________________________________________
     Here are the matching tasks in your list:
    ____________________________________________________________
    ____________________________________________________________
     OOPS! ₍^› ꘍ ‹ ^₎⟆ Please specify a keyword to search for.
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```



