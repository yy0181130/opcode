package memory.parent;

import memory.bean.SectionsBean;
import peload.ParserEngine;

public abstract class PhyMemoryObj {
	protected ParserEngine parserEngine;
	protected SectionsBean sectionBean=new SectionsBean();
	public void setSectionBean(SectionsBean sectionBean) {
		this.sectionBean = sectionBean;
	}

	
}
