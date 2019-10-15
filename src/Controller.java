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
     *
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
                ">> (3) Edit Task (update, remove)\n" +
                ">> (4) Save and Quit\n" +
                ">> ");
    }

    /**
     * make the first choice that kan be taken from the option and handel it separately because of the switch in it.
     *
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
                    sortSelection(dateOrProject);
                    break;
                case "2":
                    // input the title, project, date, from the user interface
                    System.out.println("Enter ToDo List Title: ");
                    String newTitle = readInput(scanner);
                    System.out.println("Enter project related: ");
                    String newProject = readInput(scanner);
                    System.out.println("enter date dd-MM-yyyy:");
                    LocalDate newDate = readDate(scanner);
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
                    String EditOption = scanner.nextLine();
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
     * it calls the tow sorting methods from the TodoList class.
     *
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
     * check the input from the user is not empty (for title and project)
     *
     * @param sc scanner object to teak the value from the user
     * @return the string if not empty
     */
    private String readInput(Scanner sc) {
        String word;
        do {
            word = sc.nextLine();
            if (word.equals("")) {
                System.out.println(">> must have value\n" +
                        ">> Please enter value:\n" +
                        ">>");
            }
        } while (word.equals("")); // return to interring the value if the value is empty
        return word;
    }

    /**
     * validate the date and parse it to localDate format and type with checking is not old
     *
     * @param sc scanner object that insert the input date from the user
     * @return the right format and not old Date (from today)
     */
    private LocalDate readDate(Scanner sc) {
        LocalDate localDate;
        do {
            try {
                // parse the string to localDate format with validating the input and then the old date if its entered
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                localDate = LocalDate.parse(sc.nextLine(), formatter);
                LocalDate today = LocalDate.now();
                if (localDate.compareTo(today) >= 0) continue;
                else {
                    localDate = null;
                    System.out.println("The date is old pleas try again: ");
                }
            } catch (DateTimeParseException e) {
                localDate = null;
                System.out.println("Invalid Value (dd-MM-yyyy) Please Try again: ");
            }
        } while (localDate == null); // return to enter new date if its not right
        return localDate;
    }

    /**
     * the switch be called if the third option have been chosen (Edit the Task).
     *
     * @param value take the value on the user input for chose the right option
     */
    private void editSelection(String value) {
        listClass.printToDoList();
        switch (value) {
            case "1":
                readTaskToEdit(scanner);
                break;
            case "2":
                readTaskToRemove(scanner);
                break;
            default:
                System.out.println("Invalid input");
        }
    }

    /**
     * it reads the number of the task that the user inter then call the selectTask which bring the chosen task from
     * the task list.
     *
     * @param sc scanner object for tacking the task number from the user
     * @return chosen task from the list
     */
    private Task readChosenTask(Scanner sc) {
        Task chosenTask;
        do {
            System.out.println("Enter the number of the task");
            chosenTask = listClass.selectTask(sc.nextLine());
            // validate if the number exist
            if (chosenTask != null) {
                return chosenTask;
            } else System.out.println("The number dose't exist!!..");
        } while (chosenTask == null); // return to enter task number if the number dose not exist
        return null;
    }

    /**
     * read the existing chosen task and send it to update method
     *
     * @param sc for calling the readChosenTask witch teak scanner object as parameter
     */
    private void readTaskToEdit(Scanner sc) {
        Task chosenTask = readChosenTask(sc);
        updateChosenTask(chosenTask);
    }

    /**
     * read the existing chosen task and send it to remove method inside ToDoList class
     *
     * @param sc for calling the readChosenTask witch teak scanner object as parameter
     */
    private void readTaskToRemove(Scanner sc) {
        Task chosenTask = readChosenTask(sc);
        listClass.removeTask(chosenTask);
    }

    /**
     * list a new option if the task is updating and chose the part desired to update
     *
     * @param task the chosen task to be updated
     */

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
                String newTitle = readInput(scanner);
                task.setTitle(newTitle);
                break;
            case "2":
                System.out.println("Enter project related: ");
                String newProject = readInput(scanner);
                task.setProject(newProject);
                break;
            case "3":
                System.out.println("enter date dd-MM-yyyy:");
                LocalDate newDate = readDate(scanner);
                task.setDueDate(newDate);
                break;
            case "4":
                task.setDone(true);
                break;
            default:
                System.out.println("Invalid input");
        }
    }
}





