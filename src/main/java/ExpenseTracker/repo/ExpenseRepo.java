package ExpenseTracker.repo;

import ExpenseTracker.model.Expense;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExpenseRepo {
    public final String CSV_FILE = "src/main/resources/expense.csv";

    public void saveExpense( double amount, String description) {
        List<Expense> expenseList = retrieveAllExpense();
        int newId = expenseList.size();
        Expense exp = new Expense(newId,description,amount);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE,true)))
        {
            writer.write(exp.toCsv());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public List<Expense> retrieveAllExpense() {
        List<Expense> expenseList = new ArrayList<Expense>();

        try(BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))){
            String line ;
            while((line =reader.readLine() )!= null)
            {
                expenseList.add(Expense.fromCsv(line));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        return expenseList;
    }
}
