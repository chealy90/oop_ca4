package BusinessObjects;

import DAOs.MySQLExpenseDao;
import DAOs.MySQLIncomeDao;
import DTOs.Expense;
import DTOs.Income;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        App app = new App();
        app.start();
    }


    public void start(){
        System.out.println("---Budget Manager---");
        int choice = 0;
        do {
            choice = getMenuChoice();

            //determine action
            switch (choice){
                case 1:
                    displayAllIncomes();
                    break;
                case 2:
                    displayAllExpenses();
                    break;
                case 3:
                    deleteIncomeByID();
                    break;
                case 4:
                    deleteExpenseByID();
                    break;
                case 5:
                    addNewIncome();
                    break;
                case 6:
                    addNewExpense();
                    break;
                case 7:
                    examineByMonth();
                    break;
                default:
                    System.out.println("Error: Please enter a valid option");
                    break;


            }

        } while (choice!=8);
    }

    public static int getMenuChoice(){
        System.out.println("---Menu---");
        System.out.println("1) Display all incomes.");
        System.out.println("2) Display all expenses");
        System.out.println("3) Delete an income by ID number.");
        System.out.println("4) Delete an expense by ID number");
        System.out.println("5) Add a new income.");
        System.out.println("6) Add a new expense.");
        System.out.println("7) Examine income and expenses by month.");


        System.out.print("Your choice:");
        Scanner kb = new Scanner(System.in);
        int choice = kb.nextInt();
        kb.nextLine();

        return choice;
    }

    public double generateTable(List list, String className){
        String dateHeader = className.equals("Expense") ? "Date Incurred" : "Date Earned";
        double total = 0;

        System.out.println(className + "s:");
        for (int i=0;i<80;i++){System.out.print("-");};
        System.out.println();

        System.out.printf("%-5s%-20s%-20s%-20s%-15s\n", "ID", "Title", "Category", "Amount", dateHeader);

        for (int i=0;i<80;i++){System.out.print("-");};
        System.out.println();


        //incomes
        if (className.equals("Income")){
            for (Object incomeObj: list) {
                Income income = (Income) incomeObj;
                total += income.getAmount();
                System.out.printf("%-5d%-20s%-20s%-20.2f%-15s\n", income.getIncomeID(),
                                income.getTitle(),
                                income.getCategory(),
                                income.getAmount(),
                                income.getDateEarned().toString());
            }
        }
        //expenses
        else {
            for (Object expenseObj: list) {
                Expense expense = (Expense) expenseObj;
                total += expense.getAmount();
                System.out.printf("%-5d%-20s%-20s%-20.2f%-15s\n", expense.getExpenseID(),
                        expense.getTitle(),
                        expense.getCategory(),
                        expense.getAmount(),
                        expense.getDateIncurred().toString());
            }
        }

        //show total
        System.out.printf("%80s\n", "------------------");
        System.out.printf("%80s\n", "Total: €" + total);
        System.out.printf("%80s\n", "------------------");

        return total;
    }

    public void generateDualTable(List incomes, List expenses){
        double incomeTotal = 0;
        double expenseTotal = 0;

        //titles
        for (int i=0;i<82 + 80;i++){System.out.print("-");}
        System.out.println();
        System.out.printf("%-80s|  %-80s\n", "Income", "Expenses");
        //need to find out which of the tables is longer first
        int maxLength = Math.max(incomes.size(), expenses.size());

        //headers
        for (int i=0;i<82 + 80;i++){System.out.print("-");}
        System.out.println();

        System.out.printf("%-5s%-20s%-20s%-20s%-15s|  ", "ID", "Title", "Category", "Amount", "Date Earned");
        System.out.printf("%-5s%-20s%-20s%-20s%-15s\n", "ID", "Title", "Category", "Amount", "Date Incurred");

        for (int i=0;i<82 + 80;i++){System.out.print("-");}
        System.out.println();

        //table data
        for (int i=0;i<maxLength;i++){
            //incomes
            if (i<incomes.size()){
                Income income = (Income) incomes.get(i);
                incomeTotal += income.getAmount();
                System.out.printf("%-5d%-20s%-20s%-20.2f%-15s|  ", income.getIncomeID(),
                        income.getTitle(),
                        income.getCategory(),
                        income.getAmount(),
                        income.getDateEarned().toString());
            } else {
                System.out.printf("%80s|  ", "");
            }

            //expenses
            if (i<expenses.size()){
                Expense expense = (Expense) expenses.get(i);
                expenseTotal += expense.getAmount();
                System.out.printf("%-5d%-20s%-20s%-20.2f%-15s\n", expense.getExpenseID(),
                        expense.getTitle(),
                        expense.getCategory(),
                        expense.getAmount(),
                        expense.getDateIncurred().toString());
            } else {
                System.out.println();
            }
        }

        //total
        System.out.printf("%80s|  %80s\n", "------------------", "------------------");
        System.out.printf("%80s|  %80s\n", "Total: €" + incomeTotal + "  ", "Total: €" + expenseTotal);
        System.out.printf("%80s|  %80s\n", "------------------", "------------------");

        //summary
        double leftOver = incomeTotal - expenseTotal;
        System.out.println("-----SUMMARY-----");
        System.out.println("Total income earned: €" + incomeTotal);
        System.out.println("Total expenses incurred: €" + expenseTotal);
        System.out.println("Leftover at end of month: €" + leftOver);
        System.out.println("-----------------");
        System.out.println();





    }


    //show all methods
    public void displayAllIncomes(){
        MySQLIncomeDao incomeDAO = new MySQLIncomeDao();
        //get all incomes
        List<Income> incomes = incomeDAO.findAll();
        generateTable(incomes, "Income");
    }

    public void displayAllExpenses(){
        MySQLExpenseDao expenseDAO = new MySQLExpenseDao();
        List<Expense> expenses = expenseDAO.findAll();
        generateTable(expenses, "Expense");
    }



    //delete methods
    public void deleteIncomeByID(){
        Scanner kb = new Scanner(System.in);
        MySQLIncomeDao incomeDAO = new MySQLIncomeDao();

        System.out.print("---Enter income ID:");
        int inputtedID = kb.nextInt();
        kb.nextLine();

        int rowsAffected = incomeDAO.deleteByID(inputtedID);
        if (rowsAffected != 0) {
            System.out.println("---Record with ID '" + inputtedID + "' deleted.");
        } else {
            System.out.println("---Record with ID '" + inputtedID + "' not found.");
        }
    }

    public void deleteExpenseByID(){
        Scanner kb = new Scanner(System.in);
        MySQLExpenseDao expenseDAO = new MySQLExpenseDao();

        System.out.print("---Enter expense ID:");
        int inputtedID = kb.nextInt();
        kb.nextLine();

        int rowsAffected = expenseDAO.deleteByID(inputtedID);
        if (rowsAffected != 0) {
            System.out.println("---Record with ID '" + inputtedID + "' deleted.");
        } else {
            System.out.println("---Record with ID '" + inputtedID + "' not found.");
        }
    }

    //create new methods

    public void addNewIncome(){
        Scanner kb = new Scanner(System.in);
        MySQLIncomeDao incomeDAO = new MySQLIncomeDao();

        String title;
        String category;
        double amount;
        String dateString;
        Date date;

        System.out.print("---Enter income title:");
        title = kb.nextLine();
        System.out.print("---Enter income category:");
        category = kb.nextLine();
        System.out.print("---Enter income amount:");
        amount = kb.nextDouble();
        kb.nextLine();
        System.out.print("---Enter date earned (yyyy-mm-dd):");
        dateString = kb.nextLine();
        date = Date.valueOf(dateString);

        Income newIncome = new Income(null, title, category, amount, date);
        incomeDAO.createNew(newIncome);
    }

    public void addNewExpense(){
        Scanner kb = new Scanner(System.in);
        MySQLExpenseDao expenseDAO = new MySQLExpenseDao();

        String title;
        String category;
        double amount;
        String dateString;
        Date date;

        System.out.print("---Enter expense title:");
        title = kb.nextLine();
        System.out.print("---Enter expense category:");
        category = kb.nextLine();
        System.out.print("---Enter expense amount:");
        amount = kb.nextDouble();
        kb.nextLine();
        System.out.print("---Enter date incurred (yyyy-mm-dd):");
        dateString = kb.nextLine();
        date = Date.valueOf(dateString);

        Expense newExpense = new Expense(null, title, category, amount, date);
        expenseDAO.createNew(newExpense);
    }


    //analyse methods
    public void examineByMonth(){
        Scanner kb = new Scanner(System.in);
        MySQLIncomeDao incomeDAO = new MySQLIncomeDao();
        MySQLExpenseDao expenseDAO = new MySQLExpenseDao();

        System.out.print("Enter month number:");
        int month = kb.nextInt();

        List<Income> incomes = incomeDAO.findByMonth(month);
        List<Expense> expenses = expenseDAO.findByMonth(month);

        //generate tables
        generateDualTable(incomes, expenses);
    }





}
