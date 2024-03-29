package listControl;
import TodoList.*;
import java.time.LocalDate;
import java.util.Scanner;
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
public class Controller  {
    private TodoList listClass;
    private Scanner scanner;
    private SaveRetrieve file;
    private LocalDate newDate;
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
     * @param args the arguments
     */
    public static void main(String[] args) {
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
                ">> (1) Show TodoList List (by date or project)\n" +
                ">> (2) Add New Task\n" +
                ">> (3) Edit TodoList (update, remove)\n" +
                ">> (4) Save and Quit\n" +
                ">> ");
    }
    /**
     * make the first choice that kan be taken from the option and handel it separately because of the switch in it.
     */
    private void optionMain() {
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
                    sortSelection(dateOrProject);
                    break;
                case "2":
                    // input the title, project, date, from the user interface
                    System.out.println("Enter ToDo List Title: ");
                    String newTitle = attempt(scanner);
                    System.out.println("Enter project related: ");
                    String newProject = attempt(scanner);
                    System.out.println("enter date yyyy-MM-dd:");
                    // entering the date again until its in the same format or value
                    do {
                        newDate = Utilities.readDate(scanner.nextLine());
                    } while (newDate == null);
                    // Creating new task in the list after validate and convert the input from the command line
                    listClass.add(newTitle, newProject, newDate);
                    listClass.printToDoList();
                    break;
                case "3":
                    // checking the list is not empty
                    if (listClass.getList().isEmpty()) {
                        System.out.println("No task to edit");
                        continue;
                    }
                    System.out.println(">> (1) Update task\n" +
                            ">> (2) Remove task\n" +
                            ">> ");
                    String EditOption = attempt(scanner);
                    // call another switch for editing the chosen task
                    editSelection(EditOption);
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
     * it calls the tow sorting methods from the TodoList.TodoList class.
     * @param value take the value on the user input for chose the right option
     */
    private void sortSelection(String value) {
        switch (value) {
            case "1":
                listClass.sortListByProject();
                break;
            case "2":
                listClass.sortListByDate();
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }
    /**
     * the switch be called if the third option have been chosen (Edit the TodoList.TodoList.Task).
     * @param value take the value on the user input for chose the right option
     */
    private void editSelection(String value) {

        switch (value) {
            case "1":
                listClass.printToDoList();
                updateChosenTask();
                break;
            case "2":
                listClass.printToDoList();
                Task chosenTask = readChosenTask(scanner);
                listClass.removeTask(chosenTask);
                break;
            default:
                System.out.println("Invalid input");
        }
    }
    /**
     * it reads the number of the task that the user inter then call the selectTask which bring the chosen task from
     * the task list.
     * @param sc scanner object for tacking the task number from the user
     * @return chosen task from the list
     */
    private Task readChosenTask(Scanner sc) {
        Task chosenTask;
        int i =1;
        do {
            System.out.println("Enter the number of the task");
            chosenTask = listClass.selectTask(sc.nextLine());
            // validate if the number exist
            if (chosenTask != null) {
                return chosenTask;
            } else {
                System.out.println("Invalid number or value");
                i++;
            }
        } while (i <= 3); // return to enter task number if the number dose not exist and after 3 attempts
        optionMain();
        return null;
    }
    /**
     * list a new option if the task is updating and chose the part desired to update
     */
    private void updateChosenTask() {
        Task task = readChosenTask(scanner);
        assert task != null;
        System.out.println(">> Update (1) Title:\n" +
                ">> Update (2) Project:\n" +
                ">> Update (3) Date:\n" +
                ">> (4) mark is done\n" +
                ">> (5) return to the main menu\n" +
                ">> ");
        String chosenNum = scanner.nextLine();
        switch (chosenNum) {
            case "1":
                System.out.println("Enter ToDo List Title: ");
                String newTitle = attempt(scanner);
                task.setTitle(newTitle);
                break;
            case "2":
                System.out.println("Enter project related: ");
                String newProject = attempt(scanner);
                task.setProject(newProject);
                break;
            case "3":
                System.out.println("enter date dd-MM-yyyy:");
                do {
                    newDate = Utilities.readDate(scanner.nextLine());
                } while (newDate == null);
                task.setDueDate(newDate);
                break;
            case "4":
                task.setDone(true);
                break;
            case "5":
                return;
            default:
                System.out.println("Invalid input");
        }
    }
    /**
     * making only 3 attempts to input a new title and project with both new abd update task
     * @param sc the user input
     * @return the input if it is less than 3 attempts
     */
    private String attempt(Scanner sc) {
        int attemptNam = 0;
        String input;
        do {
            input = Utilities.readInput(sc.nextLine());
            attemptNam++;
            if (attemptNam > 2) {
                optionMain();
            }
        } while (input.equals(""));
        return input;
    }

}





