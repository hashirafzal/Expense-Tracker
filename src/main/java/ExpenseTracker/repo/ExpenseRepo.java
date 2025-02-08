package ExpenseTracker.repo;

import ExpenseTracker.model.Expense;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ExpenseRepo {
    public final String CSV_FILE = "src/main/resources/expense.csv";

    public void saveExpense( int amount, String description) {
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
            while((line = reader.readLine() )!= null)
            {
                expenseList.add(Expense.fromCsv(line));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        Collections.sort(expenseList, Comparator.comparingInt(Expense::getId));
        return expenseList;
              //  expenseList.sort((Comparator.comparingInt(Expense::getId));
    }

    public void saveAllExpense(List<Expense> expList) {
    try(BufferedWriter bfw = new BufferedWriter(new FileWriter(CSV_FILE)))
    {
        for(Expense exp : expList)
        {
            bfw.write(exp.toCsv());
        }
    }catch (IOException e)
    {
        e.printStackTrace();
    }

    }

    public void deleteExpense(int id) {
        List<Expense> expList = retrieveAllExpense() ;
        expList.remove(id);
        expList.getLast().setId(id);
        saveAllExpense(expList);
    }

    public void deleteAllExpenses() {
        List<Expense> expList = retrieveAllExpense();
        expList.clear();
        saveAllExpense(expList);
    }
}
