import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodoList {
    private List<Task> list;
    private static int taskDone;
    private static int taskWaiting;

    public TodoList() {
        list = new ArrayList<>();
    }
    public void add(Task task){
        list.add(task);
    }

    public static int getTaskDone() {
        return taskDone;
    }

    public static int getTaskWaiting() {
        return taskWaiting;
    }

    public List<Task> getList() {
        return list;
    }
    public void filterListByTitleOrProject(String item){
        for (Task task: list) {
           if (task.getTitle().contains(item)){
               System.out.println(task);
           }
        }
    }
    public void filterListByDate(Date item){
        for (Task task: list) {
            if (task.getTitle().contains((CharSequence) item)){
                System.out.println(task);
            }
        }
    }
    public void showStatus() {
        for (Task task : list) {
            if (task.getIsDone().equals("Done")) {
                taskDone++;
            } else {
                taskWaiting++;
            }
        }

    }
    public void  PrintToDoList() {
        for (Task task: list) {
            System.out.println(task.getTitle()+" "+task.getToProject()+" "+task.getDueDate()+" "+task.getIsDone());

        }
        System.out.println(list.size());

    }
}
