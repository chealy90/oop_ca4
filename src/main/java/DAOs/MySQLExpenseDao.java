package DAOs;

import DTOs.Expense;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLExpenseDao extends MySQLDao implements MySQLDaoInterface {
    public List findAll(){
        ArrayList<Expense> expenses = new ArrayList<>();
        try {
            //carry out query on DB
            Connection conn = getConnection();
            String query = "SELECT * FROM expense;";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            //populate expenses list
            while (rs.next()){
                int expenseID = rs.getInt(1);
                String title = rs.getString(2);
                String category = rs.getString(3);
                double amount = rs.getDouble(4);
                Date dateIncurred = rs.getDate(5);

                expenses.add(new Expense(expenseID, title, category, amount, dateIncurred));
            }
        } catch (SQLException e){
            System.out.println("Error while reading from the database.");
            e.printStackTrace();
            System.exit(1);
        }

        return expenses;
    }

    public int deleteByID(int id){
        try {
            //carry out query
            Connection conn = getConnection();
            String query = "DELETE FROM expense WHERE expenseID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        }
        catch (SQLException e){
            System.out.println("Error reading from database:");
            e.printStackTrace();
            System.exit(1);
        }
        return 0;
    }

    public void createNew(Object newItem){
        Expense newExpense = (Expense) newItem;

        try {
            Connection conn = getConnection();
            String query = "INSERT INTO expense VALUES (NULL, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, newExpense.getTitle());
            ps.setString(2, newExpense.getCategory());
            ps.setDouble(3, newExpense.getAmount());
            ps.setDate(4, newExpense.getDateIncurred());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected!=1){
                System.out.println("---Could not write to the database.---");
            } else {
                System.out.println("---Added record to table 'expense'.---");
            }
        }
        catch(SQLException e){
            System.out.println("---Error creating record in 'expense'.---" );
            e.printStackTrace();
            System.exit(1);
        }
    }

    public List findByMonth(int month){
        List<Expense> expenses = new ArrayList<>();

        try{
            //carry out query
            Connection conn = getConnection();
            String query = "SELECT * FROM expense WHERE MONTH(dateIncurred) = ?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, month);
            ResultSet rs = ps.executeQuery();

            //generate list
            while (rs.next()){
                Expense expense = new Expense(rs.getInt(1),
                                        rs.getString(2),
                                        rs.getString(3),
                                        rs.getDouble(4),
                                        rs.getDate(5));

                expenses.add(expense);
            }

            return expenses;
        }
        catch (SQLException e){
            System.out.println("---Error reading from database:");
            e.printStackTrace();
            System.exit(1);
        }
        
        return expenses;
    }
}
