package arn;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    protected LocalDateTime date;
    protected boolean hasTime = true;
    public Deadline(String description, String date) throws ArnException {
        super(description);
        DateTimeFormatter dateTimeFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            this.date = LocalDateTime.parse(date, dateTimeFmt);
        } catch (DateTimeParseException e1) {
            try {
                LocalDate d = LocalDate.parse(date, dateFmt);
                this.date = d.atStartOfDay();
                this.hasTime = false;
            } catch (DateTimeParseException e2) {
                throw new ArnException("Invalid date format. Use yyyy-mm-dd or yyyy-mm-dd hhmm.");
            }
        }
    }

    public String getType() {
        return "D";
    }

    public String formatDate(boolean pretty) {
        if (date == null) {
            return "";
        }
        if (pretty) {
            return hasTime
                    ? date.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"))
                    : date.toLocalDate().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return hasTime
                    ? date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                    : date.toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }
    @Override
    public String toString() {
        return "[" + this.getType() + "][" + this.getStatusIcon() + "] " + description + " (by " +
                this.formatDate(true) + ")";
    }
}
