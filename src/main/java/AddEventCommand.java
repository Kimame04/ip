/**
 * Command that adds a new {@link Event} task to the task list.
 */
public class AddEventCommand extends Command {

    private final String description;
    private final String from;
    private final String to;

    /**
     * Constructs an AddEventCommand with the specified description, start time, and end time.
     *
     * @param description Description of the event task.
     * @param from Start date/time string.
     * @param to End date/time string.
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the add event command, persisting changes to storage and updating the UI.
     *
     * @param tasks The task list.
     * @param ui The UI handler for displaying output.
     * @param storage The storage handler for saving tasks.
     * @throws JijiStorageException If saving to storage fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JijiStorageException {
        Task event = new Event(description, from, to);
        tasks.add(event);
        storage.save(tasks);
        ui.showTaskAdded(event, tasks.size());
    }
}
