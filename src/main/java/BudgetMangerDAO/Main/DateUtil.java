package BudgetMangerDAO.Main;

import java.sql.Date;
import java.util.Scanner;

public class DateUtil {
    // converts user input to sql Date format
    public static Date getDateFormatFromUser(Scanner keyboard) {
        System.out.print("Enter day (1-31): ");
        int day = keyboard.nextInt();
        System.out.print("Enter month (1-12): ");
        int month = keyboard.nextInt();
        System.out.print("Enter year (e.g. 2024): ");
        int year = keyboard.nextInt();
        keyboard.nextLine();

        // formats the inputs into valid Date (YYYY-MM-DD)
        String dateString = String.format("%04d-%02d-%02d", year, month, day);

        return Date.valueOf(dateString);
    }
}
