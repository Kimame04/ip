package jiji.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jiji.command.AddDeadlineCommand;
import jiji.command.AddEventCommand;
import jiji.command.AddTodoCommand;
import jiji.command.Command;
import jiji.command.DeleteCommand;
import jiji.command.ExitCommand;
import jiji.command.ListCommand;
import jiji.command.MarkCommand;
import jiji.command.UnmarkCommand;
import jiji.exception.JijiException;
import jiji.exception.JijiInvalidIndexException;
import jiji.exception.JijiMissingArgumentException;
import jiji.exception.JijiUnknownCommandException;

public class ParserTest {

    @Test
    public void parse_validTodo_returnsAddTodoCommand() throws JijiException {
        Command cmd = Parser.parse("todo read book");
        assertInstanceOf(AddTodoCommand.class, cmd);
    }

    @Test
    public void parse_emptyTodoDescription_throwsException() {
        assertThrows(JijiMissingArgumentException.class, () -> Parser.parse("todo"));
        assertThrows(JijiMissingArgumentException.class, () -> Parser.parse("todo   "));
    }

    @Test
    public void parse_validDeadline_returnsAddDeadlineCommand() throws JijiException {
        Command cmd = Parser.parse("deadline return book /by Sunday");
        assertInstanceOf(AddDeadlineCommand.class, cmd);
    }

    @Test
    public void parse_missingDeadlineBy_throwsException() {
        assertThrows(JijiMissingArgumentException.class, () -> Parser.parse("deadline return book"));
        assertThrows(JijiMissingArgumentException.class, () -> Parser.parse("deadline return book /by "));
    }

    @Test
    public void parse_validEvent_returnsAddEventCommand() throws JijiException {
        Command cmd = Parser.parse("event project meeting /from Mon 2pm /to 4pm");
        assertInstanceOf(AddEventCommand.class, cmd);
    }

    @Test
    public void parse_missingEventParams_throwsException() {
        assertThrows(JijiMissingArgumentException.class, () -> Parser.parse("event project meeting /from Mon 2pm"));
        assertThrows(JijiMissingArgumentException.class, () -> Parser.parse("event project meeting /to 4pm"));
    }

    @Test
    public void parse_validMarkAndUnmark_returnsRespectiveCommands() throws JijiException {
        Command mark = Parser.parse("mark 2");
        assertInstanceOf(MarkCommand.class, mark);

        Command unmark = Parser.parse("unmark 2");
        assertInstanceOf(UnmarkCommand.class, unmark);
    }

    @Test
    public void parse_validDelete_returnsDeleteCommand() throws JijiException {
        Command delete = Parser.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, delete);
    }

    @Test
    public void parse_missingOrInvalidIndex_throwsException() {
        assertThrows(JijiInvalidIndexException.class, () -> Parser.parse("mark"));
        assertThrows(JijiInvalidIndexException.class, () -> Parser.parse("unmark abc"));
        assertThrows(JijiInvalidIndexException.class, () -> Parser.parse("delete"));
    }

    @Test
    public void parse_listAndBye_returnsRespectiveCommands() throws JijiException {
        assertInstanceOf(ListCommand.class, Parser.parse("list"));
        Command exit = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, exit);
        assertTrue(exit.isExit());
    }

    @Test
    public void parse_validFind_returnsFindCommand() throws JijiException {
        Command find = Parser.parse("find book");
        assertInstanceOf(jiji.command.FindCommand.class, find);
    }

    @Test
    public void parse_emptyFindKeyword_throwsException() {
        assertThrows(JijiMissingArgumentException.class, () -> Parser.parse("find"));
        assertThrows(JijiMissingArgumentException.class, () -> Parser.parse("find   "));
    }

    @Test
    public void parse_unknownCommand_throwsException() {
        assertThrows(JijiUnknownCommandException.class, () -> Parser.parse("unknownCommand"));
        assertThrows(JijiUnknownCommandException.class, () -> Parser.parse(""));
        assertThrows(JijiUnknownCommandException.class, () -> Parser.parse(null));
    }
}
