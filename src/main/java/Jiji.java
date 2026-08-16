import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for the Jiji personal assistant chatbot.
 * Level-2 implementation maintains a list of user-added tasks,
 * allows viewing all tasks via the "list" command, and terminates on "bye".
 */
public class Jiji {

    /** Horizontal line divider used to format chatbot responses. */
    private static final String LINE = "    ____________________________________________________________";

    /** Indentation prefix for chatbot response messages. */
    private static final String INDENT = "     ";

    /**
     * Entry point of the Jiji application.
     * Manages greeting, task storage, listing, and graceful exit.
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

        List<String> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                printDivider();
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(INDENT + (i + 1) + ". " + tasks.get(i));
                }
                printDivider();
            } else {
                tasks.add(input);
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
