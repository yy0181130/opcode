package peload.headerbean;

import peload.parent.MemoryObj;

public class PeHeader extends MemoryObj{
	private int peHaderIndex;
    private int endPeHeaderIndex;
    
	public int getEndPeHeaderIndex() {
		return endPeHeaderIndex;
	}

	public void setEndPeHeaderIndex(int endPeHeaderIndex) {
		this.endPeHeaderIndex = endPeHeaderIndex;
	}

	public int getPeHaderIndex() {
		return peHaderIndex;
	}

	public void setPeHaderIndex(int peHaderIndex) {
		this.peHaderIndex = peHaderIndex;
	}
	
	
}
