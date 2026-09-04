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

        assert fullCommand != null && !fullCommand.trim().isEmpty() : "fullCommand must be non-empty";
        String trimmed = fullCommand.trim();
        String[] words = trimmed.split("\\s+", 2);
        String commandWord = words[0];
        assert !commandWord.isEmpty() : "commandWord cannot be empty after trimming";
        String arguments = (words.length > 1) ? words[1].trim() : "";
        CommandType commandType = CommandType.from(commandWord);

        Command command;
        switch (commandType) {
            case BYE:
                command = new ExitCommand();
                break;

            case LIST:
                command = new ListCommand();
                break;

            case MARK:
                command = new MarkCommand(parseIndex(arguments, "mark"));
                break;

            case UNMARK:
                command = new UnmarkCommand(parseIndex(arguments, "unmark"));
                break;

            case DELETE:
                command = new DeleteCommand(parseIndex(arguments, "delete"));
                break;

            case TODO:
                command = parseTodo(arguments);
                break;

            case DEADLINE:
                command = parseDeadline(arguments);
                break;

            case EVENT:
                command = parseEvent(arguments);
                break;

            case FIND:
                command = parseFind(arguments);
                break;

            default:
                throw new JijiUnknownCommandException();
        }
        assert command != null : "Parsed command must not be null";
        return command;
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
        assert commandName != null && !commandName.isEmpty() : "commandName cannot be empty";
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
        assert !arguments.isEmpty() : "Todo description must not be empty after check";
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
        assert !description.isEmpty() && !by.isEmpty() : "Deadline description and by must not be empty after check";
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
        assert !description.isEmpty() && !from.isEmpty() && !to.isEmpty()
                : "Event fields must not be empty after check";
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
        assert !arguments.isEmpty() : "Find keyword must not be empty after check";
        return new FindCommand(arguments);
    }
}
