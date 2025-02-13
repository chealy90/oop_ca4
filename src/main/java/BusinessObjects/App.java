package BusinessObjects;

import DAOs.MySQLExpenseDao;
import DAOs.MySQLIncomeDao;
import DTOs.Expense;
import DTOs.Income;

import java.sql.Date;
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


            }

        } while (choice!=8);
    }

    public int getMenuChoice(){
        System.out.println("1) Display all incomes.");
        System.out.println("2) Display all expenses");
        System.out.println("3) Delete an income by ID number.");
        System.out.println("4) Delete an expense by ID number");
        System.out.println("5) Add a new income.");
        System.out.println("6) Add a new expense.");


        System.out.print("Your choice:");
        Scanner kb = new Scanner(System.in);
        int choice = kb.nextInt();
        kb.nextLine();

        return choice;
    }

    public void displayAllIncomes(){
        MySQLIncomeDao incomeDAO = new MySQLIncomeDao();
        double total = 0;
        //get all incomes
        List<Income> incomes = incomeDAO.findAll();

        //get total
        for (Income income: incomes){
            total += income.getAmount();
        }

        //display table
        System.out.println("Incomes:");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("%5s%20s%20s%20s%20s\n", "ID", "Title", "Category", "Amount", "Date Earned");
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Income income: incomes){
            System.out.printf("%5d%20s%20s%20.2f%20s\n", income.getIncomeID(),
                                                        income.getTitle(),
                                                        income.getCategory(),
                                                        income.getAmount(),
                                                        income.getDateEarned().toString());
        }
        System.out.println("--------------");
        System.out.println("Total: €" + total);
        System.out.println("--------------");


    }


    public void displayAllExpenses(){
        MySQLExpenseDao expenseDAO = new MySQLExpenseDao();
        double total = 0;
        //get all incomes
        List<Expense> expenses = expenseDAO.findAll();

        //get total
        for (Expense expense: expenses){
            total += expense.getAmount();
        }

        //display table
        System.out.println("Expenses:");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("%5s%20s%20s%20s%20s\n", "ID", "Title", "Category", "Amount", "Date Incurred");
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Expense expense: expenses){
            System.out.printf("%5d%20s%20s%20.2f%20s\n", expense.getExpenseID(),
                    expense.getTitle(),
                    expense.getCategory(),
                    expense.getAmount(),
                    expense.getDateIncurred().toString());
        }
        System.out.println("--------------");
        System.out.println("Total: €" + total);
        System.out.println("--------------");


    }

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
        System.out.println("---Enter date earned (yyyy-mm-dd):");
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


}
