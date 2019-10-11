/** these class is for saving and retrieving the task list to and from a file 
 * in the OS.
  */ 
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SaveRetrieve {
    private static final String FNAME = "/Users/ebrahimelias/Desktop/github/todolist.txt";
    private TodoList list;
    public SaveRetrieve(){
        list = new TodoList();
    }
  
 /* public void saveToFile(list){
      Charset charset = StandardCharsets.US_ASCII;
      Path path = Paths.get(FNAME);
      try (BufferedReader reader = Files.newBufferedReader(path, charset)){
          for(Task item : list) {

              String response = reader.;
          }
      } catch (IOException e) {
          e.printStackTrace();
      }*/


    public void saveToFile(List<Task> list) throws IOException {
        // Create a file object.
        File file = new File(FNAME);
        // Create a file writer object with the file.
        FileWriter fileWriter = new FileWriter(file);
        // Create a file object with the file writer.
        BufferedWriter writer = new BufferedWriter(fileWriter);

        // Prepare list items to be stored in the file.
        if (list != null){
        for(Task task: list){
            String message = task.getDetails();
            writer.write(message); }
        }
        // free the resources.
        writer.close();
    }

    public List<Task> retrieveFromFile(String fileName) {
        return null;
    }
}
