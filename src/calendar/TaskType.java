package calendar;

public enum TaskType {
   WORK(0),
   PRIVATE(1);

    public final int value;

    TaskType(final int value) {
        this.value = value;
    }
}
