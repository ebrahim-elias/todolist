
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TodoList {
    private List<Task> list;


    public TodoList() {
        list = new ArrayList<>();
    }

    public void add(Task task) {
        list.add(task);
    }

    public void add(String title, String project, LocalDate newDate) {
            Task task = new Task(title, project, newDate);
            list.add(task);
    }

    public void sortListByProject() {
        list.stream()
                .sorted(Comparator.comparing(Task::getToProject))
                .map(task ->
                        task.getTitle() + " " + task.getToProject() + " " + task.getDueDate() + " " + task.isDone())
                .forEach(System.out::println);
    }

    public List<Task> getList() {
        return list;
    }

    public void filterListByTitleOrProject(String item) {
        for (Task task : list) {
            if (task.getTitle().contains(item)) {
                System.out.println(task);
            }
        }
    }

    public void sortListByDate() {
        list.stream().sorted(Comparator.comparing(Task::getDueDate))
                .map(task -> task.getTitle() + " " + task.getToProject() + " " + task.getDueDate() + " " + task.isDone())
                .forEach(System.out::println);
    }


    public void PrintToDoList() {
        for (Task task : list) {
            System.out.println((list.indexOf(task) + 1) + " " + task.getDetails());
        }
        System.out.println("there is " + list.size() + " item in the list.");

    }

    public Task updateTask(String taskNumber) {
        for (Task task : list) {
            if (list.indexOf(task) == Integer.parseInt(taskNumber) - 1) {
                return task;
            }
        }
        return null;
    }

    public long countDoneTask() {
        return list.stream().filter(Task::isDone).count();
    }

    public long countUndoneTask() {
        return list.stream().filter(x -> !x.isDone()).count();
    }

    public void removeTask(Task task){
        Iterator itr = list.iterator();
        while (itr.hasNext())
        {
            Task t = (Task) itr.next();
            if (t.equals(task)){
                itr.remove();
            }
        }
    }

}
