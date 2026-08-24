import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates the collection of tasks and provides operations to add, delete,
 * retrieve, and inspect tasks in the list.
 */
public class TaskList {

    private final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList populated with the specified initial list of tasks.
     *
     * @param tasks Initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = (tasks != null) ? tasks : new ArrayList<>();
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the specified 0-based index.
     *
     * @param index The 0-based index of the task to remove.
     * @return The removed {@link Task}.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the task at the specified 0-based index.
     *
     * @param index The 0-based index of the task.
     * @return The {@link Task} at the given index.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Total count of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if there are no tasks, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the underlying list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getAllTasks() {
        return tasks;
    }
}
