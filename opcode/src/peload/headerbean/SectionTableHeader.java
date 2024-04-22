package peload.headerbean;

import peload.parent.MemoryObj;
import structs.AsciiBytes;
import structs.DWord;

public class SectionTableHeader extends MemoryObj{
    private AsciiBytes name;
    private DWord virtualAddress;//虚拟地址的RVA
    private DWord sizeOfRawData;//需要加载多少数据量
    private DWord pointerToRawData;//从文件中加载的位置信息
    private DWord characteristics;//块的属性标志，可读可写可执行等
	
	public AsciiBytes getName() {
		return name;
	}
	public void setName(AsciiBytes name) {
		this.name = name;
	}
	public DWord getVirtualAddress() {
		return virtualAddress;
	}
	public void setVirtualAddress(DWord virtualAddress) {
		this.virtualAddress = virtualAddress;
	}
	public DWord getSizeOfRawData() {
		return sizeOfRawData;
	}
	public void setSizeOfRawData(DWord sizeOfRawData) {
		this.sizeOfRawData = sizeOfRawData;
	}
	public DWord getPointerToRawData() {
		return pointerToRawData;
	}
	public void setPointerToRawData(DWord pointerToRawData) {
		this.pointerToRawData = pointerToRawData;
	}
	public DWord getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(DWord characteristics) {
		this.characteristics = characteristics;
	}
    
    
    
}
