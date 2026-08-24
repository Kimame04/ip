package jiji.command;

import jiji.exception.JijiException;
import jiji.exception.JijiInvalidIndexException;
import jiji.storage.Storage;
import jiji.task.Task;
import jiji.task.TaskList;
import jiji.ui.Ui;

/**
 * Command that marks a task at a specific index as completed.
 */
public class MarkCommand extends Command {

    private final int targetIndex;

    /**
     * Constructs a MarkCommand targeting the specified 0-based task index.
     *
     * @param targetIndex The 0-based index of the task to mark.
     */
    public MarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the mark command, updating the task state, saving to storage, and notifying the UI.
     *
     * @param tasks The task list.
     * @param ui The UI handler for displaying output.
     * @param storage The storage handler.
     * @throws JijiException If the target index is out of valid task bounds or saving fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JijiException {
        if (targetIndex < 0 || targetIndex >= tasks.size()) {
            throw JijiInvalidIndexException.forInvalidNumber();
        }
        Task task = tasks.get(targetIndex);
        task.markAsDone();
        storage.save(tasks);
        ui.showTaskMarked(task);
    }
}
