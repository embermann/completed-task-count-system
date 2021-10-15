package task.system.utils;

public class Utils {

    public static void cleanConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
