import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for the Jiji personal assistant chatbot.
 * Level-3 implementation tracks tasks with completion status,
 * supports marking/unmarking tasks as done, and listing tasks with status indicators.
 */
public class Jiji {

    /** Horizontal line divider used to format chatbot responses. */
    private static final String LINE = "    ____________________________________________________________";

    /** Indentation prefix for chatbot response messages. */
    private static final String INDENT = "     ";

    /**
     * Entry point of the Jiji application.
     * Manages greeting, task creation, status updates (mark/unmark), listing, and graceful exit.
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

            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                printDivider();
                System.out.println(INDENT + "Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(INDENT + (i + 1) + "." + tasks.get(i));
                }
                printDivider();
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5).trim()) - 1;
                Task task = tasks.get(index);
                task.markAsDone();

                printDivider();
                System.out.println(INDENT + "Nice! I've marked this task as done:");
                System.out.println(INDENT + "  " + task);
                printDivider();
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7).trim()) - 1;
                Task task = tasks.get(index);
                task.markAsNotDone();

                printDivider();
                System.out.println(INDENT + "OK, I've marked this task as not done yet:");
                System.out.println(INDENT + "  " + task);
                printDivider();
            } else {
                Task task = new Task(input);
                tasks.add(task);

                printDivider();
                System.out.println(INDENT + "added: " + input);
                printDivider();
            }
        }

        printDivider();
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        printDivider();
        scanner.close();
    }

    /**
     * Prints the horizontal divider line.
     */
    private static void printDivider() {
        System.out.println(LINE);
    }
}
