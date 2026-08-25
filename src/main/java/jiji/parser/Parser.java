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
        CommandType commandType = CommandType.from(commandWord);

        switch (commandType) {
        case BYE:
            return new ExitCommand();

        case LIST:
            return new ListCommand();

        case MARK:
            return new MarkCommand(parseIndex(trimmed, "mark"));

        case UNMARK:
            return new UnmarkCommand(parseIndex(trimmed, "unmark"));

        case DELETE:
            return new DeleteCommand(parseIndex(trimmed, "delete"));

        case TODO:
            return parseTodo(trimmed);

        case DEADLINE:
            return parseDeadline(trimmed);

        case EVENT:
            return parseEvent(trimmed);

        case FIND:
            return parseFind(trimmed);

        default:
            throw new JijiUnknownCommandException();
        }
    }

    /**
     * Parses the task index argument for mark, unmark, and delete commands.
     *
     * @param input The full command string.
     * @param commandName The name of the command ("mark", "unmark", "delete").
     * @return The 0-based task index.
     * @throws JijiException If index is missing or non-numeric.
     */
    private static int parseIndex(String input, String commandName) throws JijiException {
        String arg = input.length() > commandName.length() ? input.substring(commandName.length()).trim() : "";
        if (arg.isEmpty()) {
            throw JijiInvalidIndexException.forMissingIndex(commandName);
        }
        try {
            return Integer.parseInt(arg) - 1;
        } catch (NumberFormatException e) {
            throw JijiInvalidIndexException.forInvalidNumber();
        }
    }

    /**
     * Parses the todo command arguments.
     *
     * @param input The full todo command string.
     * @return An {@link AddTodoCommand} instance.
     * @throws JijiException If description is empty.
     */
    private static Command parseTodo(String input) throws JijiException {
        String description = input.length() > 4 ? input.substring(4).trim() : "";
        if (description.isEmpty()) {
            throw JijiMissingArgumentException.forEmptyTodo();
        }
        return new AddTodoCommand(description);
    }

    /**
     * Parses the deadline command arguments.
     *
     * @param input The full deadline command string.
     * @return An {@link AddDeadlineCommand} instance.
     * @throws JijiException If description or deadline parameter is missing.
     */
    private static Command parseDeadline(String input) throws JijiException {
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
        return new AddDeadlineCommand(description, by);
    }

    /**
     * Parses the event command arguments.
     *
     * @param input The full event command string.
     * @return An {@link AddEventCommand} instance.
     * @throws JijiException If description, /from, or /to parameter is missing.
     */
    private static Command parseEvent(String input) throws JijiException {
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
        return new AddEventCommand(description, from, to);
    }

    /**
     * Parses the find command arguments.
     *
     * @param input The full find command string.
     * @return A {@link FindCommand} instance.
     * @throws JijiException If search keyword is empty.
     */
    private static Command parseFind(String input) throws JijiException {
        String keyword = input.length() > 4 ? input.substring(4).trim() : "";
        if (keyword.isEmpty()) {
            throw JijiMissingArgumentException.forEmptyFind();
        }
        return new FindCommand(keyword);
    }
}
