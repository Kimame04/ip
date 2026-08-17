import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for the Jiji personal assistant chatbot.
 * Level-6 implementation with Extension A-Collections introduces task deletion,
 * dynamic collection management via java.util.ArrayList, and OOP exception handling.
 */
public class Jiji {

    /** Horizontal line divider used to format chatbot responses. */
    private static final String LINE = "    ____________________________________________________________";

    /** Indentation prefix for chatbot response messages. */
    private static final String INDENT = "     ";

    /**
     * Entry point of the Jiji application.
     * Manages greeting, error handling, task management (add, mark, unmark, delete, list), and exit.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        String banner = "         _     _          _     _ \n"
                + "        | |   (_)        (_)   (_)\n"
                + "        | |    _          _     _ \n"
                + "     _  | |   | |        | |   | |\n"
                + "    | |_| |   | |     _  | |   | |\n"
                + "     \\___/    |_|    | |_| |   |_|\n"
                + "                      \\___/       \n";

        printDivider();
        System.out.println(banner);
        System.out.println(INDENT + "Hello! I'm Jiji.");
        System.out.println(INDENT + "What can I do for you?");
        printDivider();

        List<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                continue;
            }

            try {
                if (input.equalsIgnoreCase("bye")) {
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    printDivider();
                    System.out.println(INDENT + "Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(INDENT + (i + 1) + "." + tasks.get(i));
                    }
                    printDivider();
                } else if (input.equals("mark") || input.startsWith("mark ")) {
                    handleMark(tasks, input);
                } else if (input.equals("unmark") || input.startsWith("unmark ")) {
                    handleUnmark(tasks, input);
                } else if (input.equals("delete") || input.startsWith("delete ")) {
                    handleDelete(tasks, input);
                } else if (input.equals("todo") || input.startsWith("todo ")) {
                    handleTodo(tasks, input);
                } else if (input.equals("deadline") || input.startsWith("deadline ")) {
                    handleDeadline(tasks, input);
                } else if (input.equals("event") || input.startsWith("event ")) {
                    handleEvent(tasks, input);
                } else {
                    throw new JijiUnknownCommandException();
                }
            } catch (JijiException e) {
                printDivider();
                System.out.println(INDENT + e.getMessage());
                printDivider();
            }
        }

        printDivider();
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        printDivider();
        scanner.close();
    }

    /**
     * Handles marking a task as done.
     *
     * @param tasks The list of tasks.
     * @param input The raw user command.
     * @throws JijiException If index is missing or out of valid range.
     */
    private static void handleMark(List<Task> tasks, String input) throws JijiException {
        String arg = input.length() > 4 ? input.substring(4).trim() : "";
        if (arg.isEmpty()) {
            throw JijiInvalidIndexException.forMissingIndex("mark");
        }
        int index = parseIndex(arg, tasks.size());
        Task task = tasks.get(index);
        task.markAsDone();

        printDivider();
        System.out.println(INDENT + "Nice! I've marked this task as done:");
        System.out.println(INDENT + "  " + task);
        printDivider();
    }

    /**
     * Handles unmarking a task (marking as not done).
     *
     * @param tasks The list of tasks.
     * @param input The raw user command.
     * @throws JijiException If index is missing or out of valid range.
     */
    private static void handleUnmark(List<Task> tasks, String input) throws JijiException {
        String arg = input.length() > 6 ? input.substring(6).trim() : "";
        if (arg.isEmpty()) {
            throw JijiInvalidIndexException.forMissingIndex("unmark");
        }
        int index = parseIndex(arg, tasks.size());
        Task task = tasks.get(index);
        task.markAsNotDone();

        printDivider();
        System.out.println(INDENT + "OK, I've marked this task as not done yet:");
        System.out.println(INDENT + "  " + task);
        printDivider();
    }

    /**
     * Handles deleting a task from the list using Java Collections.
     *
     * @param tasks The list of tasks.
     * @param input The raw user command.
     * @throws JijiException If index is missing or out of valid range.
     */
    private static void handleDelete(List<Task> tasks, String input) throws JijiException {
        String arg = input.length() > 6 ? input.substring(6).trim() : "";
        if (arg.isEmpty()) {
            throw JijiInvalidIndexException.forMissingIndex("delete");
        }
        int index = parseIndex(arg, tasks.size());
        Task removedTask = tasks.remove(index);

        printDivider();
        System.out.println(INDENT + "Noted. I've removed this task:");
        System.out.println(INDENT + "  " + removedTask);
        System.out.println(INDENT + "Now you have " + tasks.size() + " tasks in the list.");
        printDivider();
    }

    /**
     * Handles creating a Todo task.
     *
     * @param tasks The list of tasks.
     * @param input The raw user command.
     * @throws JijiException If description is empty.
     */
    private static void handleTodo(List<Task> tasks, String input) throws JijiException {
        String description = input.length() > 4 ? input.substring(4).trim() : "";
        if (description.isEmpty()) {
            throw JijiMissingArgumentException.forEmptyTodo();
        }
        addTask(tasks, new Todo(description));
    }

    /**
     * Handles creating a Deadline task.
     *
     * @param tasks The list of tasks.
     * @param input The raw user command.
     * @throws JijiException If description or deadline '/by' parameter is missing.
     */
    private static void handleDeadline(List<Task> tasks, String input) throws JijiException {
        String rest = input.length() > 8 ? input.substring(8).trim() : "";
        if (rest.isEmpty() || !rest.contains(" /by ")) {
            throw JijiMissingArgumentException.forMissingDeadline();
        }
        String[] parts = rest.split(" /by ", 2);
        String description = parts[0].trim();
        String by = parts.length > 1 ? parts[1].trim() : "";
        if (description.isEmpty() || by.isEmpty()) {
            throw JijiMissingArgumentException.forMissingDeadline();
        }
        addTask(tasks, new Deadline(description, by));
    }

    /**
     * Handles creating an Event task.
     *
     * @param tasks The list of tasks.
     * @param input The raw user command.
     * @throws JijiException If description, '/from', or '/to' parameter is missing.
     */
    private static void handleEvent(List<Task> tasks, String input) throws JijiException {
        String rest = input.length() > 5 ? input.substring(5).trim() : "";
        if (rest.isEmpty() || !rest.contains(" /from ") || !rest.contains(" /to ")) {
            throw JijiMissingArgumentException.forMissingEvent();
        }
        String[] parts = rest.split(" /from ", 2);
        String description = parts[0].trim();
        if (description.isEmpty()) {
            throw JijiMissingArgumentException.forMissingEvent();
        }
        String[] timeParts = parts[1].split(" /to ", 2);
        String from = timeParts[0].trim();
        String to = timeParts.length > 1 ? timeParts[1].trim() : "";
        if (from.isEmpty() || to.isEmpty()) {
            throw JijiMissingArgumentException.forMissingEvent();
        }
        addTask(tasks, new Event(description, from, to));
    }

    /**
     * Parses a string input into a 0-based task index.
     *
     * @param arg The string containing the task number.
     * @param size The current size of the task list.
     * @return The 0-based index.
     * @throws JijiException If the number format is invalid or out of range.
     */
    private static int parseIndex(String arg, int size) throws JijiException {
        try {
            int index = Integer.parseInt(arg) - 1;
            if (index < 0 || index >= size) {
                throw JijiInvalidIndexException.forInvalidNumber();
            }
            return index;
        } catch (NumberFormatException e) {
            throw JijiInvalidIndexException.forInvalidNumber();
        }
    }

    /**
     * Adds a task to the task list and prints the standard confirmation message.
     *
     * @param tasks The list of tasks.
     * @param task The task to be added.
     */
    private static void addTask(List<Task> tasks, Task task) {
        tasks.add(task);
        printDivider();
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + "  " + task);
        System.out.println(INDENT + "Now you have " + tasks.size() + " tasks in the list.");
        printDivider();
    }

    /**
     * Prints the horizontal divider line.
     */
    private static void printDivider() {
        System.out.println(LINE);
    }
}
