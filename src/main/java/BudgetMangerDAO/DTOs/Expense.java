package BudgetMangerDAO.DTOs;

import java.sql.Date;

public class Expense {
    private int expenseID;
    private String title;
    private double amount;
    private Date dateIncurred;


    public Expense(int expenseID, String title, double amount, Date dateIncurred) {
        this.expenseID = expenseID;
        this.title = title;
        this.amount = amount;
        this.dateIncurred = dateIncurred;
    }

    public Expense(String title, double amount, Date dateIncurred) {
        this.expenseID = 0;
        this.title = title;
        this.amount = amount;
        this.dateIncurred = dateIncurred;
    }

    public Expense() {
    }

    public int getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(int expenseID) {
        this.expenseID = expenseID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDateIncurred() {
        return dateIncurred;
    }

    public void setDateIncurred(Date dateIncurred) {
        this.dateIncurred = dateIncurred;
    }

    @Override
    public String toString() {
        return "{" +
                "expenseID = " + expenseID +
                ", title = '" + title + '\'' +
                ", amount = " + amount +
                ", dataIncurred = '" + dateIncurred + '\'' +
                '}';
    }
}
