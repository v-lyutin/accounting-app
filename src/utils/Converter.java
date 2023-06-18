package utils;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

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
        return Month.of(number).getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("ru")).toUpperCase();
    }
}
