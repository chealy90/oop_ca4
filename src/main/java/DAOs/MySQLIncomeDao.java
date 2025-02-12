package DAOs;
import DTOs.Expense;
import DTOs.Income;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLIncomeDao extends MySQLDao implements MySQLDaoInterface{


    public List findAll(){
        ArrayList<Income> incomes = new ArrayList<>();

        try {
            //carry out query
            Connection conn = getConnection();
            String query = "SELECT * FROM income;";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            //create user objects
            while (rs.next()) {
                int incomeID = rs.getInt(1);
                String title = rs.getString(2);
                String category = rs.getString(3);
                double amount = rs.getDouble(4);
                Date dateEarned = rs.getDate(5);

                Income income = new Income(incomeID, title, category, amount, dateEarned);
                incomes.add(income);
            }
        } catch (SQLException e){
            System.out.println("An error occured while accessing the database:");
            e.printStackTrace();
            System.exit(1);
        }

        return incomes;

    }

    public Income findByID(int id){
        Income income = null;


        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM income WHERE incomeID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int incomeID = rs.getInt(1);
                String title = rs.getString(2);
                String category = rs.getString(3);
                double amount = rs.getDouble(4);
                Date dateEarned = rs.getDate(5);

                income = new Income(incomeID, title, category, amount, dateEarned);
            }
        }
        catch (SQLException e){
            System.out.println("Error reading from database.");
            e.printStackTrace();
            System.exit(1);
        }

        return income;
        
    }

    public void createNew(Object newItem){
        Income newIncome = (Income) newItem;

        try {
            Connection conn = getConnection();
            String query = "INSERT INTO income VALUES (NULL, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, newIncome.getTitle());
            ps.setString(2, newIncome.getCategory());
            ps.setDouble(3, newIncome.getAmount());
            ps.setDate(4, newIncome.getDateEarned());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected!=1){
                System.out.println("---Could not write to the database.---");
            } else {
                System.out.println("---Added record to table 'income'.---");
            }
        }
        catch(SQLException e){
            System.out.println("---Error creating record in 'income'.---" );
            e.printStackTrace();
            System.exit(1);
        }
    }
}
