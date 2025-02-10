package DTOs;
import java.sql.Date;

public class Income {
    private int incomeID;
    private String title;
    private double amount;
    private Date dateEarned;
    private String category;

    public Income(Date dateEarned, double amount, String title, int incomeID, String category) {
        this.dateEarned = dateEarned;
        this.amount = amount;
        this.title = title;
        this.incomeID = incomeID;
        this.category = category;
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