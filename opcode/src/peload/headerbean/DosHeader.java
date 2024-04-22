package peload.headerbean;

import peload.parent.MemoryObj;

public class DosHeader extends MemoryObj{
	//文件头所在的位置
	private Integer e_lfanew;

	public Integer getE_lfanew() {
		return e_lfanew;
	}

	public void setE_lfanew(Integer e_lfanew) {
		this.e_lfanew = e_lfanew;
	}

}
