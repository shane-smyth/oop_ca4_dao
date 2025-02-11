package BudgetMangerDAO.DAOs;

import BudgetMangerDAO.DTOs.Income;
import BudgetMangerDAO.Exceptions.DaoException;

import java.util.List;

public interface IncomeDaoInterface {
    // read all records
    public List<Income> getAllIncomes() throws DaoException;

    // add new Income
    public void addIncome(Income income) throws DaoException;

    // delete Income by id
    public void deleteIncome(int id) throws DaoException;

    // get income by month
    public List<Income> getIncomeByMonth(int month, int year) throws DaoException;
}
