package utils;

public class Converter {
    public static String printIsExpenseOrIncome(boolean isExpenseOrIncome) {
        return (isExpenseOrIncome) ? "трата" : "доход";
    }

    public static int getYearValueByFileName(String fileName) {
        return Integer.parseInt(fileName.substring(2, 6));
    }

    public static short getMonthNumberFromFileName(String fileName) {
        String[] fileNameContent = fileName.split("");
        int firstMonthlyDigit = Short.parseShort(fileNameContent[6]);
        int secondMonthlyDigit = Short.parseShort(fileNameContent[7]);

        if (firstMonthlyDigit == 0)
            return Short.parseShort(fileNameContent[7]);
        else if (secondMonthlyDigit == 0)
            return 10;
        else if (secondMonthlyDigit == 1)
            return 11;
        else
            return 12;
    }

    public static String getMonthNameByMonthNumber(short number) {
        String monthName = null;
        switch (number) {
            case 1:
                monthName = "ЯНВАРЬ";
                break;
            case 2:
                monthName = "ФЕВРАЛЬ";
                break;
            case 3:
                monthName = "МАРТ";
                break;
            case 4:
                monthName = "АПРЕЛЬ";
                break;
            case 5:
                monthName = "МАЙ";
                break;
            case 6:
                monthName = "ИЮНЬ";
                break;
            case 7:
                monthName = "ИЮЛЬ";
                break;
            case 8:
                monthName = "АВГУСТ";
                break;
            case 9:
                monthName = "СЕНТЯБРЬ";
                break;
            case 10:
                monthName = "ОКТЯБРЬ";
                break;
            case 11:
                monthName = "НОЯБРЬ";
                break;
            case 12:
                monthName = "ДЕКАБРЬ";
                break;
        }
        return monthName;
    }
}
