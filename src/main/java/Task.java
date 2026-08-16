/**
 * Represents a task in the Jiji chatbot.
 * A task contains a textual description and a completion status.
 */
public class Task {

    /** Description of the task. */
    protected String description;

    /** Completion status of the task (true if done, false otherwise). */
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * Newly created tasks are marked as not done by default.
     *
     * @param description The text description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon representing whether the task is completed.
     *
     * @return "X" if the task is done, or " " (single space) if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string representation of the task including its status icon.
     *
     * @return Formatted string representation "[status] description".
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
