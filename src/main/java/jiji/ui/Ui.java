package jiji.ui;

import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
     * Displays one or more message lines indented and enclosed within standard divider lines.
     *
     * @param messages Variable number of message strings to display.
     */
    public void showMessages(String... messages) {
        showLine();
        if (messages != null) {
            for (String message : messages) {
                System.out.println(INDENT + message);
            }
        }
        showLine();
    }

    /**
     * Displays the farewell message upon exit.
     */
    public void showGoodbye() {
        showMessages(formatGoodbye());
    }

    /**
     * Formats the farewell message upon exit.
     *
     * @return The formatted farewell string.
     */
    public String formatGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays an error message formatted within standard divider lines.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        showMessages(message);
    }

    /**
     * Displays a warning message when existing task data cannot be loaded.
     */
    public void showLoadingError() {
        showMessages("Warning: Could not load tasks from storage. Starting with an empty task list.");
    }

    /**
     * Displays all current tasks in the task list formatted with 1-based indexing.
     *
     * @param taskList The task list to display.
     */
    public void showTaskList(TaskList taskList) {
        showMessages(formatTaskList(taskList).split("\n"));
    }

    /**
     * Formats all current tasks in the task list with 1-based indexing.
     *
     * @param taskList The task list to format.
     * @return The formatted task list string.
     */
    public String formatTaskList(TaskList taskList) {
        String items = IntStream.range(0, taskList.size())
                .mapToObj(i -> (i + 1) + "." + taskList.get(i))
                .collect(Collectors.joining("\n"));
        return items.isEmpty()
                ? "Here are the tasks in your list:"
                : "Here are the tasks in your list:\n" + items;
    }

    /**
     * Displays all matching tasks found from a keyword search.
     *
     * @param matchingTasks The list of matching tasks to display.
     */
    public void showMatchingTasks(List<Task> matchingTasks) {
        showMessages(formatMatchingTasks(matchingTasks).split("\n"));
    }

    /**
     * Formats all matching tasks found from a keyword search.
     *
     * @param matchingTasks The list of matching tasks to format.
     * @return The formatted matching tasks string.
     */
    public String formatMatchingTasks(List<Task> matchingTasks) {
        String items = IntStream.range(0, matchingTasks.size())
                .mapToObj(i -> (i + 1) + "." + matchingTasks.get(i))
                .collect(Collectors.joining("\n"));
        return items.isEmpty()
                ? "Here are the matching tasks in your list:"
                : "Here are the matching tasks in your list:\n" + items;
    }

    /**
     * Displays a confirmation message after a new task has been added.
     *
     * @param task The newly added task.
     * @param totalTasks The total number of tasks currently in the list.
     */
    public void showTaskAdded(Task task, int totalTasks) {
        showMessages(
                "Got it. I've added this task:",
                "  " + task,
                "Now you have " + totalTasks + " tasks in the list."
        );
    }

    /**
     * Formats a confirmation message after a new task has been added.
     *
     * @param task The newly added task.
     * @param totalTasks The total number of tasks currently in the list.
     * @return The formatted confirmation string.
     */
    public String formatTaskAdded(Task task, int totalTasks) {
        return "Got it. I've added this task:\n  " + task
                + "\nNow you have " + totalTasks + " tasks in the list.";
    }

    /**
     * Displays a confirmation message after a task has been deleted.
     *
     * @param task The deleted task.
     * @param totalTasks The total number of tasks remaining in the list.
     */
    public void showTaskRemoved(Task task, int totalTasks) {
        showMessages(
                "Noted. I've removed this task:",
                "  " + task,
                "Now you have " + totalTasks + " tasks in the list."
        );
    }

    /**
     * Formats a confirmation message after a task has been deleted.
     *
     * @param task The deleted task.
     * @param totalTasks The total number of tasks remaining in the list.
     * @return The formatted confirmation string.
     */
    public String formatTaskRemoved(Task task, int totalTasks) {
        return "Noted. I've removed this task:\n  " + task
                + "\nNow you have " + totalTasks + " tasks in the list.";
    }

    /**
     * Displays a confirmation message after a task has been marked as done.
     *
     * @param task The marked task.
     */
    public void showTaskMarked(Task task) {
        showMessages(
                "Nice! I've marked this task as done:",
                "  " + task
        );
    }

    /**
     * Formats a confirmation message after a task has been marked as done.
     *
     * @param task The marked task.
     * @return The formatted confirmation string.
     */
    public String formatTaskMarked(Task task) {
        return "Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * Displays a confirmation message after a task has been marked as not done.
     *
     * @param task The unmarked task.
     */
    public void showTaskUnmarked(Task task) {
        showMessages(
                "OK, I've marked this task as not done yet:",
                "  " + task
        );
    }

    /**
     * Formats a confirmation message after a task has been marked as not done.
     *
     * @param task The unmarked task.
     * @return The formatted confirmation string.
     */
    public String formatTaskUnmarked(Task task) {
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    /**
     * Displays the help guide or command details enclosed in divider lines.
     *
     * @param helpMessage The formatted help message to display.
     */
    public void showHelp(String helpMessage) {
        assert helpMessage != null : "Help message cannot be null";
        showMessages(helpMessage.split("\n"));
    }

    /**
     * Formats tasks matching a predicate filter, preserving master 1-based indices.
     *
     * @param taskList The task list to format.
     * @param filter The condition a task must meet to be included.
     * @param header The header message preceding matching tasks.
     * @param emptyMessage The message to display when no tasks match the filter.
     * @return The formatted task list string.
     */
    private String formatFilteredTasks(TaskList taskList, Predicate<Task> filter,
            String header, String emptyMessage) {
        assert taskList != null : "TaskList cannot be null";
        assert filter != null : "Filter predicate cannot be null";
        String items = IntStream.range(0, taskList.size())
                .filter(i -> filter.test(taskList.get(i)))
                .mapToObj(i -> (i + 1) + "." + taskList.get(i))
                .collect(Collectors.joining("\n"));
        return items.isEmpty() ? emptyMessage : header + "\n" + items;
    }

    /**
     * Displays all incomplete (pending) tasks in the task list, preserving master indices.
     *
     * @param taskList The task list to display.
     */
    public void showPendingTasks(TaskList taskList) {
        showMessages(formatPendingTasks(taskList).split("\n"));
    }

    /**
     * Formats all incomplete (pending) tasks in the task list, preserving master indices.
     *
     * @param taskList The task list to format.
     * @return Formatted pending tasks string.
     */
    public String formatPendingTasks(TaskList taskList) {
        return formatFilteredTasks(taskList, t -> !t.isDone(),
                "Here are the pending tasks in your list:",
                "You have no pending tasks! Great job! ₍^. .^₎");
    }

    /**
     * Displays all completed tasks in the task list, preserving master indices.
     *
     * @param taskList The task list to display.
     */
    public void showDoneTasks(TaskList taskList) {
        showMessages(formatDoneTasks(taskList).split("\n"));
    }

    /**
     * Formats all completed tasks in the task list, preserving master indices.
     *
     * @param taskList The task list to format.
     * @return Formatted completed tasks string.
     */
    public String formatDoneTasks(TaskList taskList) {
        return formatFilteredTasks(taskList, Task::isDone,
                "Here are the completed tasks in your list:",
                "You have no completed tasks yet.");
    }

    /**
     * Displays the statistics dashboard within standard divider lines.
     *
     * @param statsMessage The formatted statistics message to display.
     */
    public void showStats(String statsMessage) {
        assert statsMessage != null : "Stats message cannot be null";
        showMessages(statsMessage.split("\n"));
    }
}
