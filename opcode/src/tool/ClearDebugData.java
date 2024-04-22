package tool;

import java.sql.Connection;
import java.sql.PreparedStatement;

import util.ConnectionUtil;


public class ClearDebugData {
    
    private void clearDebugData(){
        Connection conn=ConnectionUtil.getConnection();
        if(conn==null)return;
        try{
            String insertSql = "delete from debugdata ";
            PreparedStatement ps = conn.prepareStatement(insertSql);
            ps.executeUpdate();
        }catch(Exception ex){
       }
     }
    
    private void clearOpcode(){
        Connection conn=ConnectionUtil.getConnection();
        if(conn==null)return;
        try{
            String insertSql = "delete from opcode ";
            PreparedStatement ps = conn.prepareStatement(insertSql);
            ps.executeUpdate();
        }catch(Exception ex){
       }
     }
         
    public void clearDb(){
        clearDebugData();
        clearOpcode();
    }
    
    public static void main(String[] args) {
        ClearDebugData c=new ClearDebugData();
        c.clearDebugData();
       // c.clearOpcode();
     }
}
