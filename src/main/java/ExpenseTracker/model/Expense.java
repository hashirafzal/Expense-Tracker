package ExpenseTracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

@AllArgsConstructor
public class Expense {
    private int id;
    private String description;
    private int amount;

    public String toCsv() {
        return id + "," + description + "," + amount + "\n";
    }
    public static Expense fromCsv(String csv) {
        String[] values = csv.split(",");
        return new Expense(Integer.parseInt(values[0]),values[1],Integer.parseInt(values[2]));
    }
    public String toString() {
        return "id:" +id + ",Description:"+ description + ", Amount:" + amount ;
    }
}
