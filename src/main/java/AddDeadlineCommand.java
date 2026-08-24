/**
 * Command that adds a new {@link Deadline} task to the task list.
 */
public class AddDeadlineCommand extends Command {

    private final String description;
    private final String by;

    /**
     * Constructs an AddDeadlineCommand with the specified description and deadline string.
     *
     * @param description Description of the deadline task.
     * @param by Deadline date or time string.
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the add deadline command, persisting changes to storage and updating the UI.
     *
     * @param tasks The task list.
     * @param ui The UI handler for displaying output.
     * @param storage The storage handler for saving tasks.
     * @throws JijiStorageException If saving to storage fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JijiStorageException {
        Task deadline = new Deadline(description, by);
        tasks.add(deadline);
        storage.save(tasks);
        ui.showTaskAdded(deadline, tasks.size());
    }
}
