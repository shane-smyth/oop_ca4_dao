package BudgetMangerDAO.Main;

import java.util.Scanner;

public class MenuUtil {
    public static void displayMenu(String[] menuOptions, String menuTitle) {
        System.out.println(menuTitle);
        for (String option : menuOptions) {
            System.out.println(option);
        }
    }

    public static int getMenuChoice(int numItems) {
        Scanner keyboard = new Scanner(System.in);
        int choice = keyboard.nextInt();
        while (choice < 0 || choice > numItems) {
            System.out.printf("Please enter a valid option (0 - %d)\n", numItems -1);
            choice = keyboard.nextInt();
        }
        return choice;
    }
}
