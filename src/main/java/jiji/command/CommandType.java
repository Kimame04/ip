package jiji.command;

import jiji.exception.JijiUnknownCommandException;

/**
 * Represents the set of valid command types recognized by the Jiji chatbot.
 */
public enum CommandType {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    FIND("find");

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
