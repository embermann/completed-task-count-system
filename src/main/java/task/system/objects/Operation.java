package task.system.objects;

import task.system.dao.DataRetrieval;
import task.system.domain.OperationObj;
import task.system.domain.TaskObj;
import task.system.utils.StateEnum;
import task.system.utils.Utils;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Operation {

    private DataRetrieval dataRetrieval;

    public Operation() {
        dataRetrieval = new DataRetrieval();
    }

    public void newOperationUserData() {

        System.out.println("This is list of existing non completed tasks\n");

        List<TaskObj> list = dataRetrieval.getTaskList();

        for (TaskObj task : list) {

            System.out.printf("Task id: %d\t|\t Task price: %d\t|\t State: %s\n", task.getIdTask(), task.getPrice(), StateEnum.getByID(task.getIdState()).getName());

        }

        System.out.println();

        Scanner in = new Scanner(System.in);
        int id, plannedQuantity, price;

        System.out.print("Input selected task id: ");
        id = in.nextInt();

        System.out.print("\nInput description: ");
        String description = in.next();

        System.out.print("\nInput planned count: ");
        plannedQuantity = in.nextInt();

        System.out.print("\nInput price: ");
        price = in.nextInt();

        System.out.printf("\nYour new task operation ID: %d\n", addNewOperation(id, plannedQuantity, price, description));

    }

    public Integer addNewOperation(int id, int plannedQuantity, int price, String description) {

        OperationObj operation = new OperationObj(
                id,
                StateEnum.PROJECT.getId(),
                description, plannedQuantity,
                price,
                Utils.calculateTotalCost(price, plannedQuantity),
                new Date(System.currentTimeMillis())
        );

        return dataRetrieval.insertNewOperation(operation);

    }


    public void changeOperationState() {

        Scanner in = new Scanner(System.in);
        boolean loop = false;
        int id = 0;

        while (!loop) {

            System.out.print("\nInput selected operation id: ");
            id = in.nextInt();

            StateEnum operationState = dataRetrieval.getOperationState(id);

            if (operationState == null) {
                System.out.println("Sorry, but this operation does not exist");
            } else {
                if (operationState == StateEnum.COMPLETED) {
                    System.out.println("Sorry, but this operation already is completed");
                } else loop = true;
            }
        }

        System.out.print("Input actual quantity: ");
        int actual_quantity = in.nextInt();

        changeState(id, actual_quantity);

        System.out.println("\nCompleted");


    }

    public void changeState(int id, int actual_quantity) {

        OperationObj operationObj = new OperationObj(
                id,
                StateEnum.COMPLETED.getId(),
                actual_quantity,
                Utils.calculateTotalCost(dataRetrieval.getOperationPrice(id), actual_quantity),
                new Date(System.currentTimeMillis())
        );

        dataRetrieval.completeOperation(operationObj);

        Task task = new Task();
        task.performTaskUpdate(operationObj.getIdTaskOperation());
    }

}
