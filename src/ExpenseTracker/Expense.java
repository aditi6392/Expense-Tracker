package ExpenseTracker;

import java.time.LocalDateTime;

public class Expense {
    double amount;
    String category;
    String description;
    String date;
    public Expense(double amount , String category , String description){
        this.amount=amount;
        this.category=category;
        this.description=description;
        this.date= LocalDateTime.now().toString();
    }
    public void display(){
        System.out.println(amount + " - " + category + " - " + description + " - " + date);
    }
}
