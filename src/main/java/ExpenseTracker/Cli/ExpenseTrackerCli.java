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
           System.out.println("4. Delete a single Expense");
           System.out.println("5. Delete All Expenses");
           System.out.println("6. Exit");

           int option = input.nextInt();
           handleUserInput(option);
       }
    }

    private static void handleUserInput(int option) {
        ExpenseService service = new ExpenseService();
        List<Expense> expenseList;
        int id;
        int amount;
        String description;

        switch (option) {
            case 1:
                input.nextLine();
                System.out.println("Enter description:");
                description = input.nextLine();
                System.out.println("Enter expense amount:");
                amount = input.nextInt();


                try {
                    service.addExpense(amount, description);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Expense added successfully");
                break;
            case 2:
                System.out.println("Here is the list of all Expenses:");
                expenseList =  service.getAllExpenses();
                if(expenseList.isEmpty()) {
                    System.out.println("No expenses found");
                }
                else
                {

                System.out.println("Total expenses added: " + expenseList.size());
                System.out.println("Expense list:");
                for(Expense exp :expenseList)
                {
                    System.out.println(exp.toString());
                }
                }
                break;
            case 3:
                System.out.println("Here is the list of all Expenses:");
                expenseList = service.getAllExpenses();
                System.out.println(expenseList);
                if(!expenseList.isEmpty()) {
                    System.out.println("Expense list:");
                    while (true) {
                        System.out.println("Enter the id of Expense you want to edit:");
                        id = input.nextInt();
                        if (id>=0 & id<expenseList.size()) {
                            System.out.println("Enter description:");
                            description = input.nextLine();
                            description= description+ input.nextLine();
                            System.out.println("Enter amount:");
                            amount = input.nextInt();
                            Expense exp = expenseList.get(id);
                            exp.setDescription(description);
                            exp.setAmount(amount);
                            service.updateExpense(exp);
                            System.out.println("Expense edited successfully");
                            break;
                        }
                        else {
                            System.out.println("Expense not found. Kindly try again");
                            System.out.println("Do you want to continue? (Y/N)");
                            String answer = input.next();
                            if(answer.equalsIgnoreCase("Y")) {
                            }
                            else if(answer.equalsIgnoreCase("N")) {
                                break;
                            }
                            else {
                                System.out.println("It wasn't a valid option. Going back to main menu");
                                break;
                            }
                        }
                    }
                }
                else {
                    System.out.println("Sorry, Expense list is empty. Kindly add new expense first.");
                }
                break;
            case 4:
                System.out.println("Here is the list of all Expenses:");
                expenseList = service.getAllExpenses();
                System.out.println("Expense list:");
                System.out.println(expenseList);
                System.out.println("Enter the id of Expense you want to delete:");
                id = input.nextInt();
                if(id>=0 & id<expenseList.size()) {
                service.deleteExpense(id);
                System.out.println("Expense deleted successfully");
                }
                else{
                    System.out.println("Expense not found. Kindly try again");
                }
                break;
            case 5:
                System.out.println("Here is the list of all Expenses:");
                expenseList = service.getAllExpenses();
                System.out.println(expenseList);
                System.out.println("Are you sure you want to delete all expenses?");
                String answer = input.next();
                if(answer.equalsIgnoreCase("Y")) {
                    service.deleteAllExpenses();
                }
                break;

            case 6:
                System.out.println("Exiting Application\nThank you for using Expense Tracker");
                break;

        }
    }


}
