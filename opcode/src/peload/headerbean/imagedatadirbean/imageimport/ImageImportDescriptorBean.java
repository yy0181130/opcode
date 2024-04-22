package peload.headerbean.imagedatadirbean.imageimport;

import java.util.ArrayList;
import java.util.List;

import peload.headerbean.imagedatadirbean.componet.ImageThunkData32Bean;
import peload.parent.MemoryObj;
import structs.DWord;

public class ImageImportDescriptorBean extends MemoryObj{
	private DWord characteristics;//别名
	private DWord originalFirstThunk;//指向MAGE_THUNK_DATA结构数组的指针;
	private DWord timeDateStamp; //无用
	private DWord forwarderChain;//无用,成员ForwarderChain指向链接索引(DLL转接比如.PE文件A引入D又引入了B.dll)
	
	private DWord name;  //成员Name也是一个RVA.它指向一个导入DLL文件的文件名.
	private String nameValue;
	private DWord firstThunk; //成员FirstThunk指向引入函数真实地址单元处的RVA
	private List<ImageThunkData32Bean> medLst=new ArrayList<ImageThunkData32Bean>();
	
	public void addMed(ImageThunkData32Bean bean){
		medLst.add(bean);
	}
	public List<ImageThunkData32Bean> getMedLst() {
		return medLst;
	}



	public String getNameValue() {
		return nameValue;
	}
	public void setNameValue(String nameValue) {
		this.nameValue = nameValue;
	}
	public DWord getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(DWord characteristics) {
		this.characteristics = characteristics;
	}
	public DWord getOriginalFirstThunk() {
		return originalFirstThunk;
	}
	public void setOriginalFirstThunk(DWord originalFirstThunk) {
		this.originalFirstThunk = originalFirstThunk;
	}
	public DWord getTimeDateStamp() {
		return timeDateStamp;
	}
	public void setTimeDateStamp(DWord timeDateStamp) {
		this.timeDateStamp = timeDateStamp;
	}
	public DWord getForwarderChain() {
		return forwarderChain;
	}
	public void setForwarderChain(DWord forwarderChain) {
		this.forwarderChain = forwarderChain;
	}
	public DWord getName() {
		return name;
	}
	public void setName(DWord name) {
		this.name = name;
	}
	public DWord getFirstThunk() {
		return firstThunk;
	}
	public void setFirstThunk(DWord firstThunk) {
		this.firstThunk = firstThunk;
	}
	
	
	
}
