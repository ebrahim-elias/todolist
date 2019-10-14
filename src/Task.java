/**
 * The Task Class is represent the new task how it interact with the system and
 * what it must include (title,project,date,status).
 *
 *  @author Ebrahim Elias
 *  @version 19.10.02.1
 */


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

public class Task {

    private String title;
    private String toProject;
    private boolean done;
    private LocalDate dueDate; //TODO LocalDate


    public Task() {
    }
    public Task(String title, String toProject, LocalDate dueDate, boolean status) {
        this.title = title;
        this.toProject = toProject;
        this.dueDate = dueDate;
        this.done = status;
    }

    public Task(String title, String toProject, LocalDate dueDate) {
        this.title = title;
        this.toProject = toProject;
        this.dueDate = dueDate;
        done = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsDone() {
        if (done) {
            return "Done";
        } else return "Waiting";
    }

    boolean isDone() {
        return done;
    }


    public void setDone(boolean done) {
        this.done = done;
    }

    public String getToProject() {
        return toProject;
    }

    public void setProject(String toProject) {
        this.toProject = toProject;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getDetails() {

        return "Title: " + title + ", Project: " + toProject + ", Due Date: " + dueDate + ", Status: " + getIsDone();
    }
    public String getFileDetails(){
        return title+","+toProject+","+dueDate+","+done;
    }

}




