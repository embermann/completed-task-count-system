package task.system.utils;

public class Utils {

    public static void cleanConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static int calculateTotalCost(int price, int quantity) {

        return price * quantity;

    }

    public static String constructSqlDateStr(String year, String month, String day) {
        return year + "-" + month + "-" + day;
    }

}
