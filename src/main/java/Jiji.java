/**
 * Main class for the Jiji personal assistant chatbot.
 * Level-0 implementation provides greeting and exit functionality.
 */
public class Jiji {

    /** Horizontal line divider used to format chatbot responses. */
    private static final String LINE = "____________________________________________________________";

    /**
     * Entry point of the Jiji application.
     * Displays a welcome banner, a greeting message, and an exit message.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        String banner = "     _     _          _     _ \n"
                + "    | |   (_)        (_)   (_)\n"
                + "    | |    _          _     _ \n"
                + " _  | |   | |        | |   | |\n"
                + "| |_| |   | |     _  | |   | |\n"
                + " \\___/    |_|    | |_| |   |_|\n"
                + "                  \\___/       \n";

        printDivider();
        System.out.println(banner);
        System.out.println("Hello! I'm Jiji.");
        System.out.println("What can I do for you?");
        printDivider();
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }

    /**
     * Prints the horizontal divider line.
     */
    private static void printDivider() {
        System.out.println(LINE);
    }
}
