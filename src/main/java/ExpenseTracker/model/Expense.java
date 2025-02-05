package ExpenseTracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data

@AllArgsConstructor
public class Expense {
    private int id;
    private String description;
    private double amount;

    public String toCsv() {
        return id + "," + description + "," + amount + "\n";
    }
    public static Expense fromCsv(String csv) {
        String[] values = csv.split(",");
        return new Expense(Integer.parseInt(values[0]),values[1],Double.parseDouble(values[2]));
    }
    public String toString() {
        return "id:" +id + ",Description:"+ description + ", Amount:" + amount ;
    }
}
