/**
 * Exception thrown when a user enters an unrecognized command.
 */
public class JijiUnknownCommandException extends JijiException {

    /**
     * Constructs a JijiUnknownCommandException with the standard unknown command message.
     */
    public JijiUnknownCommandException() {
        super("OOPS! ₍^› ꘍ ‹ ^₎⟆ I'm sorry, but I don't know what that means.");
    }
}
