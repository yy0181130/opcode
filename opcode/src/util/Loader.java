/**
 * 
 */
package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import cpu.CpuEngine;
import cpu.bean.OneByteOpcodeBean;
import cpu.bean.OneByteOpcodeBeanLst;




/**
 * @author youy
 * 
 *         2012-10-26
 */
public class Loader extends org.springframework.beans.factory.config.PropertyPlaceholderConfigurer {
	

	private static String getResourcePath() {
		String className = Loader.class.getName();
		String classNamePath = className.replace(".", "/") + ".class";
		URL is = Loader.class.getClassLoader().getResource(
				classNamePath);
		String path = is.getFile();
		path = StringUtils.replace(path, "%20", " ");
		return StringUtils.removeStart(path, "/");
	}
	
	public static String getResourcePathByClazz(Class clazz) {
		String className = clazz.getName();
		String classNamePath = className.replace(".", "/") + ".class";
		URL is = clazz.getClassLoader().getResource(classNamePath);
		String path = is.getFile();
		path = StringUtils.replace(path, "%20", " ");
		return StringUtils.removeStart(path, "/");
	}
	
    /* (non-Javadoc)
	 * @see org.springframework.core.io.support.PropertiesLoaderSupport#setLocations(org.springframework.core.io.Resource[])
	 */
	@Override
	public void setLocations(Resource[] locations) {
		int i=0;
		for (Resource resource : locations) {
			File file = new File(getResourcePath()).getParentFile().getParentFile().getParentFile().getParentFile().getParentFile().getParentFile();
	        File f = new File(file.getAbsolutePath() + "\\TMRAcquisition-parameter\\"+ resource.getFilename());
	        try {
				locations[i++]=new UrlResource(f.toURL());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		super.setLocations(locations);
	}
	
	public static String getTMRFilePath(){
		File file = new File(getResourcePath()).getParentFile().getParentFile().getParentFile().getParentFile().getParentFile().getParentFile();
		String path = file.getAbsolutePath() + "\\TMRAcquisition-parameter\\";
		return path;
	}
	
	public static String getListeningResPath(){
		File file = new File(getResourcePath()).getParentFile().getParentFile().getParentFile().getParentFile().getParentFile();
		String path = file.getAbsolutePath() + "\\res\\";
		return path;
	}
	
	
	
	public static String getPropertiesValue(String fileName,String propertieName){
		try{
			File file=new File(getTMRFilePath()+fileName);
	    	FileInputStream infile = new FileInputStream(file);
	       
			Properties props = new Properties();
			props.load(infile);
			String value = props.getProperty(propertieName);
			return value;
		 }catch(Exception ex){
			return null;
		}
	}
	
	public static Properties getPropertiesByName(String pathName) {
	    // 获取自动进程配置文件
		File file = new File(pathName);
		try {
			// 获取Properties对象并加载文件
			FileInputStream fis = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fis);
			fis.close();
			return properties;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static Object getObjFromXmlFile(Class sourcePath,String fileName,Class retclass){
    	String resourcePath = Loader.getResourcePathByClazz(sourcePath);
  		String path = new File(resourcePath).getParentFile().getAbsolutePath() + "//res//"+fileName;
  	    try {
			JAXBContext context = JAXBContext.newInstance(retclass);
			File file = new File(path);//用于输出的XML文档
			Marshaller marshaller = context.createMarshaller();//创建编组
			Object retObj = (Object) context.createUnmarshaller().unmarshal(file);//从文件解组到一个新对象中
		   return retObj;
  	      } catch (JAXBException e) {
			e.printStackTrace();
		  }
		return null;
	  }
	
	
	private void test_getListeningResPath(){
		String p=getListeningResPath();
		System.out.println(p);
	}
	
	public static void main(String[] args) {
		Loader l=new Loader();
		l.test_getListeningResPath();
	}
	
	
	
}
