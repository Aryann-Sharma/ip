public class Event extends Task {
    protected String date = "";
    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[" + this.getType() + "][" + this.getStatusIcon() + "] " + description + " (" + date + ")";
    }
}
