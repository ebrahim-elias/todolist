/** these class is for saving and retrieving the task list to and from a file 
 * in the OS.
  */ 
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class SaveRetrieve {
    private static final String FNAME = "/Users/ebrahimelias/Desktop/github/todolist.txt";
    private BufferedWriter writer;
    private Task task;
    private TodoList list;

    public void saveToFile(List<Task> list) throws IOException {
       try
    {
           // Create a file object.
           File file = new File(FNAME);
           // Create a file writer object with the file.
           FileWriter fileWriter = new FileWriter(file);
           // Create a file object with the file writer.
            writer = new BufferedWriter(fileWriter);
            // Prepare list items to be stored in the file.
            if (list != null) {
                for (Task task : list) {
                    String message = task.getFileDetails();
                    writer.write(message+" \n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            // free the resources.
            writer.close();
        }
    }

   public TodoList retrieveFromFile() {
        list = new TodoList();
        try (FileReader reader = new FileReader(FNAME);
             BufferedReader br = new BufferedReader(reader)) {
            // read line by line
            String line;
            while ((line = br.readLine()) != null) {
                String [] str = line.split(",");
                List<String> al = Arrays.asList(str);
                String title = al.get(0);
                String project = al.get(1);
                String date = al.get(2);
                LocalDate l = dateConvert(date);
                String status = al.get(3);
                boolean ss = statusConvert(status);
                task = new Task(title,project,l,ss);
                list.add(task);
            }
            return list;

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        return null;
   }
    private boolean statusConvert(String s){
        if (s.contains("false")){
            return false;
        } else { return true;}
    }
    private LocalDate dateConvert(String lo){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(lo, formatter);
        return  localDate;
    }
    }

