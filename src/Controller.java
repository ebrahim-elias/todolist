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

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class Controller {
    private TodoList listClass;
    private Scanner scanner;
    private SaveRetrieve file;

    /**
     * initializing empty list object and scanner object that take value from command line and file object
     */

    private Controller() {
        listClass = new TodoList();
        scanner = new Scanner(System.in);
        file = new SaveRetrieve();
    }

    /**
     * The Main method
     * @param args
     * @throws IOException throw I/OException for it handel a file save and retrieve.
     */

    public static void main(String[] args) throws IOException {
        Controller c = new Controller();
        SaveRetrieve sr = new SaveRetrieve();
        c.listClass = sr.retrieveFromFile();
        c.optionMain();

    }

    /**
     * the main method in the app it appear in the beginning and after each choice.
     * it shows the number of tasks is done and undone as well.
     */

    private void welcomeMessage() {

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

    /**
     * make the first choice that kan be taken from the option and handel it separately because of the switch in it.
     * @throws IOException it throws exception because the save to file is written here before exiting the app.
     */

    private void optionMain() throws IOException {
        boolean finished = false;                            // local variable for exit the app
        while (!finished) {
            welcomeMessage();
            String optionAsString = scanner.nextLine();
            switch (optionAsString) {
                case "1":
                    System.out.println(">> (1) Show By Project\n" +
                            ">> (2) Show By Date\n" +
                            ">> ");
                    String dateOrProject = scanner.nextLine();
                    // call another switch for showing the list
                    sortExtension(dateOrProject);
                    break;
                case "2":
                    System.out.println("Enter ToDo List Title: ");
                    String newTitle = checkInput(scanner);
                    System.out.println("Enter project related: ");
                    String newProject = checkInput(scanner);
                    System.out.println("enter date dd-MM-yyyy:");
                    LocalDate newDate = checkDate(scanner);
                    // Creating new task in the list after validate and convert the input from the command line
                    listClass.add(newTitle, newProject, newDate);
                    listClass.PrintToDoList();
                    break;
                case "3":
                    if (listClass.getList().isEmpty()){
                        System.out.println("No task to edit");
                        continue;
                    }
                    System.out.println(">> (1) Update task\n" +
                            ">> (2) Remove task\n" +
                            ">> ");
                    String EditOption = scanner.nextLine();
                    // call another switch for editing the chosen task
                    editExtension(EditOption);
                    break;
                case "4":
                    // save and change the local var for exiting
                    file.saveToFile(listClass.getList());
                    finished = true;
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

    /**
     * the switch be called if the first option have been chosen (show the list).
     * it calls the tow sorting methods from the TodoList class.
     * @param value take the value on the user input for chose the right option
     */

    private void sortExtension(String value) {
        switch (value) {
            case "1":
                listClass.sortListByProject();
                welcomeMessage();
                break;
            case "2":
                listClass.sortListByDate();
                welcomeMessage();
                break;
            default:
                System.out.println("Invalid input");
                welcomeMessage();
        }
    }

    /**
     * the switch be called if the third option have been chosen (Edit the Task).
     * @param value take the value on the user input for chose the right option
     */

    private void editExtension(String value) {
        listClass.PrintToDoList();
        switch (value) {
            case "1":
                bringTaskToEdit(scanner);
                break;
            case "2":
                bringTaskToRemove(scanner);
                break;
            default:
                System.out.println("Invalid input");
                welcomeMessage();
        }
    }
    public Task bringChosenTask(Scanner sc){
        Task chosenTask;
        do {
            System.out.println("Enter the number of the task");
            chosenTask = listClass.bringTask(sc.nextLine());
            if (chosenTask != null) {
                return chosenTask;
            } else System.out.println("The number dose't exist!!..");
        }while (chosenTask == null);
        welcomeMessage();
        return null;
    }
    private void bringTaskToEdit(Scanner sc){
           Task chosenTask = bringChosenTask(sc);
           updateChosenTask(chosenTask);
           welcomeMessage();
    }
    private void bringTaskToRemove(Scanner sc){
        System.out.println("Enter the number of the task");
        Task chosenTask = listClass.bringTask(sc.nextLine());
        if (chosenTask != null) {
            listClass.removeTask(chosenTask);
            welcomeMessage();
        } else System.out.println("The number dose't exist!!..");
    }


    private String checkInput(Scanner sc) {
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

    private void updateChosenTask(Task task) {
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
                break;
            default:
                System.out.println("Invalid input");
        }
        return;
    }
    private LocalDate checkDate(Scanner sc)
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





