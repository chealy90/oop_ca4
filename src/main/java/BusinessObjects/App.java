package BusinessObjects;

import DAOs.MySQLExpenseDao;
import DAOs.MySQLIncomeDao;
import DTOs.Expense;
import DTOs.Income;

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
                    findIncomeByID();
                    break;
                case 4:
                    findExpenseByID();
                    break;


            }

        } while (choice!=7);
    }

    public int getMenuChoice(){
        System.out.println("1) Display all incomes.");
        System.out.println("2) Display all expenses");
        System.out.println("3) Find an income by ID number.");
        System.out.println("4) Find an expense by ID number");


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

    public void findIncomeByID(){
        Scanner kb = new Scanner(System.in);
        MySQLIncomeDao incomeDAO = new MySQLIncomeDao();

        System.out.print("---Enter income ID:");
        int inputtedID = kb.nextInt();
        kb.nextLine();

        Income income = incomeDAO.findByID(inputtedID);
        if (income==null){
            System.out.println("---Income with ID " + inputtedID + " not found.---");
        } else {
            System.out.println("Result:");
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.printf("%5s%20s%20s%20s%20s\n", "ID", "Title", "Category", "Amount", "Date Earned");
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.printf("%5d%20s%20s%20.2f%20s\n", income.getIncomeID(),
                    income.getTitle(),
                    income.getCategory(),
                    income.getAmount(),
                    income.getDateEarned().toString());
            System.out.println("--------------------------------------------------------------------------------------------");
        }
    }


    public void findExpenseByID(){
        Scanner kb = new Scanner(System.in);
        MySQLExpenseDao expenseDAO = new MySQLExpenseDao();

        System.out.print("---Enter expense ID:");
        int inputtedID = kb.nextInt();
        kb.nextLine();

        Expense expense = expenseDAO.findByID(inputtedID);
        if (expense==null){
            System.out.println("---Expense with ID " + inputtedID + " not found.---");
        } else {
            System.out.println("Result:");
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.printf("%5s%20s%20s%20s%20s\n", "ID", "Title", "Category", "Amount", "Date Incurred");
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.printf("%5d%20s%20s%20.2f%20s\n", expense.getExpenseID(),
                    expense.getTitle(),
                    expense.getCategory(),
                    expense.getAmount(),
                    expense.getDateIncurred().toString());
            System.out.println("--------------------------------------------------------------------------------------------");
        }
    }


}
