package managers;

import transactions.YearlyTransactions;
import utils.Converter;
import utils.FileManager;
import utils.InputHandler;
import java.util.ArrayList;
import java.util.Scanner;

public class YearlyReportManager {
    private final String directory = "./resources/";
    private final FileManager fileManager = new FileManager(directory);
    protected ArrayList<YearlyTransactions> yearlyTransactions= new ArrayList<>();

    public void readYearlyReport(Scanner scanner) {
        ArrayList<String> yearlyFilesNames = fileManager.getFilesNames("y");

        if (yearlyFilesNames.isEmpty()) {
            System.out.println("Годовых отчетов в папке " + directory + " не обнаружено");
        } else {
            ArrayList<String>  yearlyReportContent = fileManager.readFileContents(yearlyFilesNames.get(0));
            yearlyReportContent.remove(0);
            for (String fileLine : yearlyReportContent) {
                String[] line = fileLine.split("/n");
                for (String lineContent : line) {
                    String[] parts = lineContent.split(",");

                    String month = Converter.getMonthNameByMonthNumber(Short.parseShort(parts[0]));
                    double amount = Double.parseDouble(parts[1]);
                    boolean isExpense = Boolean.parseBoolean(parts[2]);

                    YearlyTransactions yearlyTransaction = new YearlyTransactions(month, amount, isExpense);
                    yearlyTransactions.add(yearlyTransaction);
                }
            }
            System.out.println(InputHandler.ANSI_WHITE + "\n***Ежегодный отчет успешно сформирован***\n" + InputHandler.ANSI_WHITE);
        }
    }

    private int getYearlyAverageIncome() {
        double totalIncome = 0;
        int count = 0;
        for (YearlyTransactions yearlyTransaction : yearlyTransactions) {
            if (yearlyTransaction.isExpense)
                continue;
            else {
                totalIncome += yearlyTransaction.amount;
                count++;
            }
        }
        return (int) totalIncome / count;
    }

    private int getYearlyAverageExpense() {
        double totalExpense = 0;
        int count = 0;
        for (YearlyTransactions yearlyTransaction : yearlyTransactions) {
            if (yearlyTransaction.isExpense) {
                totalExpense += yearlyTransaction.amount;
                count++;
            }
        }
        return (int) totalExpense / count;
    }

    private void printMonthlyIncome() {
        for (YearlyTransactions yearlyTransaction : yearlyTransactions) {
            if (!(yearlyTransaction.isExpense))
                System.out.println("Прибыль за " + yearlyTransaction.month + " составила: " + yearlyTransaction.amount);
        }
    }

    public void printYearlyReport() {
        if (yearlyTransactions.isEmpty()) {
            System.out.println(InputHandler.ANSI_WHITE + "Годовой отчет пока не сформирован. Воспользуйтесь для начала командой [2]\n" + InputHandler.ANSI_WHITE);
        } else {
            ArrayList<String> yearlyFilesNames = fileManager.getFilesNames("y");
            int year = Converter.getYearValueByFileName(yearlyFilesNames.get(0));

            System.out.println("***" + year + "***\n");
            printMonthlyIncome();
            System.out.println("\nСредний доход за год составил: " + getYearlyAverageIncome());
            System.out.println("Средний расход за год составил: " + getYearlyAverageExpense() + "\n");
        }
    }
}
