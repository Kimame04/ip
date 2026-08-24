package jiji.command;

import jiji.exception.JijiException;
import jiji.storage.Storage;
import jiji.task.TaskList;
import jiji.ui.Ui;

/**
 * Abstract base class for all executable commands in Jiji.
 */
public abstract class Command {

    /**
     * Executes the command with the specified task list, UI, and storage dependencies.
     *
     * @param tasks The task list manipulated by the command.
     * @param ui The UI handler for displaying output to the user.
     * @param storage The storage handler for persisting changes.
     * @throws JijiException If an error occurs during command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws JijiException;

    /**
     * Indicates whether this command signals the chatbot to exit.
     *
     * @return True if the chatbot should terminate after this command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
