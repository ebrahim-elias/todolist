
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class TaskTest {
    @Test
    void somthing() {
         Task task;
         TodoList list = new TodoList();

        String s= "do something,12-12-2019,2019-12-12,false";
        String s1 = "the king,elias,2019-11-11,Waiting";

            String[] str = s.split(",");
            List<String> al = Arrays.asList(str);
            String title = al.get(0);
            String project = al.get(1);
            String date = al.get(2);
            LocalDate l = dateConvert(date);
            String status = al.get(3);
            boolean ss = statusConvert(status);
             task = new Task(title,project,l,ss);
             list.add(task);

            System.out.println(task.getTitle()+" "+project+" "+l+" "+ss);
        }
        @Test
        boolean statusConvert(String s){
        if (s.equals("false")){
            return false;
        } else { return true;}
        }
        @Test
        LocalDate dateConvert(String lo){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(lo, formatter);
                return  localDate;
        }
    }
