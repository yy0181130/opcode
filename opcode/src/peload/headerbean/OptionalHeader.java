package peload.headerbean;

import java.util.ArrayList;
import java.util.List;

import peload.headerbean.imagedatadirbean.ImageDataDirectoryBean;
import peload.headerbean.imagedatadirbean.imageimport.ImageImportDescriptorBean;
import peload.parent.MemoryObj;
import structs.DWord;

public class OptionalHeader extends MemoryObj {
	private int optionnalHeaderSize;
	private DWord addressOfEntryPoint;
	private DWord baseOfCode;
	private DWord imageBase;
	private List<ImageDataDirectoryBean> lst;
	//导入列表的集合
	private List<ImageImportDescriptorBean> importDesBeanLst;
    
	public OptionalHeader() {
		lst = new ArrayList<ImageDataDirectoryBean>();
		importDesBeanLst=new ArrayList<ImageImportDescriptorBean>();
	}

	public List<ImageDataDirectoryBean> getLst() {
		return lst;
	}
	
	public void addImportDesBean(ImageImportDescriptorBean b){
		importDesBeanLst.add(b);
	}

	public List<ImageImportDescriptorBean> getImportDesBeanLst() {
		return importDesBeanLst;
	}

	public DWord getBaseOfCode() {
		return baseOfCode;
	}

	public void setBaseOfCode(DWord baseOfCode) {
		this.baseOfCode = baseOfCode;
	}

	public int getOptionnalHeaderSize() {
		return optionnalHeaderSize;
	}

	public void setOptionnalHeaderSize(int optionnalHeaderSize) {
		this.optionnalHeaderSize = optionnalHeaderSize;
	}

	public DWord getAddressOfEntryPoint() {
		return addressOfEntryPoint;
	}

	public void setAddressOfEntryPoint(DWord addressOfEntryPoint) {
		this.addressOfEntryPoint = addressOfEntryPoint;
	}

	public DWord getImageBase() {
		return imageBase;
	}

	public void setImageBase(DWord imageBase) {
		this.imageBase = imageBase;
	}

}
