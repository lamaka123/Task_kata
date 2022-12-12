import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int num1, num2;
    static char operation;
    static String input;
    static String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};


    public static void main(String[] args)
    {
        System.out.println("Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами: a + b, a - b, a * b, a / b.");
        System.out.print("Введите выражение:");
        input = scanner.nextLine().replaceAll("\\s", "");
        ConvertString(input);
    }



    public static String calc(int num1, int num2, char operation)
    {
        if (num1 <= 0 || num1 > 10 || num2 <= 0 || num2 > 10)
            throw new IllegalArgumentException("Число больше 10, или меньше 1 || число больше X, или меньше I");
        int result = 0;
        switch (operation)
        {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':

                try
                {
                    result = num1 / num2;
                }
                catch (ArithmeticException | InputMismatchException e)
                {
                    System.out.println("Exception : " + e);
                    System.out.println("Допускаются только целые числа");

                    break;
                }
                break;
            default:
                throw new IllegalArgumentException("Не верный знак!");

        }
        return String.valueOf(result);
    }



    public static void ConvertString(String input) {
        int placeOfChar = 0;
        String firstValue;
        String secondValue;
        int countTru = 0;

        if (input.indexOf('+') != -1)
        {
            placeOfChar = input.indexOf('+');
        }
        else if (input.indexOf('-') != -1)
        {
            placeOfChar = input.indexOf('-');
        }
        else if (input.indexOf('*') != -1)
        {
            placeOfChar = input.indexOf('*');
        }
        else if (input.indexOf('/') != -1)
        {
            placeOfChar = input.indexOf('/');
        }

        firstValue = input.substring(0, placeOfChar);
        secondValue = input.substring(placeOfChar + 1);
        operation = input.substring(placeOfChar).charAt(0);

        for (int i = 0; i < roman.length; i++)
        {
            if (firstValue.equals(roman[i]))
            {
                ++countTru;
            }
            if (secondValue.equals(roman[i]))
            {
                ++countTru;
            }
        }

        if (countTru == 1)
        {
            try
            {
                throw new IOException();
            } catch (IOException e)
            {
                System.out.println("Exception:" + e);
                System.out.println("Используются одновременно разные системы счисления");
            }
        }
        if (countTru == 0)
        {
            num1 = Integer.parseInt(firstValue);
            num2 = Integer.parseInt(secondValue);
            System.out.println("Ответ: "+calc(num1, num2, operation));
        }
        if (countTru == 2) {

            for (int i = 0; i < roman.length; i++)
            {
                if (roman[i].equals(firstValue))
                {
                    num1 = i + 1;
                }
                if (roman[i].equals(secondValue))
                {
                    num2 = i + 1;
                }
            }
            String resString = calc(num1, num2, operation);
            int resInt = Integer.parseInt(resString);
            System.out.print("Ответ: "+roman[resInt - 1]);
        }
    }
}