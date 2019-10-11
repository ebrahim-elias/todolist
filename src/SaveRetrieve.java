/** these class is for saving and retrieving the task list to and from a file 
 * in the OS.
  */ 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SaveRetrieve {
  private static final String FNAME = "/Users/ebrahimelias/Desktop/github/todolist.txt";
  private List<Task> list;
  
  public void saveToFile(list){
      Charset charset = StandardCharsets.US_ASCII;
      Path path = Paths.get(FNAME);
      try (BufferedReader reader = Files.newBufferedReader(path, charset)){
          for(Task item : list) {

              String response = reader.;
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
  public List<Task> retrieveFromFile(String fileName){

  }
  
}
