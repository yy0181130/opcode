package peload.headerbean;

import java.util.ArrayList;
import java.util.List;

import peload.parent.MemoryObj;

public class SectionTableHeaderLst extends MemoryObj {
	private List<SectionTableHeader> sectionTableLst;

	public SectionTableHeaderLst() {
		 sectionTableLst=new ArrayList<SectionTableHeader>();
	}
	public List<SectionTableHeader> getSectionTableLst() {
		return sectionTableLst;
	}

	public void addSectionTable(SectionTableHeader sectionTable) {
		sectionTableLst.add(sectionTable);
	}
    
}
