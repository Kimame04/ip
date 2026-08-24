/**
 * Command that removes a task at a specific index from the task list.
 */
public class DeleteCommand extends Command {

    private final int targetIndex;

    /**
     * Constructs a DeleteCommand targeting the specified 0-based task index.
     *
     * @param targetIndex The 0-based index of the task to delete.
     */
    public DeleteCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the delete command, removing the task, saving to storage, and notifying the UI.
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
        Task removed = tasks.delete(targetIndex);
        storage.save(tasks);
        ui.showTaskRemoved(removed, tasks.size());
    }
}
