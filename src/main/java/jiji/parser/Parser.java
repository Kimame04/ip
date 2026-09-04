package jiji.parser;

import jiji.command.AddDeadlineCommand;
import jiji.command.AddEventCommand;
import jiji.command.AddTodoCommand;
import jiji.command.Command;
import jiji.command.CommandType;
import jiji.command.DeleteCommand;
import jiji.command.ExitCommand;
import jiji.command.FindCommand;
import jiji.command.ListCommand;
import jiji.command.MarkCommand;
import jiji.command.UnmarkCommand;
import jiji.exception.JijiException;
import jiji.exception.JijiInvalidIndexException;
import jiji.exception.JijiMissingArgumentException;
import jiji.exception.JijiUnknownCommandException;

/**
 * Parses user input commands into executable {@link Command} objects.
 */
public class Parser {

    private static final String DEADLINE_BY_DELIMITER = " /by ";
    private static final String EVENT_FROM_DELIMITER = " /from ";
    private static final String EVENT_TO_DELIMITER = " /to ";

    /**
     * Prevents instantiation of utility class.
     */
    private Parser() {
    }

    /**
     * Parses the full command string entered by the user.
     *
     * @param fullCommand The raw input string.
     * @return The corresponding executable {@link Command}.
     * @throws JijiException If the input is unrecognized, malformed, or missing required parameters.
     */
    public static Command parse(String fullCommand) throws JijiException {
        if (fullCommand == null || fullCommand.trim().isEmpty()) {
            throw new JijiUnknownCommandException();
        }

        String trimmed = fullCommand.trim();
        String[] words = trimmed.split("\\s+", 2);
        String commandWord = words[0];
        String arguments = (words.length > 1) ? words[1].trim() : "";
        CommandType commandType = CommandType.from(commandWord);

        switch (commandType) {
            case BYE:
                return new ExitCommand();

            case LIST:
                return new ListCommand();

            case MARK:
                return new MarkCommand(parseIndex(arguments, "mark"));

            case UNMARK:
                return new UnmarkCommand(parseIndex(arguments, "unmark"));

            case DELETE:
                return new DeleteCommand(parseIndex(arguments, "delete"));

            case TODO:
                return parseTodo(arguments);

            case DEADLINE:
                return parseDeadline(arguments);

            case EVENT:
                return parseEvent(arguments);

            case FIND:
                return parseFind(arguments);

            default:
                throw new JijiUnknownCommandException();
        }
    }

    /**
     * Parses the task index argument for mark, unmark, and delete commands.
     *
     * @param arguments The command arguments string.
     * @param commandName The name of the command ("mark", "unmark", "delete").
     * @return The 0-based task index.
     * @throws JijiException If index is missing or non-numeric.
     */
    private static int parseIndex(String arguments, String commandName) throws JijiException {
        if (arguments.isEmpty()) {
            throw JijiInvalidIndexException.forMissingIndex(commandName);
        }
        try {
            return Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw JijiInvalidIndexException.forInvalidNumber();
        }
    }

    /**
     * Parses the todo command arguments.
     *
     * @param arguments The command arguments string.
     * @return An {@link AddTodoCommand} instance.
     * @throws JijiException If description is empty.
     */
    private static Command parseTodo(String arguments) throws JijiException {
        if (arguments.isEmpty()) {
            throw JijiMissingArgumentException.forEmptyTodo();
        }
        return new AddTodoCommand(arguments);
    }

    /**
     * Parses the deadline command arguments.
     *
     * @param arguments The command arguments string.
     * @return An {@link AddDeadlineCommand} instance.
     * @throws JijiException If description or deadline parameter is missing.
     */
    private static Command parseDeadline(String arguments) throws JijiException {
        if (arguments.isEmpty() || !arguments.contains(DEADLINE_BY_DELIMITER)) {
            throw JijiMissingArgumentException.forMissingDeadline();
        }
        String[] parts = arguments.split(DEADLINE_BY_DELIMITER, 2);
        String description = parts[0].trim();
        String by = parts.length > 1 ? parts[1].trim() : "";
        if (description.isEmpty() || by.isEmpty()) {
            throw JijiMissingArgumentException.forMissingDeadline();
        }
        return new AddDeadlineCommand(description, by);
    }

    /**
     * Parses the event command arguments.
     *
     * @param arguments The command arguments string.
     * @return An {@link AddEventCommand} instance.
     * @throws JijiException If description, /from, or /to parameter is missing.
     */
    private static Command parseEvent(String arguments) throws JijiException {
        if (arguments.isEmpty() || !arguments.contains(EVENT_FROM_DELIMITER)
                || !arguments.contains(EVENT_TO_DELIMITER)) {
            throw JijiMissingArgumentException.forMissingEvent();
        }
        String[] parts = arguments.split(EVENT_FROM_DELIMITER, 2);
        String description = parts[0].trim();
        if (description.isEmpty()) {
            throw JijiMissingArgumentException.forMissingEvent();
        }
        String[] timeParts = parts[1].split(EVENT_TO_DELIMITER, 2);
        String from = timeParts[0].trim();
        String to = timeParts.length > 1 ? timeParts[1].trim() : "";
        if (from.isEmpty() || to.isEmpty()) {
            throw JijiMissingArgumentException.forMissingEvent();
        }
        return new AddEventCommand(description, from, to);
    }

    /**
     * Parses the find command arguments.
     *
     * @param arguments The command arguments string.
     * @return A {@link FindCommand} instance.
     * @throws JijiException If search keyword is empty.
     */
    private static Command parseFind(String arguments) throws JijiException {
        if (arguments.isEmpty()) {
            throw JijiMissingArgumentException.forEmptyFind();
        }
        return new FindCommand(arguments);
    }
}
