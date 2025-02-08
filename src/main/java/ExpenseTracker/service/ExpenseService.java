package ExpenseTracker.service;

import ExpenseTracker.model.Expense;
import ExpenseTracker.repo.ExpenseRepo;

import java.net.URISyntaxException;
import java.util.List;

public class ExpenseService {
    ExpenseRepo expRepo = new ExpenseRepo();
    public void addExpense(int expense, String description) throws URISyntaxException {


        expRepo.saveExpense(expense,description);

    }

    public List<Expense> getAllExpenses() {
        return expRepo.retrieveAllExpense();
    }

    public void updateExpense(Expense exp) {

        List<Expense> expList = expRepo.retrieveAllExpense();
        expList.remove(exp.getId());
        expList.add(exp.getId(),exp);

        expRepo.saveAllExpense(expList);

    }

    public void deleteExpense(int id) {
        expRepo.deleteExpense(id);
    }

    public void deleteAllExpenses() {
            expRepo.deleteAllExpenses();
    }
}
