package ExpenseTracker.Cli;

import ExpenseTracker.model.Expense;
import ExpenseTracker.service.ExpenseService;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

public class ExpenseTrackerCli {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        showMenu();
    }

    private static void showMenu() {
        System.out.println("Welcome to Expense Tracker, please select an option:");
       while(true) {
           System.out.println("\n**************************");
           System.out.println("1. Add Expense");
           System.out.println("2. Show all Expenses");
           System.out.println("3. Edit Expense");
           System.out.println("4. Delete Expense");
           System.out.println("5. Exit");

           int option = input.nextInt();
           handleUserInput(option);
           if(option == 5) {
               System.out.println("Exiting Application\nThank you for using Expense Tracker");
               break;
           }
       }
    }

    private static void handleUserInput(int option) {
        ExpenseService service = new ExpenseService();
        switch (option) {
            case 1:
                System.out.println("Enter expense:");
                double expense = input.nextDouble();
                System.out.println("Enter description:");
                String description = input.next();
                try {
                    service.addExpense(expense, description);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Expense added successfully");
                break;
            case 2:
                System.out.println("Here is the list of all Expenses:");
                List<Expense> expenseList =  service.getAllExpenses();
                System.out.println("Total expenses added: " + expenseList.size());
                System.out.println("Expense list:");
                for(Expense exp :expenseList)
                {
                    System.out.println(exp.toString());
                }

                break;

        }
    }


}
