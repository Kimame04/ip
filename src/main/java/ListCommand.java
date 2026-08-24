/**
 * Command that lists all current tasks formatted for the user.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks via the UI.
     *
     * @param tasks The task list.
     * @param ui The UI handler for displaying output.
     * @param storage The storage handler.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
