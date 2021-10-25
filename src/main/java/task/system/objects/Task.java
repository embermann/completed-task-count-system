package task.system.objects;

import task.system.dao.DataRetrieval;
import task.system.domain.OperationObj;
import task.system.domain.TaskObj;
import task.system.utils.StateEnum;
import task.system.utils.Utils;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Task {

    private DataRetrieval dataRetrieval;

    public Task() {
        dataRetrieval = new DataRetrieval();
    }

    public void createTask() {
        Scanner in = new Scanner(System.in);

        System.out.println("Here you can create new task. Just follow instructions\n");

        System.out.print("Input task description: ");
        String description = in.nextLine();

        int task_id = dataRetrieval.insertNewTask(description);

        System.out.printf("\nYour new task ID: %d\n", task_id);

    }

    public void performTaskUpdate(int taskOperationId) {

        List<OperationObj> operationList = dataRetrieval.getTaskOperationList(taskOperationId);
        boolean allTasksCompleted = true;
        int taskPrice = 0;

        StateEnum taskState;

        for (OperationObj operationObj : operationList) {

            if (operationObj.getIdState() != StateEnum.COMPLETED.getId()) {
                allTasksCompleted = false;
            }

            taskPrice += operationObj.getTotalCost();

        }

        if (allTasksCompleted) {
            taskState = StateEnum.COMPLETED;
        } else {
            taskState = StateEnum.IN_PROGRESS;
        }

        dataRetrieval.updateTaskData(taskState, taskPrice, taskOperationId);

    }

    public void uncompletedTaskList() {
        System.out.println("This is uncompleted task list\n");

        List <TaskObj> taskList = dataRetrieval.getTaskList();

        for (TaskObj task : taskList) {

            System.out.printf("Task id: %d\t|\t Description: %s\t|\t Task price: %d\t|\t State: %s\t\n", task.getIdTask(), task.getDescription(), task.getPrice(), StateEnum.getByID(task.getIdState()).getName());

        }

    }

    private String getDateStr() {

        Scanner in = new Scanner(System.in);
        String year, month, day;

        System.out.print("Year: ");
        year = in.next();
        System.out.print("Month: ");
        month = in.next();
        System.out.print("Day: ");
        day = in.next();

        return Utils.constructSqlDateStr(year, month, day);
    }

    public void completedTaskListInPeriod() {

        Date from, to;

        System.out.println("\nPlease input period FROM date");
        from = Date.valueOf(getDateStr());
        System.out.println("\nPlease input period TO date");
        to = Date.valueOf(getDateStr());

        List<TaskObj> taskList= dataRetrieval.getTaskListInPeriod(from, to);

        System.out.println("\nTask list in period:");

        for (TaskObj task : taskList) {

            System.out.printf("Task id: %d\t|\t Description: %s\t|\t Price: %d\t|\t State: %s\t|\t Completion date: %s\n",
                    task.getIdTask(),
                    task.getDescription(),
                    task.getPrice(),
                    StateEnum.getByID(task.getIdState()).getName(),
                    task.getCompletionDate().toString());

        }

    }

    public void completedTaskTotalCostInPeriod() {
        Date from, to;

        System.out.println("\nPlease input period FROM date");
        from = Date.valueOf(getDateStr());
        System.out.println("\nPlease input period TO date");
        to = Date.valueOf(getDateStr());

        List<TaskObj> taskList= dataRetrieval.getTaskListInPeriod(from, to);
        int totalCost = 0;

        for (TaskObj task : taskList) {
            totalCost += task.getPrice();

        }

        System.out.printf("\nTask total cost in period: %d", totalCost);


    }


}
