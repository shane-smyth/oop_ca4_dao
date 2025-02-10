package BudgetMangerDAO.DTOs;

public class Expense {
    private int expenseID;
    private String title;
    private double amount;
    private String dataIncurred;


    public Expense(int expenseID, String title, double amount, String dataIncurred) {
        this.expenseID = expenseID;
        this.title = title;
        this.amount = amount;
        this.dataIncurred = dataIncurred;
    }

    public Expense(String title, double amount, String dataIncurred) {
        this.expenseID = 0;
        this.title = title;
        this.amount = amount;
        this.dataIncurred = dataIncurred;
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

    public String getDataIncurred() {
        return dataIncurred;
    }

    public void setDataIncurred(String dataIncurred) {
        this.dataIncurred = dataIncurred;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseID=" + expenseID +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", dataIncurred='" + dataIncurred + '\'' +
                '}';
    }
}
