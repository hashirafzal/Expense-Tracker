package ExpenseTracker.service;

import ExpenseTracker.model.Expense;
import ExpenseTracker.repo.ExpenseRepo;

import java.net.URISyntaxException;
import java.util.List;

public class ExpenseService {

    public void addExpense(double expense, String description) throws URISyntaxException {

        ExpenseRepo expRepo = new ExpenseRepo();
        expRepo.saveExpense(expense,description);

    }

    public List<Expense> getAllExpenses() {
        ExpenseRepo expRepo = new ExpenseRepo();
        return expRepo.retrieveAllExpense();
    }
}
