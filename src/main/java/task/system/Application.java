package task.system;

import jdk.jshell.execution.Util;
import task.system.objects.Task;
import task.system.utils.DatabaseConnection;
import task.system.utils.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        DatabaseConnection.init();

        Scanner in = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Hello. This is system to control your tasks!");

            System.out.println();
            System.out.println("1.Create new task");
            System.out.println("2.Add operation to task");
            System.out.println("3.Change operation state");
            System.out.println("4.Uncompleted task list");
            System.out.println("5.Completed task list in period");
            System.out.println("6.Completed task total cost in period");
            System.out.println("0.Exit");
            System.out.println();

            System.out.print("Input a number: ");

            int num = in.nextInt();



            switch (num) {

                case 1: {
                    Task.createTask();
                    break;
                }

                case 2: {
                    System.out.println("Add operation to task");
                    break;
                }

                case 3: {
                    System.out.println("Change operation state");
                    break;
                }

                case 4: {
                    System.out.println("Uncompleted task list");
                    break;
                }

                case 5: {
                    System.out.println("Completed task list in period");
                    break;
                }

                case 6: {
                    System.out.println("Completed task total cost in period");
                    break;
                }

                case 0: {
                    System.out.println("Exit");
                    exit = true;
                    break;
                }

                default: System.out.println("Sorry, your input is incorrect");


            }
            Utils.cleanConsole();
        }
    }



}
