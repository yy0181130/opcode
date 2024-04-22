package tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpu.parent.CpuObject;

public class GetRedmoonMedFrist {
	private boolean medOverFlag=false;
	private static Logger logger = LogManager.getLogger(GetRedmoonMedFrist.class);
	public void readTxt(String filePath) throws IOException{
		   File file=new File(filePath);
		   InputStreamReader read = new InputStreamReader(new FileInputStream(file));//考虑到编码格式
		   BufferedReader bufferedReader = new BufferedReader(read);
		   String lineTxt = null;
	       while((lineTxt = bufferedReader.readLine()) != null){
	           doCheck(lineTxt);
	       }
	       read.close();
	   }
	
	private void doCheck(String lineTxt){
		if(lineTxt.contains("nop")){
			medOverFlag=true;
		    return;	
		}
		if(lineTxt.contains("retn")){
			medOverFlag=true;
		    return;	
		}
		//String values=lineTxt.substring(8);
		if(lineTxt.contains(" db ")||lineTxt.contains(" dd ")){
			return;
		}
		if(medOverFlag){
			String address=lineTxt.substring(0,8);
			logger.info("bp "+address);
			medOverFlag=false;
		}
		
	}
	
	
	
	
	   public static void main(String[] args) throws IOException {
		   GetRedmoonMedFrist r=new GetRedmoonMedFrist();
		   r.readTxt("E:\\gamedata\\redmooncode.txt");
	   }
	
}
