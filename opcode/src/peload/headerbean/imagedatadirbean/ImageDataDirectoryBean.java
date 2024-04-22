package peload.headerbean.imagedatadirbean;

import structs.DWord;

public class ImageDataDirectoryBean {
	private DWord virtualAddress;
	private DWord size;	
	private ImageDataDirectoryType type;
	public ImageDataDirectoryBean(DWord virtualAddress, DWord size,ImageDataDirectoryType type) {
		this.virtualAddress = virtualAddress;
		this.size = size;
		this.type = type;
	}
	public DWord getVirtualAddress() {
		return virtualAddress;
	}
	public void setVirtualAddress(DWord virtualAddress) {
		this.virtualAddress = virtualAddress;
	}
	public DWord getSize() {
		return size;
	}
	public void setSize(DWord size) {
		this.size = size;
	}
	public ImageDataDirectoryType getType() {
		return type;
	}
	public void setType(ImageDataDirectoryType type) {
		this.type = type;
	}
	
	
	
	
	
}
