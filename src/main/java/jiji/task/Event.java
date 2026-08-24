package jiji.task;

import jiji.parser.DateTimeUtil;

/**
 * Represents an event task with a start time and an end time.
 */
public class Event extends Task {

    /** Start time or date of the event. */
    protected String from;

    /** End time or date of the event. */
    protected String to;

    /**
     * Constructs a new Event task with the specified description, start time, and end time.
     *
     * @param description Description of the event.
     * @param from Start time or date string.
     * @param to End time or date string.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the storage text representation for an Event task with standardized date formatting.
     *
     * @return Formatted string for file storage: "E | <isDone> | <description> | <from> | <to>".
     */
    @Override
    public String toFileFormat() {
        return TaskType.EVENT.getCode() + " | " + super.toFileFormat() + " | "
                + DateTimeUtil.formatForStorage(from) + " | " + DateTimeUtil.formatForStorage(to);
    }

    /**
     * Returns the string representation of the event task, prefixed with [E] and suffixing formatted times.
     *
     * @return Formatted event string representation.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeUtil.formatForDisplay(from)
                + " to: " + DateTimeUtil.formatForDisplay(to) + ")";
    }
}
