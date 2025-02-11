package DTOs;
import java.sql.Date;

public class Income {
    private int incomeID;
    private String title;
    private double amount;
    private Date dateEarned;
    private String category;

    public Income(int incomeID, String title, String category, double amount, Date dateEarned) {
        this.incomeID = incomeID;
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.dateEarned = dateEarned;
    }

    public int getIncomeID() {
        return incomeID;
    }

    public void setIncomeID(int incomeID) {
        this.incomeID = incomeID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDateEarned() {
        return dateEarned;
    }

    public void setDateEarned(Date dateEarned) {
        this.dateEarned = dateEarned;
    }
    
    public String getCategory(){
        return this.category;
    }
    
    public void setCategory(String category){
        this.category = category;
    }
}