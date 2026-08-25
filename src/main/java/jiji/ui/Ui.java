package jiji.ui;

import java.util.List;
import java.util.Scanner;

import jiji.task.Task;
import jiji.task.TaskList;

/**
 * Handles all user interactions with the Jiji chatbot, including reading user commands
 * and rendering formatted responses, banners, task notifications, and error messages.
 */
public class Ui {

    /** Standard indentation for chatbot output. */
    private static final String INDENT = "     ";

    /** Horizontal divider line matching the standard Jiji UI layout. */
    private static final String DIVIDER = "    ____________________________________________________________";

    /** ASCII art banner for Jiji. */
    private static final String BANNER =
            "         _     _          _     _ \n"
            + "        | |   (_)        (_)   (_)\n"
            + "        | |    _          _     _ \n"
            + "     _  | |   | |        | |   | |\n"
            + "    | |_| |   | |     _  | |   | |\n"
            + "     \\___/    |_|    | |_| |   |_|\n"
            + "                      \\___/       \n";

    private final Scanner scanner;

    /**
     * Constructs a new Ui instance reading from standard input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a single line of command input from the user.
     *
     * @return The raw command string entered by the user, or empty string if input stream ended.
     */
    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return "";
    }

    /**
     * Checks if there are more lines of input available.
     *
     * @return True if another line is available, false otherwise.
     */
    public boolean hasNextCommand() {
        return scanner.hasNextLine();
    }

    /**
     * Prints the horizontal divider line.
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Displays the welcome message and ASCII art banner upon startup.
     */
    public void showWelcome() {
        showLine();
        System.out.println(BANNER);
        System.out.println(INDENT + "Hello! I'm Jiji.");
        System.out.println(INDENT + "What can I do for you?");
        showLine();
    }

    /**
     * Displays the farewell message upon exit.
     */
    public void showGoodbye() {
        showLine();
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Displays an error message formatted within standard divider lines.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        showLine();
        System.out.println(INDENT + message);
        showLine();
    }

    /**
     * Displays a warning message when existing task data cannot be loaded.
     */
    public void showLoadingError() {
        showLine();
        System.out.println(INDENT + "Warning: Could not load tasks from storage. Starting with an empty task list.");
        showLine();
    }

    /**
     * Displays all current tasks in the task list formatted with 1-based indexing.
     *
     * @param taskList The task list to display.
     */
    public void showTaskList(TaskList taskList) {
        showLine();
        System.out.println(INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(INDENT + (i + 1) + "." + taskList.get(i));
        }
        showLine();
    }

    /**
     * Displays all matching tasks found from a keyword search.
     *
     * @param matchingTasks The list of matching tasks to display.
     */
    public void showMatchingTasks(List<Task> matchingTasks) {
        showLine();
        System.out.println(INDENT + "Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println(INDENT + (i + 1) + "." + matchingTasks.get(i));
        }
        showLine();
    }

    /**
     * Displays a confirmation message after a new task has been added.
     *
     * @param task The newly added task.
     * @param totalTasks The total number of tasks currently in the list.
     */
    public void showTaskAdded(Task task, int totalTasks) {
        showLine();
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + "  " + task);
        System.out.println(INDENT + "Now you have " + totalTasks + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a confirmation message after a task has been deleted.
     *
     * @param task The deleted task.
     * @param totalTasks The total number of tasks remaining in the list.
     */
    public void showTaskRemoved(Task task, int totalTasks) {
        showLine();
        System.out.println(INDENT + "Noted. I've removed this task:");
        System.out.println(INDENT + "  " + task);
        System.out.println(INDENT + "Now you have " + totalTasks + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a confirmation message after a task has been marked as done.
     *
     * @param task The marked task.
     */
    public void showTaskMarked(Task task) {
        showLine();
        System.out.println(INDENT + "Nice! I've marked this task as done:");
        System.out.println(INDENT + "  " + task);
        showLine();
    }

    /**
     * Displays a confirmation message after a task has been marked as not done.
     *
     * @param task The unmarked task.
     */
    public void showTaskUnmarked(Task task) {
        showLine();
        System.out.println(INDENT + "OK, I've marked this task as not done yet:");
        System.out.println(INDENT + "  " + task);
        showLine();
    }
}
