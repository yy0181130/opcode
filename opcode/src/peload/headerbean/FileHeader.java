package peload.headerbean;

import peload.parent.MemoryObj;
import structs.Word;

public class FileHeader extends MemoryObj {
	private final int fileHeaderSize = 20;// Peheader 4byte ;fileHeader 20字节
	private Word sizeOfOptionalHeader;// 可选头的大小
	private Word numberOfSections;// 节的大小
	private int fileHeaderEndIndex;

	public int getFileHeaderEndIndex() {
		return fileHeaderEndIndex;
	}

	public Word getNumberOfSections() {
		return numberOfSections;
	}

	public void setNumberOfSections(Word numberOfSections) {
		this.numberOfSections = numberOfSections;
	}

	public void setFileHeaderEndIndex(int fileHeaderEndIndex) {
		this.fileHeaderEndIndex = fileHeaderEndIndex;
	}

	public Word getSizeOfOptionalHeader() {
		return sizeOfOptionalHeader;
	}

	public void setSizeOfOptionalHeader(Word sizeOfOptionalHeader) {
		this.sizeOfOptionalHeader = sizeOfOptionalHeader;
	}

	public int getFileHeaderSize() {
		return fileHeaderSize;
	}
}
