package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static int checkInputValue(Scanner scanner) throws InputMismatchException {
        while (true) {
            try {
                int value = scanner.nextInt();
                if (value >= 0)
                    return value;
                throw new InputMismatchException();
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "Ошибка ввода, повторите попытку!" + ANSI_RED);
                scanner.nextLine();
            }
        }
    }
}
