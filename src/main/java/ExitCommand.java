/**
 * Command that terminates the Jiji chatbot session.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by displaying the farewell message.
     *
     * @param tasks The task list.
     * @param ui The UI handler for displaying output.
     * @param storage The storage handler.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Returns true to signal application termination.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
