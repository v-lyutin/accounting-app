package model;

public class MonthlyTransaction {
    public String itemName;
    public boolean isExpense;
    public int quantity;
    public double unitPrice;

    public MonthlyTransaction(String itemName, boolean isExpense, int quantity, double unitPrice) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}
