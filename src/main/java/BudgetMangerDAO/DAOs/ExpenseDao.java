package BudgetMangerDAO.DAOs;

import java.sql.*;
import BudgetMangerDAO.DTOs.Expense;
import BudgetMangerDAO.Exceptions.DaoException;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDao extends MySqlDao implements ExpenseDaoInterface {
    // Read all Expenses
    @Override
    public List<Expense> getAllExpenses() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Expense> expenses = new ArrayList<Expense>();

        try {
            connection = getConnection();
            String query = "SELECT * FROM expense";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int expenseID = resultSet.getInt("expenseID");
                String title = resultSet.getString("title");
                double amount = resultSet.getDouble("amount");
                Date dateIncurred = resultSet.getDate("dateIncurred");

                expenses.add(new Expense(expenseID, title, amount, dateIncurred));
            }
        } catch (SQLException e) {
            throw new DaoException("getAllTasksWithTag() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("getAllTasksWithTag() " + e.getMessage());
            }
        }
        return expenses;
    }


    // Add new Expense
    @Override
    public void addExpense(Expense expense) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.getConnection();

            String query = "INSERT INTO expense (TITLE, AMOUNT, DATEINCURRED) VALUES (?,?,?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, expense.getTitle());
            preparedStatement.setDouble(2, expense.getAmount());
            preparedStatement.setDate(3, expense.getDateIncurred());

            preparedStatement.executeUpdate();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null)
                {
                    preparedStatement.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            }
            catch (SQLException e)
            {
                throw new DaoException("addExpense() " + e.getMessage());
            }
        }
    }



    // delete Expense by id
    @Override
    public void deleteExpense(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.getConnection();
            String query = "DELETE FROM expense WHERE expenseID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null)
                {
                    preparedStatement.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            }
            catch (SQLException e)
            {
                throw new DaoException("deleteExpense() " + e.getMessage());
            }
        }
    }



    // get Expense by month
    public List<Expense> getExpenseByMonth(int month, int year) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Expense> expenses = new ArrayList<>();

        try {
            connection = getConnection();
            String query = "SELECT * FROM expense WHERE MONTH(dateIncurred) = ? AND YEAR(dateIncurred) = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, month);
            preparedStatement.setInt(2, year);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int expenseID = resultSet.getInt("expenseID");
                String title = resultSet.getString("title");
                double amount = resultSet.getDouble("amount");
                Date dateIncurred = resultSet.getDate("dateIncurred");

                expenses.add(new Expense(expenseID, title, amount, dateIncurred));
            }
        } catch (SQLException e) {
            throw new DaoException("getIncomeByMonth() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("getIncomeByMonth() " + e.getMessage());
            }
        }
        return expenses;
    }
}
