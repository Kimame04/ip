import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Utility class providing helper methods to parse, format, and serialize dates and times.
 * Supports multiple standard date formats (e.g. yyyy-MM-dd, d/M/yyyy) and date-time formats
 * (e.g. yyyy-MM-dd HHmm, d/M/yyyy HH:mm), formatting them for user display (e.g. MMM dd yyyy)
 * and file storage.
 */
public class DateTimeUtil {

    /** Formatter for displaying date-only values to the user. */
    private static final DateTimeFormatter DISPLAY_DATE_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);

    /** Formatter for displaying date-and-time values to the user. */
    private static final DateTimeFormatter DISPLAY_DATETIME_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma", Locale.ENGLISH);

    /** Formatter for saving date-only values to disk storage. */
    private static final DateTimeFormatter STORAGE_DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /** Formatter for saving date-and-time values to disk storage. */
    private static final DateTimeFormatter STORAGE_DATETIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /** Recognized input patterns for parsing LocalDate. */
    private static final DateTimeFormatter[] DATE_PARSERS = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("d-M-yyyy"),
            DateTimeFormatter.ofPattern("yyyy/M/d")
    };

    /** Recognized input patterns for parsing LocalDateTime. */
    private static final DateTimeFormatter[] DATETIME_PARSERS = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"),
            DateTimeFormatter.ISO_LOCAL_DATE_TIME
    };

    /**
     * Attempts to parse an input string as a {@link LocalDateTime}.
     *
     * @param input The raw date-time string.
     * @return The parsed {@link LocalDateTime}, or null if the string cannot be parsed.
     */
    public static LocalDateTime parseLocalDateTime(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }
        String trimmed = input.trim();
        for (DateTimeFormatter formatter : DATETIME_PARSERS) {
            try {
                return LocalDateTime.parse(trimmed, formatter);
            } catch (DateTimeParseException ignored) {
                // Try next pattern
            }
        }
        return null;
    }

    /**
     * Attempts to parse an input string as a {@link LocalDate}.
     *
     * @param input The raw date string.
     * @return The parsed {@link LocalDate}, or null if the string cannot be parsed.
     */
    public static LocalDate parseLocalDate(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }
        String trimmed = input.trim();
        for (DateTimeFormatter formatter : DATE_PARSERS) {
            try {
                return LocalDate.parse(trimmed, formatter);
            } catch (DateTimeParseException ignored) {
                // Try next pattern
            }
        }
        return null;
    }

    /**
     * Formats a date or date-time string into a human-readable display representation.
     * If the input is recognized as a date or date-time, it is formatted nicely (e.g. "Aug 30 2026").
     * Otherwise, the original input string is returned unchanged.
     *
     * @param input The date, date-time, or raw text string.
     * @return Formatted string for UI display.
     */
    public static String formatForDisplay(String input) {
        if (input == null) {
            return "";
        }
        String trimmed = input.trim();
        LocalDateTime ldt = parseLocalDateTime(trimmed);
        if (ldt != null) {
            return ldt.format(DISPLAY_DATETIME_FORMATTER);
        }
        LocalDate ld = parseLocalDate(trimmed);
        if (ld != null) {
            return ld.format(DISPLAY_DATE_FORMATTER);
        }
        return trimmed;
    }

    /**
     * Formats a date or date-time string into a standardized storage representation.
     * Standardizes date and date-time values into ISO-compatible formats for disk storage.
     * If not recognized, returns the original input string.
     *
     * @param input The date, date-time, or raw text string.
     * @return Standardized string for file storage.
     */
    public static String formatForStorage(String input) {
        if (input == null) {
            return "";
        }
        String trimmed = input.trim();
        LocalDateTime ldt = parseLocalDateTime(trimmed);
        if (ldt != null) {
            return ldt.format(STORAGE_DATETIME_FORMATTER);
        }
        LocalDate ld = parseLocalDate(trimmed);
        if (ld != null) {
            return ld.format(STORAGE_DATE_FORMATTER);
        }
        return trimmed;
    }
}
