package calendar;

import calendar.exceptions.WrongInputException;

import java.time.LocalDateTime;

public class YearlyTask extends Task implements Repeatable{

    public YearlyTask(String title, String description, TaskType taskType, LocalDateTime date) throws WrongInputException {
        super(title, description, taskType, date);
    }

    @Override
    public boolean checkOccurance(LocalDateTime requestedDate) {
        return getFirstDate().getYear() == requestedDate.getYear();
    }
}
