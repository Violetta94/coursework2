package calendar;

import calendar.exceptions.WrongInputException;

import java.time.LocalDateTime;

public class WeeklyTask extends Task implements Repeatable {


    public WeeklyTask(String title, String description, TaskType taskType, LocalDateTime date) throws WrongInputException {
        super(title, description, taskType, date);
    }

    @Override
    public boolean checkOccurance(LocalDateTime requestedDate) {
        return getFirstDate().getDayOfWeek().equals(requestedDate.getDayOfWeek());
    }

}
