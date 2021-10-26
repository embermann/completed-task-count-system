package task.system;

import task.system.objects.Operation;
import task.system.objects.Task;
import task.system.utils.Utils;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        boolean exit = false;

        Task task = new Task();
        Operation operation = new Operation();

        System.out.println("\nHello. This is system to control your tasks!");

        while (!exit) {

            System.out.println("\n--------------------------------------------------------------------------");
            System.out.println("1.Create new task");
            System.out.println("2.Add operation to task");
            System.out.println("3.Change operation state");
            System.out.println("4.Uncompleted task list");
            System.out.println("5.Completed task list in period");
            System.out.println("6.Completed task total cost in period");
            System.out.println("0.Exit\n");

            System.out.print("Input a number: ");

            int num = in.nextInt();

            switch (num) {

                case 1: {
                    task.createTask();
                    break;
                }

                case 2: {
                    operation.newOperationUserData();
                    break;
                }

                case 3: {
                    operation.changeOperationState();
                    break;
                }

                case 4: {
                    task.uncompletedTaskList();
                    break;
                }

                case 5: {
                    task.completedTaskListInPeriod();
                    break;
                }

                case 6: {
                    task.completedTaskTotalCostInPeriod();
                    break;
                }

                case 0: {
                    exit = true;
                    break;
                }

                default: System.out.println("Sorry, your input is incorrect");

            }
        }
    }



}
