package ExpenseTracker.service;

import ExpenseTracker.model.Expense;
import ExpenseTracker.repo.ExpenseRepo;

import java.net.URISyntaxException;
import java.util.List;

public class ExpenseService {
    ExpenseRepo expRepo = new ExpenseRepo();
    public void addExpense(double expense, String description) throws URISyntaxException {


        expRepo.saveExpense(expense,description);

    }

    public List<Expense> getAllExpenses() {
        return expRepo.retrieveAllExpense();
    }

    public void updateExpense(Expense exp) {

        List<Expense> expList = expRepo.retrieveAllExpense();
        expList.add(exp.getId(),exp);

        expRepo.saveAllExpense(expList);

    }
}
