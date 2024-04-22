package peload.parent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.ArrayUtil;
import util.PrintUtil;


public class LoadFromPe {
     private ArrayList<Integer> pefilelst;
     
     public LoadFromPe() {
    	 pefilelst=new ArrayList<Integer>();
     }
     
	 private void loadPeFile(String path) throws IOException{
		 File file=new File(path);
		 FileInputStream inStream = new FileInputStream(file);
		 byte[] inOutb = new byte[inStream.available()];
		 inStream.read(inOutb); 
		 inStream.close();
		 ArrayUtil.addByteToLinkedLst(inOutb,pefilelst);

	 } 
	 
	 public static void main(String[] args) throws IOException {
		 LoadFromPe l=new LoadFromPe();
		 String path="G:\\cpu\\是男人就撑过20秒.exe";
		 l.loadPeFile(path);
		 PrintUtil.printHexArray(l.pefilelst);
	 }
	
}
