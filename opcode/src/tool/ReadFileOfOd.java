package tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

import util.ConnectionUtil;

public class ReadFileOfOd {
	
	public void readTxt(String filePath) throws IOException{
	   File file=new File(filePath);
	   InputStreamReader read = new InputStreamReader(new FileInputStream(file));//考虑到编码格式
	   BufferedReader bufferedReader = new BufferedReader(read);
	   String lineTxt = null;
       while((lineTxt = bufferedReader.readLine()) != null){
           doSaveCall(lineTxt);
       }
       read.close();
   }
   
   private void doSaveCall(String lineTxt){
	    if(!lineTxt.contains("CALL"))return;
	    String address=lineTxt.substring(0,8);
	    System.out.println(address);
	    save(address);
	 }
   
   private void save(String address){
       Connection conn=ConnectionUtil.getConnection();
       if(conn==null)return;
       try{
           String insertSql = "insert into opcode(address, type,op1,op2,opcode,bytecodes) values(?,?,?,?,?,?)";
           PreparedStatement ps = conn.prepareStatement(insertSql);
           ps.setString(1, address);
           ps.setInt(2, 0);
           ps.setString(3, "");
           ps.setString(4, "");
           ps.setString(5, "");
           ps.setString(6, "");
           ps.executeUpdate();
       }catch(Exception ex){
      }
   }
   
   
   
   public static void main(String[] args) throws IOException {
	   ReadFileOfOd r=new ReadFileOfOd();
	   r.readTxt("E:\\microstar\\callTest.txt");
   }
} 
