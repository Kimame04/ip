package jiji.task;

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
        assert this.tasks != null : "Internal task collection must not be null";
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        assert task != null : "Task to add cannot be null";
        tasks.add(task);
        assert tasks.contains(task) : "TaskList should contain added task";
    }

    /**
     * Adds multiple tasks to the list.
     *
     * @param tasks Variable number of {@link Task} objects to add.
     */
    public void addAll(Task... tasks) {
        if (tasks != null) {
            for (Task task : tasks) {
                assert task != null : "Cannot add null task to TaskList";
                this.tasks.add(task);
            }
        }
    }

    /**
     * Removes and returns the task at the specified 0-based index.
     *
     * @param index The 0-based index of the task to remove.
     * @return The removed {@link Task}.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task delete(int index) {
        Task removed = tasks.remove(index);
        assert removed != null : "Removed task should not be null";
        return removed;
    }

    /**
     * Returns the task at the specified 0-based index.
     *
     * @param index The 0-based index of the task.
     * @return The {@link Task} at the given index.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task get(int index) {
        Task task = tasks.get(index);
        assert task != null : "Retrieved task should not be null";
        return task;
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
     * Finds and returns all tasks whose descriptions contain the specified keyword.
     * The search is case-insensitive.
     *
     * @param keyword The search keyword.
     * @return A list of matching tasks.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        if (keyword == null || keyword.trim().isEmpty()) {
            return matchingTasks;
        }
        String lowerKeyword = keyword.toLowerCase();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(lowerKeyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Returns the underlying list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getAllTasks() {
        assert tasks != null : "Underlying task list cannot be null";
        return tasks;
    }
}
