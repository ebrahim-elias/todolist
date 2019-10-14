/**
 * The Task Class is represent the new task how it interact with the system and
 * what it must include (title,project,date,status).
 * the getter and setter for each field.
 *
 * @author Ebrahim Elias
 * @version 19.10.02.1
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
    private LocalDate dueDate;

    /**
     * constructor used when the new task is created from the user side without status witch assumed to be waiting
     * @param title title and discretion of the task
     * @param toProject to witch project is it related
     * @param dueDate what is the time that should be done
     */

    public Task(String title, String toProject, LocalDate dueDate) {
        this.title = title;
        this.toProject = toProject;
        this.dueDate = dueDate;
        done = false;
    }

    /**
     * constructor used when the task is retrieved from the text file
     * @param title title and discretion of the task
     * @param toProject to witch project is it related
     * @param dueDate what is the time that should be done
     * @param status is it done or waiting
     */
    public Task(String title, String toProject, LocalDate dueDate, boolean status) {
        this.title = title;
        this.toProject = toProject;
        this.dueDate = dueDate;
        this.done = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * set the boolean values from true to (Done) and from false to (Waiting)
     * @return string converted value
     */
    public String getIsDone() {
        if (done) {
            return "Done";
        } else return "Waiting";
    }

    public boolean isDone() {
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

    /**
     * @return a string of all the fields in the task (status is calling the getIsDone) for showing them in the app
     */

    public String getDetails() {

        return "Title: " + title + ", Project: " + toProject + ", Due Date: " + dueDate + ", Status: " + getIsDone();
    }

    /**
     * @return a string to be saved in the file so it will be easy to convert when if be retrieved
     */
    public String getFileDetails() {
        return title + "," + toProject + "," + dueDate + "," + done;
    }

}




