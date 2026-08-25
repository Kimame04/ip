package jiji.command;

import jiji.exception.JijiUnknownCommandException;

/**
 * Represents the set of valid command types recognized by the Jiji chatbot.
 */
public enum CommandType {
    /** Command to terminate the chatbot session. */
    BYE("bye"),
    /** Command to list all current tasks. */
    LIST("list"),
    /** Command to mark a task as completed. */
    MARK("mark"),
    /** Command to mark a task as incomplete. */
    UNMARK("unmark"),
    /** Command to delete a task from the list. */
    DELETE("delete"),
    /** Command to create a new Todo task. */
    TODO("todo"),
    /** Command to create a new Deadline task. */
    DEADLINE("deadline"),
    /** Command to create a new Event task. */
    EVENT("event");

    private final String commandWord;

    /**
     * Constructs a CommandType associated with its string command word.
     *
     * @param commandWord The command word recognized in user input.
     */
    CommandType(String commandWord) {
        this.commandWord = commandWord;
    }

    /**
     * Returns the command word string.
     *
     * @return The command word.
     */
    public String getCommandWord() {
        return commandWord;
    }

    /**
     * Parses a raw command word into its corresponding {@link CommandType}.
     *
     * @param word The first word of the user input command.
     * @return The matching {@link CommandType}.
     * @throws JijiUnknownCommandException If the word does not match any recognized command.
     */
    public static CommandType from(String word) throws JijiUnknownCommandException {
        if (word == null) {
            throw new JijiUnknownCommandException();
        }
        for (CommandType type : values()) {
            if (type.commandWord.equalsIgnoreCase(word)) {
                return type;
            }
        }
        throw new JijiUnknownCommandException();
    }
}
