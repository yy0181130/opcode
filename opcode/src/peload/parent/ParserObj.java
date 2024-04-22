package peload.parent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import util.ArrayUtil;

public class ParserObj {
	public static Logger logger = LogManager.getLogger(ParserObj.class);
	private final boolean isPrintLog=true;
	 // 解码
	 public void decode(ParserObj parserObj){}
	 
	private ArrayList<Integer> pefilelst=new ArrayList<Integer>();
	// 存放参数的list
	private List<MemoryObj> memoryPhyBeanLst=new ArrayList<MemoryObj>();
	
	public void addMemoryBean(MemoryObj bean) {
		memoryPhyBeanLst.add(bean);
	}

	public void clearParameterList() {
		memoryPhyBeanLst.clear();
	}

	public MemoryObj getParameter(String className) {
		for (int i = 0; i < memoryPhyBeanLst.size(); i++) {
			MemoryObj obj = memoryPhyBeanLst.get(i);
			String pathName = obj.getClass().getSimpleName();
			if (pathName.equals(className.trim()))
				return obj;
		}
		return null;
	}
	
	
	public ArrayList<Integer> getPefilelst() {
		return pefilelst;
	}

	public void loadPeFile(String path) throws IOException{
		 File file=new File(path);
		 FileInputStream inStream = new FileInputStream(file);
		 byte[] inOutb = new byte[inStream.available()];
		 inStream.read(inOutb); 
		 inStream.close();
		 ArrayUtil.addByteToLinkedLst(inOutb,pefilelst);
    }
	 
	
	public void infoLog(Logger logger,String context){
	    if(isPrintLog)
	      logger.info(context);
	}
	
}
