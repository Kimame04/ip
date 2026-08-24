package jiji.command;

import jiji.exception.JijiStorageException;
import jiji.storage.Storage;
import jiji.task.Task;
import jiji.task.TaskList;
import jiji.task.Todo;
import jiji.ui.Ui;

/**
 * Command that adds a new {@link Todo} task to the task list.
 */
public class AddTodoCommand extends Command {

    private final String description;

    /**
     * Constructs an AddTodoCommand with the specified task description.
     *
     * @param description Description of the todo item.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the add todo command, persisting changes to storage and updating the UI.
     *
     * @param tasks The task list.
     * @param ui The UI handler for displaying output.
     * @param storage The storage handler for saving tasks.
     * @throws JijiStorageException If saving to storage fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JijiStorageException {
        Task todo = new Todo(description);
        tasks.add(todo);
        storage.save(tasks);
        ui.showTaskAdded(todo, tasks.size());
    }
}
