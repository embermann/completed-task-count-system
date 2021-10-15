package task.system.objects;

import task.system.utils.DatabaseConnection;
import task.system.utils.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Task {

    private String description;

    public static void createTask() {
        Utils.cleanConsole();

        Scanner in = new Scanner(System.in);

        System.out.println("Here you can create new task. Just follow instructions");
        System.out.println();
        System.out.println();

        System.out.print("Input task description: ");
        in.nextLine();

        Connection connection = DatabaseConnection.getConnection();
        // запись данных в бд

        System.out.println();
        System.out.println();
        System.out.printf("Your new task ID: %c", '0');
        System.out.println();
        System.out.println();



    }

    private void insertNewTaskInDB() {



//        DriverManager.

//            try (Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem://localhost/testdb", "SA", "")) {
//
//        }
//            catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

}
