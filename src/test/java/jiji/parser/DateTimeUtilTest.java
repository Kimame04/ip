package jiji.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DateTimeUtilTest {

    @Test
    public void parseLocalDate_isoFormat_success() {
        LocalDate date = DateTimeUtil.parseLocalDate("2026-08-30");
        assertNotNull(date);
        assertEquals(LocalDate.of(2026, 8, 30), date);
    }

    @Test
    public void parseLocalDate_slashedFormat_success() {
        LocalDate date = DateTimeUtil.parseLocalDate("2/12/2026");
        assertNotNull(date);
        assertEquals(LocalDate.of(2026, 12, 2), date);
    }

    @Test
    public void parseLocalDate_invalidDate_returnsNull() {
        assertNull(DateTimeUtil.parseLocalDate("not-a-date"));
        assertNull(DateTimeUtil.parseLocalDate(""));
        assertNull(DateTimeUtil.parseLocalDate(null));
    }

    @Test
    public void parseLocalDateTime_validFormats_success() {
        LocalDateTime dt1 = DateTimeUtil.parseLocalDateTime("2026-08-30 1800");
        assertNotNull(dt1);
        assertEquals(LocalDateTime.of(2026, 8, 30, 18, 0), dt1);

        LocalDateTime dt2 = DateTimeUtil.parseLocalDateTime("2/12/2026 18:00");
        assertNotNull(dt2);
        assertEquals(LocalDateTime.of(2026, 12, 2, 18, 0), dt2);
    }

    @Test
    public void parseLocalDateTime_invalidDateTime_returnsNull() {
        assertNull(DateTimeUtil.parseLocalDateTime("not-a-date"));
        assertNull(DateTimeUtil.parseLocalDateTime(""));
        assertNull(DateTimeUtil.parseLocalDateTime(null));
    }

    @Test
    public void formatForDisplay_validDate_formattedString() {
        assertEquals("Aug 30 2026", DateTimeUtil.formatForDisplay("2026-08-30"));
        assertEquals("Dec 02 2026", DateTimeUtil.formatForDisplay("2/12/2026"));
    }

    @Test
    public void formatForDisplay_validDateTime_formattedString() {
        assertEquals("Aug 30 2026, 6:00PM", DateTimeUtil.formatForDisplay("2026-08-30 1800"));
        assertEquals("Dec 02 2026, 6:00PM", DateTimeUtil.formatForDisplay("2/12/2026 18:00"));
    }

    @Test
    public void formatForDisplay_nonDateString_returnsOriginal() {
        assertEquals("Sunday", DateTimeUtil.formatForDisplay("Sunday"));
        assertEquals("tomorrow evening", DateTimeUtil.formatForDisplay("tomorrow evening"));
        assertEquals("", DateTimeUtil.formatForDisplay(null));
    }

    @Test
    public void formatForStorage_validDate_isoDateString() {
        assertEquals("2026-08-30", DateTimeUtil.formatForStorage("2026-08-30"));
        assertEquals("2026-12-02", DateTimeUtil.formatForStorage("2/12/2026"));
    }

    @Test
    public void formatForStorage_validDateTime_isoDateTimeString() {
        assertEquals("2026-08-30 1800", DateTimeUtil.formatForStorage("2026-08-30 1800"));
        assertEquals("2026-12-02 1800", DateTimeUtil.formatForStorage("2/12/2026 18:00"));
    }

    @Test
    public void formatForStorage_nonDateString_returnsOriginal() {
        assertEquals("Sunday", DateTimeUtil.formatForStorage("Sunday"));
        assertEquals("", DateTimeUtil.formatForStorage(null));
    }
}
