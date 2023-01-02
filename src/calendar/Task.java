package calendar;

import calendar.exceptions.WrongInputException;
import util.ValidateUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task {
    private String title;
    private String description;
    private TaskType taskType;
    private LocalDateTime firstDate;
    private boolean archived;
    private static Integer counter = 1;
    private final Integer id;

    public Task(String title, String description, TaskType taskType, LocalDateTime LocalDateTime) throws WrongInputException {
        this.title = ValidateUtils.checkString(title);
        this.description = ValidateUtils.checkString(description);
        this.taskType = taskType;
        this.firstDate = LocalDateTime;
        this.archived = false;
        id = counter++;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public LocalDateTime getFirstDate() {
        return firstDate;
    }

    public boolean isArchived() {
        return archived;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return isArchived() == task.isArchived() && Objects.equals(getTitle(), task.getTitle()) && Objects.equals(getDescription(), task.getDescription()) && getTaskType() == task.getTaskType() && Objects.equals(getFirstDate(), task.getFirstDate()) && Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDescription(), getTaskType(), getFirstDate(), isArchived(), id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", taskType=" + taskType +
                ", firstDate=" + firstDate +
                ", archived=" + archived +
                ", id=" + id +
                '}';
    }
}
