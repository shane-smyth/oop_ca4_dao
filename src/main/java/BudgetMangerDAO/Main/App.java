package BudgetMangerDAO.Main;

import BudgetMangerDAO.DAOs.ExpenseDao;
import BudgetMangerDAO.DAOs.ExpenseDaoInterface;
import BudgetMangerDAO.DAOs.IncomeDao;
import BudgetMangerDAO.DAOs.IncomeDaoInterface;
import BudgetMangerDAO.DTOs.Expense;
import BudgetMangerDAO.DTOs.Income;
import BudgetMangerDAO.Exceptions.DaoException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.sql.Date;

public class App {
    public static void main(String[] args) throws DaoException {
        Scanner keyboard = new Scanner(System.in);
        ExpenseDaoInterface IExpenseDao = new ExpenseDao();
        IncomeDaoInterface IIncomeDao = new IncomeDao();

        String[] menuOptions = {
                "1. Display All Expenses",
                "2. Add Expense",
                "3. Delete Expense (by ID)",
                "4. Display All Income",
                "5. Add Income",
                "6. Delete Income (by ID)",
                "7. Display Total Expenses & Income for a Month",
                "0. Exit",
        };
        int menuChoice = -1;
        do {
            MenuUtil.displayMenu(menuOptions, "====== Budget Manger ======");
            try {
                menuChoice = MenuUtil.getMenuChoice(menuOptions.length);
                switch (menuChoice) {
                    case 1:
                        List<Expense> expenses = IExpenseDao.getAllExpenses();
                        double totalExpenses = 0;
                        for (Expense expense : expenses) {
                            totalExpenses += expense.getAmount();
                            System.out.println(expense.toString());
                        }
                        System.out.println("\nTotal Expenses: €" + totalExpenses + "\n\n");
                        break;
                    case 2:
                        System.out.println("\nCreate New Expense");
                        System.out.print("Expense Title: ");
                        String expenseTitle = keyboard.nextLine();
                        System.out.print("Expense Amount: ");
                        double expenseAmount = keyboard.nextDouble();
                        keyboard.nextLine();

                        System.out.print("Expense Date: ");
                        Date expenseDate = DateUtil.getDateFormatFromUser(keyboard);
                        System.out.println("\n");
                        Expense expense = new Expense(expenseTitle, expenseAmount, expenseDate);
                        IExpenseDao.addExpense(expense);
                        break;
                    case 3:
                        System.out.print("\nEnter an ID to delete: ");
                        int expenseId = keyboard.nextInt();
                        keyboard.nextLine();
                        IExpenseDao.deleteExpense(expenseId);
                        System.out.println();
                        break;
                    case 4:
                        List<Income> incomes = IIncomeDao.getAllIncomes();
                        double totalIncomes = 0;
                        for (Income income : incomes) {
                            totalIncomes += income.getAmount();
                            System.out.println(income.toString());
                        }
                        System.out.println("\nTotal Income: €" + totalIncomes + "\n\n");
                        break;
                    case 5:
                        System.out.println("\nCreate New Income");
                        System.out.print("Income Title: ");
                        String incomeTitle = keyboard.nextLine();
                        System.out.print("Income Amount: ");
                        double incomeAmount = keyboard.nextDouble();
                        keyboard.nextLine();

                        System.out.print("Income Date: ");
                        Date incomeDate = DateUtil.getDateFormatFromUser(keyboard);
                        System.out.println("\n");
                        Income income = new Income(incomeTitle, incomeAmount, incomeDate);
                        IIncomeDao.addIncome(income);
                        break;
                    case 6:
                        System.out.print("\nEnter an ID to delete: ");
                        int incomeId = keyboard.nextInt();
                        keyboard.nextLine();
                        IIncomeDao.deleteIncome(incomeId);
                        System.out.println();
                        break;
                    case 7:
                        System.out.println("Enter Month (1-12): ");
                        int month = keyboard.nextInt();
                        System.out.println("Enter Year (e.g. 2025): ");
                        int year = keyboard.nextInt();
                        keyboard.nextLine();
                        displayIncomeAndExpensesByMonth(month, year);
                        break;
                    default:
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid option!");
            }
        } while (menuChoice != 0);
    }


    public static void displayIncomeAndExpensesByMonth(int month, int year) throws DaoException {
        ExpenseDaoInterface IExpenseDao = new ExpenseDao();
        IncomeDaoInterface IIncomeDao = new IncomeDao();

        List<Expense> expenses = IExpenseDao.getExpenseByMonth(month, year);
        List<Income> incomes = IIncomeDao.getIncomeByMonth(month, year);

        System.out.println("\n===== Income & Expenses for " + month + "/" + year + " =====");
        System.out.println("Income:");
        double totalExpenses = 0;
        for (Expense expense : expenses) {
            totalExpenses += expense.getAmount();
            System.out.println(expense.toString());
        }
        System.out.println("Total Expenses: €" + totalExpenses+ "\n");
        System.out.println("Expenses:");
        double totalIncomes = 0;
        for (Income income : incomes) {
            totalIncomes += income.getAmount();
            System.out.println(income.toString());
        }
        System.out.println("Total Income: €" + totalIncomes+"\n");

        double balance = totalIncomes - totalExpenses;
        System.out.println("Balance: €" + balance + "\n\n");
    }
}
