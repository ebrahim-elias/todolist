package listControl;
import TodoList.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
/**
 * these class is for saving and retrieving the task list to and from a text file
 * in the OS.
 * @author Ebrahim Elias
 * @version 19.10.02.1
 */
class SaveRetrieve {
    private static final String FNAME = "./todolist.txt";  //using the dynamic path

    /**
     * The method is to save the actual list to text file
     * @param list from type list of task
     */
    void saveToFile(List<Task> list)  {
        try {
            // Create a file object.
            File file = new File(FNAME);
            // Create a file writer object with the file.
            FileWriter fileWriter = new FileWriter(file);
            // Create a file object with the file writer.
            BufferedWriter writer = new BufferedWriter(fileWriter);
            // Prepare list items to be stored in the file.
            if (list != null) {
                for (Task task : list) {
                    String message = task.getFileDetails();
                    writer.write(message + " \n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * retrieving the list from the text file
     * @return list from type ToDoList
     */
    TodoList retrieveFromFile() {
        TodoList list = new TodoList();
        try (FileReader reader = new FileReader(FNAME);
             BufferedReader br = new BufferedReader(reader)) {
            // read line by line
            String line;
            while ((line = br.readLine()) != null) {
                String[] str = line.split(",");
                List<String> al = Arrays.asList(str);
                // tacking out the values from the list by index
                String title = al.get(0);
                String project = al.get(1);
                String date = al.get(2);
                // convert the date from string to localDate
                LocalDate localDate = dateConvert(date);
                String status = al.get(3);
                // convert the status from string to boolean
                boolean statusAsBoolean = statusConvert(status);
                // make new task with the constructor and add it to the list from type ToDoList
                Task task = new Task(title, project, localDate, statusAsBoolean);
                list.add(task);
            }
            return list;
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        return null;
    }
    /**
     * converting the string to boolean to be matched with the task constructor
     * @param status as string
     * @return status as boolean
     */
    private boolean statusConvert(String status) {
        return !status.contains("false");
    }
    /**
     * converting the string to LocalDate to be matched with the task constructor
     * @param localDate1 as string
     * @return localdate as type LocalDate
     */
    private LocalDate dateConvert(String localDate1) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(localDate1, formatter);
    }
}

