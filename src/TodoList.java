/**
 * The class TodoList is the class where all the tasks can listed in a ArrayList
 * of type Task, with tow ways to add and tow ways of sort them for showing the list.
 * the removing and the bringing specific task is written here as well.
 *
 * @author Ebrahim Elias
 * @version 19.10.03.1
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class TodoList {
    private List<Task> list;

    /**
     * constructor that initialize an empty task array list
     */
    public TodoList() {
        list = new ArrayList<>();
    }

    /**
     * add task to the list
     *
     * @param task with the type Task
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * add task to list and is used when adding a new task from the user
     * the status is assumed as undone(waiting)
     *
     * @param title   title from the user
     * @param project project from the user
     * @param newDate date from the user
     */
    public void add(String title, String project, LocalDate newDate) {
        Task task = new Task(title, project, newDate);
        list.add(task);
    }

    /**
     * showing all the elements of the list by the project name
     */
    public void sortListByProject() {
        list.stream()
                .sorted(Comparator.comparing(Task::getToProject))
                .map(Task::getDetails)
                .forEach(System.out::println);
    }

    public List<Task> getList() {
        return list;
    }

    public void setList(List<Task> list) {
        this.list = list;
    }

    /**
     * filter the list by a specific project and show it
     *
     * @param item
     */
    public void filterListByTitleOrProject(String item) {
        for (Task task : list) {
            if (task.getTitle().contains(item)) {
                System.out.println(task);
            }
        }
    }

    /**
     * showing all the elements of the list by the Due Date
     */
    public void sortListByDate() {
        list.stream().sorted(Comparator.comparing(Task::getDueDate))
                .map(Task::getDetails)
                .forEach(System.out::println);
    }

    /**
     * print the list items with the index+1 for avoiding the 0 index so it will showed beginning with 1
     */

    public void printToDoList() {
        for (Task task : list) {
            // calling printing method from task class
            System.out.println((list.indexOf(task) + 1) + " " + task.getDetails());
        }
        System.out.println("there is " + list.size() + " item in the list."); // extra check to the size

    }

    /**
     * read and returning the task from the list by the index number is chosen by the user
     *
     * @param taskNumber chose by the user depending on the index (must be index -1)
     * @return the task from the list
     */
    public Task selectTask(String taskNumber) {
        for (Task task : list) {
            if (list.indexOf(task) == Integer.parseInt(taskNumber) - 1) {
                return task;
            }
        }
        return null;
    }

    /**
     * counting all the tasks from the list which is done
     *
     * @return long number of the tasks
     */
    public long countDoneTask() {
        return list.stream().filter(Task::isDone).count();
    }

    /**
     * counting all the tasks from the list which is undone
     *
     * @return long number of the tasks
     */
    public long countUndoneTask() {
        return list.stream().filter(x -> !x.isDone()).count();
    }

    /**
     * removing the chosen task by the user
     *
     * @param task chosen task
     */
    public void removeTask(Task task) {
        Iterator itr = list.iterator();
        while (itr.hasNext()) {
            Task t = (Task) itr.next();
            if (t.equals(task)) {
                itr.remove();
            }
        }
    }

}
