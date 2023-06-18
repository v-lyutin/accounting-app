package transactions;

public class YearlyTransactions {
    public String month;
    public double amount;
    public boolean isExpense;

    public YearlyTransactions(String month, double amount, boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }
}
