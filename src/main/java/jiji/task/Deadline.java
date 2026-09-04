package jiji.task;

import jiji.parser.DateTimeUtil;

/**
 * Represents a task with a deadline date/time constraint.
 */
public class Deadline extends Task {

    /** Deadline time or date by which the task must be completed. */
    protected String by;

    /**
     * Constructs a new Deadline task with the specified description and deadline time.
     *
     * @param description Description of the deadline task.
     * @param by Deadline date or time string.
     */
    public Deadline(String description, String by) {
        super(description);
        assert by != null && !by.isBlank() : "Deadline 'by' date/time cannot be null or blank";
        this.by = by;
    }

    /**
     * Returns the storage text representation for a Deadline task with standardized date formatting.
     *
     * @return Formatted string for file storage: {@code "D | <isDone> | <description> | <by>"}.
     */
    @Override
    public String toFileFormat() {
        return TaskType.DEADLINE.getCode() + " | " + super.toFileFormat() + " | "
                + DateTimeUtil.formatForStorage(by);
    }

    /**
     * Returns the string representation of the deadline task, prefixed with [D] and suffixing formatted (by: ...).
     *
     * @return Formatted deadline string representation.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeUtil.formatForDisplay(by) + ")";
    }
}
