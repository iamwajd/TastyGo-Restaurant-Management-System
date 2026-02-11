package project_cs;
import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {
    // إضافة بيانات الاتصال
    
    public static Connection getConnection() {
        Connection cnx = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/fooddb", "root", "fooddb");
        } catch (Exception e) {
            System.out.println("Exception = " + e);
        }
        return cnx;
    }
}

    
    
    
    
    
