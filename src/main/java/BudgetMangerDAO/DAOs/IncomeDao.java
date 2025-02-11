package BudgetMangerDAO.DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import BudgetMangerDAO.DTOs.Income;
import BudgetMangerDAO.Exceptions.DaoException;

public class IncomeDao extends MySqlDao implements IncomeDaoInterface {
    // read all records
    @Override
    public List<Income> getAllIncomes() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Income> incomes = new ArrayList<Income>();

        try {
            connection = getConnection();
            String query = "SELECT * FROM income";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int incomeID = resultSet.getInt("incomeID");
                String title = resultSet.getString("title");
                double amount = resultSet.getDouble("amount");
                Date dateEarned = resultSet.getDate("dateEarned");

                incomes.add(new Income(incomeID, title, amount, dateEarned));
            }
        } catch (SQLException e) {
            throw new DaoException("getAllIncomes() " + e.getMessage());
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
                throw new DaoException("getAllIncomes() " + e.getMessage());
            }
        }
        return incomes;
    }



    // add new Income
    @Override
    public void addIncome(Income income) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();

            String query = "INSERT INTO income (TITLE, AMOUNT, DATEEARNED) VALUES (?,?,?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, income.getTitle());
            preparedStatement.setDouble(2, income.getAmount());
            preparedStatement.setDate(3, income.getDateEarned());

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



    // delete Income by id
    @Override
    public void deleteIncome(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            String query = "DELETE FROM income WHERE INCOMEID = ?";
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
                throw new DaoException("deleteIncome() " + e.getMessage());
            }
        }
    }



    // get income by month
    public List<Income> getIncomeByMonth(int month, int year) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Income> incomes = new ArrayList<Income>();
        double total = 0;

        try {
            connection = getConnection();
            String query = "SELECT * FROM income WHERE MONTH(dateEarned) = ? AND YEAR(dateEarned) = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, month);
            preparedStatement.setInt(2, year);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int incomeID = resultSet.getInt("incomeID");
                String title = resultSet.getString("title");
                double amount = resultSet.getDouble("amount");
                Date dateEarned = resultSet.getDate("dateEarned");

                incomes.add(new Income(incomeID, title, amount, dateEarned));
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
        return incomes;
    }
}