package peload.headerbean.imagedatadirbean;

/**
 * 输出表
 * @author :
 * @time:2014年5月13日
 */
public enum ImageDataDirectoryType {
	//导出表
	IMAGE_DIRECTORY_ENTRY_EXPORT(0){},
	IMAGE_DIRECTORY_ENTRY_IMPORT(1){},
	IMAGE_DIRECTORY_ENTRY_RESOURCE(2){},
	IMAGE_DIRECTORY_ENTRY_EXCEPTION(3){},
	IMAGE_DIRECTORY_ENTRY_SECURITY(4){},
	IMAGE_DIRECTORY_ENTRY_BASERELOC(5){},
	IMAGE_DIRECTORY_ENTRY_DEBUG(6){},
	IMAGE_DIRECTORY_ENTRY_ARCHITECTU(7){},
	IMAGE_DIRECTORY_ENTRY_GLOBALPTR(8){},
	IMAGE_DIRECTORY_ENTRY_TLS(9){},
	IMAGE_DIRECTORY_ENTRY_LOAD_CONFIG(10){},
	IMAGE_DIRECTORY_ENTRY_BOUND_IMPORT(11){},
	IMAGE_DIRECTORY_ENTRY_IAT(12){},
	IMAGE_DIRECTORY_ENTRY_DELAY_IMPORT(13){},
	IMAGE_DIRECTORY_ENTRY_COM_DESCRIPTOR(14){},
	IMAGE_DIRECTORY_Unused(15){};
	
	private int index;
	private ImageDataDirectoryType(int index) {
		this.index=index;
	}
	
	public int getIndex() {
		return index;
	}



	public static ImageDataDirectoryType getTypeByIndex(int index){
		for (ImageDataDirectoryType value : ImageDataDirectoryType.values()) {
			if(value.getIndex()==index)return value;
		}
		return null;
	}
}
