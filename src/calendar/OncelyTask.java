package calendar;

import calendar.exceptions.WrongInputException;

import java.time.LocalDateTime;

public class OncelyTask extends Task implements Repeatable{
    public OncelyTask(String title, String description, TaskType taskType, LocalDateTime date) throws WrongInputException {
        super(title, description, taskType, date);
    }

    @Override
    public boolean checkOccurance(LocalDateTime requestedDate) {
        return getFirstDate().toLocalDate().equals(requestedDate.toLocalDate());
    }
}
