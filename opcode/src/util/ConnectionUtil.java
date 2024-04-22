package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
   
    private static Connection conn = null;
    
    public static Connection getConnection() {
       if(conn!=null)return conn;
        try {
            Class.forName("org.sqlite.JDBC");
           // String dbPath="jdbc:sqlite:E:\\waigua\\debug\\PRO\\PRO\\db\\memory.db";
            String dbPath="jdbc:sqlite:c:\\db\\memory.db";
            conn = DriverManager.getConnection(dbPath);
        } catch (Exception e) {
            System.err.println("链接不到数据库");
            e.printStackTrace();
            return null;
        }
        return conn;
    }
    
    public static void main(String[] args) throws SQLException {
        ConnectionUtil c=new ConnectionUtil();
        c.getConnection();
    }
}