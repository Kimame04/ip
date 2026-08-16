import java.util.Scanner;

/**
 * Main class for the Jiji personal assistant chatbot.
 * Level-1 implementation provides interactive input reading, echoing user commands,
 * and exiting when the "bye" command is received.
 */
public class Jiji {

    /** Horizontal line divider used to format chatbot responses. */
    private static final String LINE = "    ____________________________________________________________";

    /** Indentation prefix for chatbot response messages. */
    private static final String INDENT = "     ";

    /**
     * Entry point of the Jiji application.
     * Starts the greeting sequence, reads and echoes user inputs, and terminates on "bye".
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

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            printDivider();
            System.out.println(INDENT + input);
            printDivider();
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
