/**
 * Your task is to build a Todo list Application.
 * The application allows a user to create new tasks,
 * assign them a title and due date, and choose a project for that task to belong to.
 * using a text based user interface via the command-line.
 * Once they are using the application, the user is able to edit, mark as done or remove tasks.
 * They can also quit and save the current task list to file,
 * and then restart the application with the former state restored.
 *
 * @author Ebrahim Elias
 * @version 19.10.02.1
 */


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class Controller {
    private TodoList listClass;
    private Scanner scanner;

    public static void main(String[] args) {
        Controller c = new Controller();
        c.welcomeMessage();
        c.optionMain();

    }

    public Controller() {
        listClass = new TodoList();
        scanner = new Scanner(System.in);
    }


    public void welcomeMessage() {

        System.out.println(" _____________________________________________\n " +
                ">> Welcome to ToDoLy\n" +
                ">> You have " + listClass.countUndoneTask() + " tasks todo and " + listClass.countDoneTask() + " tasks are done!\n" +
                ">> Pick an option:\n" +
                ">> (1) Show Task List (by date or project)\n" +
                ">> (2) Add New Task\n" +
                ">> (3) Edit Task (update, mark as done, remove)\n" +
                ">> (4) Save and Quit\n" +
                ">> ");

    }


    public void optionMain() {
        boolean finished = false;
        while (!finished) {
            String optionAsString = scanner.nextLine();

            switch (optionAsString) {
                case "1":
                    System.out.println(">> (1) Show By Project\n" +
                            ">> (2) Show By Date\n" +
                            ">> ");
                    String dateOrProject = scanner.nextLine();
                    sortExtension(dateOrProject);
                    break;
                case "2":
                    System.out.println("Enter ToDo List Title: ");
                    String newTitle = checkInput(scanner);
                    System.out.println("Enter project related: ");
                    String newProject = checkInput(scanner);
                    System.out.println("enter date dd-MM-yyyy:");
                    LocalDate newDate = checkDate(scanner);
                        listClass.add(newTitle, newProject, newDate);
                        listClass.PrintToDoList();

                    welcomeMessage();
                    break;
                case "3":
                    System.out.println(">> (1) Update task\n" +
                            ">> (2) Remove task\n" +
                            ">> ");
                    String EditOption = scanner.nextLine();
                    editExtension(EditOption);
                    break;
                case "4": //SaveRetrieve();
                    finished = true;
                    break;
                default:
                    System.out.println("Invalid input");
                    welcomeMessage();
            }
        }
    }

    public void sortExtension(String value) {
        switch (value) {
            case "1":
                listClass.sortListByProject();
                welcomeMessage();
                break;
            case "2":
                listClass.sortListByDate();
                welcomeMessage();
                break;
        }
    }

    public void editExtension(String value) {
        listClass.PrintToDoList();
        switch (value) {
            case "1":
                bringTask(scanner);
                break;
            case "2":
                /*System.out.println("Enter the number of the task");
                Task chosenTask1 = listClass.updateTask(scanner.nextLine());
                //listClass.removeTask();
                welcomeMessage();*/
                bringTask(scanner);
                //listClass.removeTask() TODO
                break;
        }

    }
    public void bringTask(Scanner sc){
        System.out.println("Enter the number of the task");
        Task chosenTask = listClass.updateTask(sc.nextLine());
        if (chosenTask != null) {
            updateChosenTask(chosenTask);
            welcomeMessage();
        } else System.out.println("The number dose't exist!!..");
    }

    public String checkInput(Scanner sc) {
        String word;
        do {
            word = sc.nextLine();
            if (word.equals("")) {
                System.out.println(">> must have value\n" +
                        ">> Please enter value:\n" +
                        ">>");
            }
        } while (word.equals(""));
        return word;
    }

    public void updateChosenTask(Task task) {
        System.out.println(">> Update (1) Title:\n" +
                ">> Update (2) Project:\n" +
                ">> Update (3) Date:\n" +
                ">> (4) mark is done\n" +
                ">> ");

        String chosenNum = scanner.nextLine();
        switch (chosenNum) {
            case "1":
                System.out.println("Enter ToDo List Title: ");
                String newTitle = checkInput(scanner);
                task.setTitle(newTitle);
                break;
            case "2":
                System.out.println("Enter project related: ");
                String newProject = checkInput(scanner);
                task.setProject(newProject);
                break;
            case "3":
                System.out.println("enter date dd-MM-yyyy:");
                LocalDate newDate = checkDate(scanner);
                task.setDueDate(newDate);
                break;
            case "4":
                task.setDone(true);
        }
        return;
    }
    public LocalDate checkDate(Scanner sc)
    {
        LocalDate localDate;
        do {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                localDate = LocalDate.parse(sc.nextLine(), formatter);
                LocalDate today = LocalDate.now();
                if (localDate.compareTo(today) >= 0) continue;
                else{
                    localDate = null;
                    System.out.println("The date is old pleas try again: ");
                }
            }
            catch (DateTimeParseException e) {
                localDate = null;
                System.out.println("Invalid Value (dd-MM-yyyy) Please Try again: ");
            }
        }while (localDate == null);
        return localDate;
    }
}





