import service.ReportsService;
import utils.InputHandler;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        ReportsService reportsService = new ReportsService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();

            int command = InputHandler.checkInputValue(scanner);

            switch (command) {
                case 1:
                    reportsService.monthlyReportManager.readAllMonthlyReports();
                    break;
                case 2:
                    reportsService.yearlyReportManager.readYearlyReport();
                    break;
                case 3:
                    reportsService.checkReports();
                    break;
                case 4:
                    reportsService.monthlyReportManager.printMonthlyReports();
                    break;
                case 5:
                    reportsService.yearlyReportManager.printYearlyReport();
                    break;
                case 0:
                    scanner.close();
                    return;
                default:
                    System.out.println(InputHandler.ANSI_RED + "Такой команды нет! Повторите попытку:" + InputHandler.ANSI_RED);
                    break;
            }
        }

    }

    public static void printMenu() {
        System.out.println(
                        InputHandler.ANSI_WHITE +
                        "[1] - Считать все месячные отчёты\n" +
                        "[2] - Считать годовой отчёт\n" +
                        "[3] - Сверить отчёты\n" +
                        "[4] - Вывести информацию обо всех месячных отчётах\n" +
                        "[5] - Вывести информацию о годовом отчёте\n" +
                        "[0] - Выход\n" +
                        InputHandler.ANSI_WHITE
        );
    }
}


