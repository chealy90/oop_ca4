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
}
