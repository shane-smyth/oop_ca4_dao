package BudgetMangerDAO.DAOs;

import BudgetMangerDAO.DTOs.Expense;
import BudgetMangerDAO.Exceptions.DaoException;

import java.util.List;

public interface ExpenseDaoInterface {
    // Read all expense records
    public List<Expense> getAllExpenses() throws DaoException;

    // add new Expense
    public void addExpense(Expense expense) throws DaoException;

    // delete Expense by id
    public void deleteExpense(int id) throws DaoException;

    // get expense by month
    public List<Expense> getExpenseByMonth(int month, int year) throws DaoException;
}
