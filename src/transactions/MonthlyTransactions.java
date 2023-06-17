package transactions;

public class MonthlyTransactions {
    public String itemName;
    public boolean isExpense;
    public int quantity;
    public double unitPrice;

    public MonthlyTransactions(String itemName, boolean isExpense, int quantity, double unitPrice) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}
