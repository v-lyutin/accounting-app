package service;

import managers.MonthlyReportManager;
import managers.YearlyReportManager;
import transactions.YearlyTransactions;
import utils.FileManager;
import utils.InputHandler;
import java.util.HashMap;

public class ReportsService {
    private final String directory = "./resources/";
    private final FileManager fileManager = new FileManager(directory);
    public final YearlyReportManager yearlyReportManager = new YearlyReportManager(fileManager, directory);
    public final MonthlyReportManager monthlyReportManager = new MonthlyReportManager(fileManager, directory);

    public void checkReports() {
        if (yearlyReportManager.yearlyTransactions.isEmpty() && monthlyReportManager.monthlyReports.isEmpty()) {
            System.out.println(InputHandler.ANSI_RED + "Отчеты пока не сформированы. Воспользуйтесь для начала командой [1] и [2]\n" + InputHandler.ANSI_RED);
            return;
        } else {

            HashMap<String, Double> monthlyExpenses = monthlyReportManager.getAllMonthlyExpenses();
            HashMap<String, Double> monthlyIncome = monthlyReportManager.getAllMonthlyIncome();

            for (YearlyTransactions yearlyTransaction : yearlyReportManager.yearlyTransactions) {
                if (yearlyTransaction.isExpense) {
                    if (monthlyExpenses.get(yearlyTransaction.month) == yearlyTransaction.amount)
                        printMatch(yearlyTransaction.month, true);
                    else {
                        printMismatch(yearlyTransaction.month, monthlyExpenses.get(yearlyTransaction.month), yearlyTransaction.amount, true);
                    }
                } else {
                    if (monthlyIncome.get(yearlyTransaction.month) == yearlyTransaction.amount)
                        printMatch(yearlyTransaction.month, false);
                    else {
                        printMismatch(yearlyTransaction.month, monthlyExpenses.get(yearlyTransaction.month), yearlyTransaction.amount, false);
                    }
                }
            }
            System.out.println();
        }
    }

    private void printMismatch(String month, double monthlyAmount, double yearlyAmount, boolean isExpense) {
        if (isExpense) {
            System.out.println(InputHandler.ANSI_RED + "[!] Обнаружено несоответствие расходов за " + month + InputHandler.ANSI_RED);
            System.out.println("В месячном отчете расходы составляют - " + monthlyAmount + ", в годовом - " + yearlyAmount);
        } else {
            System.out.println(InputHandler.ANSI_RED + "[!] Обнаружено несоответствие доходов за " + month + InputHandler.ANSI_RED);
            System.out.println("В месячном отчете доходы составляют - " + monthlyAmount + ", в годовом - " + yearlyAmount);
        }
    }

    private void printMatch(String month, boolean isExpense) {
        if (isExpense)
            System.out.println(InputHandler.ANSI_WHITE + "[✔] Сверка расходов за " + month + " прошла успешно" + InputHandler.ANSI_WHITE);
        else
            System.out.println(InputHandler.ANSI_WHITE + "[✔] Сверка доходов за " + month + " прошла успешно" + InputHandler.ANSI_WHITE);
    }
}
