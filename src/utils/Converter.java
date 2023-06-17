package utils;

public class Converter {
    public static String printIsExpenseOrIncome(boolean isExpenseOrIncome) {
        return (isExpenseOrIncome) ? "трата" : "доход";
    }

    public static int getYearValueByFileName(String fileName) {
        return Integer.parseInt(fileName.substring(2, 6));
    }

    public static short getMonthNumberFromFileName(String fileName) {
        return Short.parseShort(fileName.substring(6, 8));
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
