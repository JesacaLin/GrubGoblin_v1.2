package org.JesacaLin;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
public class UserInput {
    public static String getStringInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        try {
            System.out.println(prompt);
            userInput = scanner.nextLine().toLowerCase();
        } catch (NullPointerException e) {
            System.out.println("Please enter an answer!");
        }
        return userInput;
    }

    public static double getDoubleInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        double userInput = 0.0;
        try {
            System.out.println(prompt);
            userInput = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number!");
        }
        return userInput;
    }
    public static int getIntInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;
        try {
            System.out.println(prompt);
            userInput = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number!");
        }
        return userInput;
    }

    public static LocalTime getTimeInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        LocalTime userInput = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            System.out.println(prompt);
            userInput = LocalTime.parse(scanner.nextLine(), formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Please use the correct HH:mm format!");
        }
        return userInput;
    }

}

//    public static List<DayOfWeek> getDaysOfWeekInput(String prompt) {
//        Scanner scanner = new Scanner(System.in);
//        //Display prompt
//        System.out.println(prompt);
//        //Initialize new ArrayList to store DayOfWeek;
//        List<DayOfWeek> listOfDays = new ArrayList<>();
//        String[] daysArray = scanner.nextLine().split(",");
//        //need throw an exception if the strings in the day array does not match DayOfWeek constants.
//        //convert each daysArray element into DayOfWeek and add to new Array.
//        for (String day : daysArray) {
//            try {
//                DayOfWeek convertedDay = DayOfWeek.valueOf(day.toUpperCase());
//                listOfDays.add(convertedDay);
//            } catch (IllegalArgumentException e) {
//                System.out.println(day + " is not a valid day of the week. Please try again");
//            }
//        }
//
//        return listOfDays;
//    }



