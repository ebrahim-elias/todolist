import TodoList.*;
import listControl.Utilities;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;


class TaskTest {
    private LocalDate date = Utilities.readDate("2019-12-12");
    private String title = "ebrahim";
    private String project = "king";


    @Test
    void getTitle() {
        Task task = new Task(title, project, date);

        assertEquals(title, task.getTitle());
        assertEquals("ebrahim", task.getTitle());
    }

    @Test
    void setTitle() {
        Task task = new Task(title, project, date);
        String anotherTitle = "somthing";
        task.setTitle(anotherTitle);
        assertEquals(anotherTitle, task.getTitle());
    }

    @Test
    void getIsDone() {
        Task task = new Task(title, project, date);
        assertEquals("Waiting", task.getIsDone());

    }

    @Test
    void isDone() {
        Task task = new Task(title, project, date);
        assertFalse(task.isDone());
    }

    @Test
    void setDone() {
        Task task = new Task(title, project, date);
        task.setDone(true);
        assertTrue(task.isDone());
    }

    @Test
    void getToProject() {
        Task task = new Task(title, project, date);

        assertEquals(project, task.getToProject());
        assertEquals("king", task.getToProject());
    }

    @Test
    void setProject() {
        Task task = new Task(title, project, date);
        String anotherProject = "somthing";
        task.setProject(anotherProject);
        assertEquals(anotherProject, task.getToProject());
    }

    @Test
    void getDueDate() {
        Task task = new Task(title, project, date);
        assertEquals(date, task.getDueDate());
    }

    @Test
    void setDueDate() {
        LocalDate date1 = Utilities.readDate("11-11-2019");
        Task task = new Task(title, project, date);
        task.setDueDate(date1);
        assertEquals(date1, task.getDueDate());
    }

    @Test
    void getDetails() {
        Task task = new Task(title, project, date);
        assertEquals("Title: ebrahim, Project: king, Due Date: 2019-12-12, Status: Waiting", task.getDetails());
    }
}
