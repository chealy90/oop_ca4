package DAOs;
import Exceptions.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDao {
    public MySQLDao(){

    }


    public Connection getConnection(){
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/ca4_budget_app";
        String username = "root";
        String password = "";
        Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        }
        catch (ClassNotFoundException e){
            System.out.println("Error: driver not found.");
            System.exit(1);
        }
        catch (SQLException e){
            System.out.println("Error: could not connect to database.");
            e.printStackTrace();
            System.exit(1);
        }

        return conn;
    }

    public void freeConnection(Connection conn) throws DaoException {
        try {
            if (conn!=null){
                conn.close();
                conn = null;
            }
        } catch (SQLException e){
            System.out.println("Error closing connection: ");
            e.printStackTrace();
            System.exit(1);
        }
    }


}
