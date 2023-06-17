package managers;

import transactions.YearlyTransactions;
import utils.InputHandler;
import java.util.HashMap;

public class ReportsCheckerManager {
    private final YearlyReportManager yearlyReportManager;
    private final MonthlyReportManager monthlyReportManager;

    public ReportsCheckerManager(YearlyReportManager yearlyReportManager, MonthlyReportManager monthlyReportManager) {
        this.yearlyReportManager = yearlyReportManager;
        this.monthlyReportManager = monthlyReportManager;
    }

    public void checkReports() {
        if (!(yearlyReportManager.yearlyTransactions.isEmpty() && monthlyReportManager.allMonthsReports.isEmpty())) {
            HashMap<String, Double> monthlyExpenses = monthlyReportManager.getAllMontlyExpenses();
            HashMap<String, Double> mothlyIncome = monthlyReportManager.getAllMonthlyIncome();

            for (YearlyTransactions yearlyTransaction : yearlyReportManager.yearlyTransactions) {
                if (yearlyTransaction.isExpense) {
                    if (monthlyExpenses.get(yearlyTransaction.month) == yearlyTransaction.amount)
                        System.out.println("Траты за " + yearlyTransaction.month + " cошлись");
                    else {
                        System.out.println("Обнаражено несоответствие трат за " + yearlyTransaction.month +
                                ": " + monthlyExpenses.get(yearlyTransaction.month) + " и " + yearlyTransaction.amount);
                    }
                } else {
                    if (mothlyIncome.get(yearlyTransaction.month) == yearlyTransaction.amount)
                        System.out.println("Доходы за " + yearlyTransaction.month + " cошлись");
                    else {
                        System.out.println("Обнаражено несоответствие доходов за " + yearlyTransaction.month +
                                ": " + mothlyIncome.get(yearlyTransaction.month) + " и " + yearlyTransaction.amount);
                    }
                }
            }
            System.out.println();
        } else
            System.out.println(InputHandler.ANSI_RED + "Отчеты пока не сформированы. Воспользуйтесь для начала командой [1] и [2]\n" + InputHandler.ANSI_RED);
    }
}
