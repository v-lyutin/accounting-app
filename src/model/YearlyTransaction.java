package model;

public class YearlyTransaction {
    public String month;
    public double amount;
    public boolean isExpense;

    public YearlyTransaction(String month, double amount, boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }
}
