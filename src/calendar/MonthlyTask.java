package calendar;

import calendar.exceptions.WrongInputException;

import java.time.LocalDateTime;

public class MonthlyTask extends Task implements Repeatable{

    public MonthlyTask(String title, String description, TaskType taskType, LocalDateTime date) throws WrongInputException {
        super(title, description, taskType, date);
    }

    @Override
    public boolean checkOccurance(LocalDateTime requestedDate) {
        return getFirstDate().getMonth().equals(requestedDate.getMonth());
    }
}
