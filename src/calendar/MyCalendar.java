package calendar;

import calendar.exceptions.WrongInputException;
import util.ValidateUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class MyCalendar {

    private static final Map<Integer, Repeatable> actualTask = new HashMap<>();

    public static void addTask(Scanner scanner) {
        try {
            scanner.nextLine();
            System.out.println("Введите название задачи");
            String title = ValidateUtils.checkString(scanner.nextLine());
            System.out.println("Введите описание задачи");
            String description = ValidateUtils.checkString(scanner.nextLine());
            System.out.println("Введите тип задачи: 0 - Рабочая, 1 - Личная");
            TaskType taskType = TaskType.values()[scanner.nextInt()];
            System.out.println("Введите повторяемость задачи: 0 - Однократная, 1 - Ежедневная, 2 - Еженедельная, 3 - Ежемесячная, 4 - Ежегодная");
            int occurance = scanner.nextInt();
            System.out.println("Введите дату dd.MM.yyyy HH:nn");
            scanner.nextLine();
            createEvent(scanner, title, description, taskType, occurance);
            System.out.println("Для выхода нажмите Enter\n");
            scanner.nextLine();
        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createEvent(Scanner scanner, String title, String description, TaskType taskType, int occurance) {
        try {
            LocalDateTime eventDate = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:nn"));
            Repeatable task = null;
            try {
                task = createTask(occurance,title, description, taskType, eventDate);
                System.out.println("Создана задача " + task);
            } catch ( WrongInputException e) {
                System.out.println(e.getMessage());
            }
        } catch (DateTimeParseException e) {
            System.out.println("Проверьте формат даты dd.MM.yyyy HH:nn и попробуйте еще раз");
            createEvent(scanner, title, description, taskType, occurance);
        }
    }

    private static Repeatable createTask(int occurance, String title, String description, TaskType taskType, LocalDateTime localDateTime) throws WrongInputException {
    return switch (occurance) {
        case 0 -> {
            OncelyTask oncelyTask = new OncelyTask(title, description, taskType, localDateTime);
            actualTask.put(oncelyTask.getId(), oncelyTask);
            yield oncelyTask;
        }
        case 1 -> {
            DailyTask task = new DailyTask(title, description, taskType, localDateTime);
            actualTask.put(task.getId(), task);
            yield task;
        }
        case 2 -> {
            WeeklyTask task = new WeeklyTask(title, description, taskType, localDateTime);
            actualTask.put(task.getId(), task);
            yield task;
        }
        case 3 -> {
            MonthlyTask task = new MonthlyTask(title, description, taskType, localDateTime);
            actualTask.put(task.getId(), task);
            yield task;
        }
        case 4 -> {
            YearlyTask task = new YearlyTask(title, description, taskType, localDateTime);
            actualTask.put(task.getId(), task);
            yield task;
        }
        default -> null;
    };
    }

    public static void deleteTask(Scanner scanner) {
        System.out.println("Текущие задачи\n");
        printActualTasks();
        System.out.println("Для удаления введите id задачи\n");
        int id = scanner.nextInt();
        if ( actualTask.containsKey(id)) {
            actualTask.remove(id);
            System.out.println("Задача " + id + " удалена\n");
        } else {
            System.out.println("Такой задачи не существует\n");
        }
    }

    public static void printActualTasks() {
        for (Repeatable task : actualTask.values()) {
            System.out.println(task);
        }
    }

    public static void getTasksByDay(Scanner scanner) {
        System.out.println("Введите дату формата dd.MM.yyyy:");
        try {
            String date = scanner.next();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate requestedDate = LocalDate.parse(date, dateFormatter);
            List<Repeatable> foundEvents = findTasksByDate(requestedDate);
            System.out.println("События на " + requestedDate + ": ");
            for (Repeatable task : foundEvents) {
                System.out.println(task);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Проверьте формат даты еще раз");
        }
        scanner.nextLine();
        System.out.println("Для выхода нажмите Enter\n");
    }

    private static List<Repeatable> findTasksByDate (LocalDate date) {
        List<Repeatable> tasks = new ArrayList<>();
        for (Repeatable task : actualTask.values()) {
            if (task.checkOccurance(date.atStartOfDay())) {
                tasks.add(task);
            }
        }
        return tasks;
    }
}
