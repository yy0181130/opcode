package cpu.bean;

import java.util.HashSet;
import java.util.Set;

import structs.DWord;

public class SwithTableBean {
	private Integer swicthtableLength;
	private DWord  cmpIPPosation;
	private boolean isDb;
	private String cmpRegName;
	private Set<String> instructStartPosationVector=new HashSet<String>();//此集合保存了分支表
	private Set<String> branchIndexTable=new HashSet<String>();//此集合保存了分支表
	
    public String getCmpRegName() {
        return cmpRegName;
    }

    
    public void setCmpRegName(String cmpRegName) {
        this.cmpRegName = cmpRegName;
    }

    public Set<String> getBranchIndexTable() {
        return branchIndexTable;
    }
    
    public void addBranchIndexTableElement(String value){
        branchIndexTable.add(value);
    }

    public Set<String> getInstructStartPosationVector() {
        return instructStartPosationVector;
    }
    
    public void addInstructStartPosationVector(String value) {
        instructStartPosationVector.add(value);
    }
       
    public boolean isDb() {
		return isDb;
	}
	public void setDb(boolean isDb) {
		this.isDb = isDb;
	}
	public Integer getSwicthtableLength() {
		return swicthtableLength;
	}
	public void setSwicthtableLength(Integer swicthtableLength) {
		this.swicthtableLength = swicthtableLength;
	}
	public DWord getCmpIPPosation() {
		return cmpIPPosation;
	}
	public void setCmpIPPosation(DWord cmpIPPosation) {
		this.cmpIPPosation = cmpIPPosation;
	}
	
}
