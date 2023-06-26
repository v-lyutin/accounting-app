package managers;

import transactions.MonthlyTransactions;
import utils.Converter;
import utils.FileManager;
import utils.InputHandler;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReportManager {
    private final String directory;
    private final FileManager fileManager;
    public HashMap<String, ArrayList<MonthlyTransactions>> monthlyReports = new HashMap<>();

    public MonthlyReportManager(FileManager fileManager, String directory) {
        this.fileManager = fileManager;
        this.directory = directory;
    }

    private void loadMonthlyFile(String month, String fileName) {
        ArrayList<MonthlyTransactions> monthlyTransactions = new ArrayList<>();

        ArrayList<String> monthlyReportContent = fileManager.readFileContents(fileName);
        monthlyReportContent.remove(0);

        for (String fileLine : monthlyReportContent) {
            String[] line = fileLine.split("\n");
            for (String lineContent : line) {
                String[] parts = lineContent.split(",");

                String itemName = parts[0];
                boolean isExpense = Boolean.parseBoolean(parts[1]);
                int quantity = Integer.parseInt(parts[2]);
                double unitPrice = Double.parseDouble(parts[3]);

                MonthlyTransactions monthlyTransaction = new MonthlyTransactions(itemName, isExpense, quantity,
                        unitPrice);
                monthlyTransactions.add(monthlyTransaction);
            }
        }
        monthlyReports.put(month, monthlyTransactions);
    }

    public void readAllMonthlyReports() {
        if (monthlyReports.isEmpty()) {
            ArrayList<String> monthlyFilesNames = fileManager.getFileNames("m");

            if (monthlyFilesNames.isEmpty())
                System.out.println("Месячных отчетов в папке " + directory + " не обнаружено");
            else {
                for (String monthlyReportName : monthlyFilesNames) {
                    short monthNumber = Converter.getMonthNumberFromFileName(monthlyReportName);
                    String month = Converter.getMonthNameByMonthNumber(monthNumber);

                    loadMonthlyFile(month, monthlyReportName);
                }
                System.out.println(InputHandler.ANSI_WHITE + "\n***Ежемесячные отчеты успешно сформированы***\n"
                        + InputHandler.ANSI_WHITE);
            }
        } else System.out.println(InputHandler.ANSI_RED + "Ежемесячные отчеты уже сформированы"
                + InputHandler.ANSI_RED);
    }

    private HashMap<String, Double> getMostProfitableProduct(String month) {
        HashMap<String, Double> mostProfitableProduct = new HashMap<>();
        String mostIncomeItemName = null;
        double maxIncomeAmount = 0;

        for (MonthlyTransactions monthlyTransaction : monthlyReports.get(month)) {
            if (monthlyTransaction.isExpense) {
                continue;
            }
            else {
                double profit = monthlyTransaction.unitPrice * monthlyTransaction.quantity;
                String itemName = monthlyTransaction.itemName;

                if (profit > maxIncomeAmount) {
                    maxIncomeAmount = profit;
                    mostIncomeItemName = itemName;
                }
            }
        }
        mostProfitableProduct.put(mostIncomeItemName, maxIncomeAmount);
        return mostProfitableProduct;
    }

    private HashMap<String, Double> getMaxExpense(String month) {
        HashMap<String, Double> maxExpense = new HashMap<>();
        String maxExpenseItemName = null;
        double maxExpenseAmount = 0;

        for (MonthlyTransactions monthlyTransaction : monthlyReports.get(month)) {
            if (monthlyTransaction.isExpense) {
                double expense = monthlyTransaction.quantity * monthlyTransaction.unitPrice;
                String itemName = monthlyTransaction.itemName;

                if (expense > maxExpenseAmount) {
                    maxExpenseAmount = expense;
                    maxExpenseItemName = itemName;
                }
            }
        }
        maxExpense.put(maxExpenseItemName, maxExpenseAmount);
        return maxExpense;
    }

    private void printMaxExpense(HashMap<String, Double> maxExpense) {
        for (String itemName : maxExpense.keySet()) {
            System.out.print("Cамая большая трата - " + itemName.toLowerCase() + ". Расход составил "
                    + maxExpense.get(itemName) + "\n\n\n");
        }
    }

    private void printMostProfitableProduct(HashMap<String, Double> mostProfitableProduct) {
        for (String itemName : mostProfitableProduct.keySet()) {
            System.out.print("Cамый прибыльный товар - " + itemName.toLowerCase() + ". Доход составил "
                    + mostProfitableProduct.get(itemName) + "\n");
        }
    }

    public HashMap<String, Double> getAllMonthlyIncome() {
        HashMap<String, Double> allMonthlyIncome = new HashMap<>();

        for (String month : monthlyReports.keySet()) {
            double expenses = 0;
            for (MonthlyTransactions monthlyTransaction : monthlyReports.get(month)) {
                if (!(monthlyTransaction.isExpense)) {
                    expenses += monthlyTransaction.quantity * monthlyTransaction.unitPrice;
                }
            }
            allMonthlyIncome.put(month, expenses);
        }
        return allMonthlyIncome;
    }

    public HashMap<String, Double> getAllMonthlyExpenses() {
        HashMap<String, Double> allMonthlyExpenses = new HashMap<>();

        for (String month : monthlyReports.keySet()) {
            double expenses = 0;
            for (MonthlyTransactions monthlyTransaction : monthlyReports.get(month)) {
                if (monthlyTransaction.isExpense) {
                    expenses += monthlyTransaction.quantity * monthlyTransaction.unitPrice;
                }
            }
            allMonthlyExpenses.put(month, expenses);
        }
        return allMonthlyExpenses;
    }


    public void printMonthlyReports() {
        if (monthlyReports.isEmpty())
            System.out.println(InputHandler.ANSI_RED
                    + "Отчеты пока не сформированы. Воспользуйтесь для начала командой [1]\n"
                    + InputHandler.ANSI_RED);
        else {
            for (String month : monthlyReports.keySet()) {
                System.out.println(InputHandler.ANSI_WHITE + "***" + month + "***" + InputHandler.ANSI_WHITE);
                System.out.println("=====================================================");
                for (MonthlyTransactions monthlyTransaction : monthlyReports.get(month)) {
                    System.out.println("Название товара: " + monthlyTransaction.itemName.toLowerCase());
                    System.out.println("Количество закупленного или проданного товара: " + monthlyTransaction.quantity);
                    System.out.println("Стоимость одной единицы товара: " + monthlyTransaction.unitPrice);
                    System.out.println("Трата или доход: " + Converter.printIsExpenseOrIncome(
                            monthlyTransaction.isExpense));
                    System.out.println("=====================================================");
                }
                System.out.println("ИТОГИ МЕСЯЦА:");
                printMostProfitableProduct(getMostProfitableProduct(month));
                printMaxExpense(getMaxExpense(month));
            }
        }
    }
}
