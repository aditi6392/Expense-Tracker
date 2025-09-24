package ExpenseTracker;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseTracker {
    public static void main(String []args){
        Scanner sc=new Scanner(System.in);
        ArrayList<Expense> expenses=new ArrayList<>();
        boolean running=true;
        //load expenses from file
        loadExpenses(expenses);

        while (running){
            System.out.println("\n====Expense Tracker ====");
            System.out.println("1.Add Expense");
            System.out.println("2.View Expense");
            System.out.println("3.Show Total");
            System.out.println("4.Save and Exit");
            int choice=sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    addExpense(sc,expenses);
                    break;
                case 2:
                    viewExpense(expenses);
                    break;
                case 3:
                    showTotal(expenses);
                    break;
                case 4:
                    saveExpenses(expenses);
                    running=false;
                    System.out.println("Expense saved exiting");
                    break;
                default:
                    System.out.println("Invalid choice! Try Again");
            }

        }
        sc.close();
    }
    public static void addExpense(Scanner sc,ArrayList<Expense> expenses){
        System.out.println("Enter amount: ");
        double amount=sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter category: ");
        String category=sc.nextLine();
        System.out.println("Enter description: ");
        String description = sc.nextLine();

        Expense exp=new Expense(amount,category,description);
        expenses.add(exp);
        System.out.println("Expense added");
    }
    //View all expenses
    public static void viewExpense(ArrayList<Expense> expenses){
        if (expenses.isEmpty()){
            System.out.println("No expense recorded");
            return;
        }
        int index=1;
        for (Expense exp : expenses){
            System.out.println(index+".");
            exp.display();
            index++;
        }
    }
    //Show total Expense
    public static void showTotal(ArrayList<Expense> expenses){
        double total =0;
        for (Expense exp : expenses){
            total +=exp.amount;
        }
        System.out.println("Total Expenses: "+total);
    }
    //Save expenses to file
    public static void saveExpenses(ArrayList<Expense> expenses){
        try {
            PrintWriter pw=new PrintWriter(new FileWriter("expenses.txt"));
            for (Expense exp : expenses) {
                pw.println(exp.amount + "," + exp.category + "," + exp.description + "," + exp.date);
            }
            pw.close();
            }
        catch (IOException e){
            System.out.println("Error saving expenses: "+e.getMessage());
        }
    }
    //Load expense from file
    public static void loadExpenses(ArrayList<Expense> expenses){
        try {
            File file=new File("expenses.txt");
            if (!file.exists()) return;
            BufferedReader br=new BufferedReader(new FileReader(file));
            String line;
            while ((line=br.readLine())!=null){
                String []parts=line.split(",");
                if (parts.length==4){
                    double amount = Double.parseDouble(parts[0]);
                    String category=parts[1];
                    String description =parts[2];
                    String date=parts[3];
                    Expense exp=new Expense(amount,category,description);
                    exp.date=date;
                    expenses.add(exp);

                }
            }
            br.close();
        }
        catch (IOException e){
            System.out.println("Error loading expenses: "+e.getMessage());
        }
    }
}
