# User Manual
When the app is started, the following welcome message will show up:

```
Welcome to ToDoLy:
   >> You have 3 tasks todo and 1 tasks are done!
   >> Pick an option:
   >> (1) Show TodoList.TodoList.Task List (by date or project)
   >> (2) Add New TodoList.TodoList.Task
   >> (3) Edit TodoList.TodoList.Task (update, remove)
   >> (4) Save and Quit
   >>
```

*  The same menu will appear after each step from option (1,2,3) ends 
*  In the example above there are 3 tasks waiting and 1 task done, which
is shown in the first line.
*  The user has 4 main options to chose from


## Showing the ToDo List

* When option 1 is chosen in the main menu, the user can choose to
sort their tasks by Date or Project

#### the user will see the following list:
```
 >> (1) Show By Project
 >> (2) Show By Date
 >>
```
* If options 1 is chosen, tasks will be sorted by projects

### Sorted by Projects

```
Title: go to the Doctor, Project: appointment, Due Date: 2019-12-12, Status: Done
Title: buy milk, Project: kitchen, Due Date: 2019-11-11, Status: Waiting
Title: buy eggs, Project: kitchen, Due Date: 2019-11-09, Status: Waiting
Title: go to my child school, Project: meeting, Due Date: 2019-11-11, Status: Waiting
```
* If options 2 is chosenl, tasks will be sorted by date

### Sorted by Date

```
Title: buy eggs, Project: kitchen, Due Date: 2019-11-09, Status: Waiting
Title: buy milk, Project: kitchen, Due Date: 2019-11-11, Status: Waiting
Title: go to my child school, Project: meeting, Due Date: 2019-11-11, Status: Waiting
Title: go to the Doctor, Project: appointment, Due Date: 2019-12-12, Status: Done
```
## Add new TodoList.TodoList.Task
#### In case option 2 is selected a new sequence of options will appear:
```
Enter ToDo List Title:
```
* Here the user can enter a new title and it works the same for other options as well:
```
Enter project related:
enter date dd-MM-yyyy:
```
## Edit the TodoList.TodoList.Task
#### In case option 3 is selected the follwing menu will be shown:
```
 >> (1) Update task
 >> (2) Remove task
 >>
```
### Update the TodoList.TodoList.Task
#### If 1 is selected the tasks will be shown with their unique numbers:
```
1 Title: buy milk, Project: kitchen, Due Date: 2019-11-11, Status: Waiting
2 Title: buy eggs, Project: kitchen, Due Date: 2019-11-09, Status: Waiting
3 Title: go to the Doctor, Project: appointment, Due Date: 2019-12-12, Status: Done
4 Title: go to my child school, Project: meeting, Due Date: 2019-11-11, Status: Waiting
Enter the number of the task
```
* For each entered number the user choses the task that they want to edit 
* The App will ask the user which part they want to edit with the follwing menu:
```
 >> Update (1) Title:
 >> Update (2) Project:
 >> Update (3) Date:
 >> (4) mark is done
 >> 
```
### Removing the TodoList.TodoList.Task
 * If  2  is selected the list of tasks will be shown again 
 * To remove the task the user needs enter the number of the task they want to remove

## Exit the ToDoList App
 If 4 is selected the App will save the changes that have been done into a TXT file and exit .
 
 
