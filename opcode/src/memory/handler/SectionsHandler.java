package memory.handler;

import java.util.ArrayList;
import java.util.List;

import memory.bean.SectionsBean;
import memory.util.MemoryAddressUtil;
import peload.headerbean.OptionalHeader;
import peload.headerbean.SectionTableHeader;
import peload.headerbean.SectionTableHeaderLst;
import peload.parent.ParserObj;
import structs.DWord;

/**
 * 对节的处理,将节的数据放在一个map中.id是baseAddress+节的rva
 * @author :youy
 * @time:2014年5月17日
 */
public class SectionsHandler {
	private ParserObj parserEngine;
	private SectionsBean sectionBean;

	
	
	public SectionsHandler(ParserObj parserEngine, SectionsBean sectionBean) {
		this.parserEngine = parserEngine;
		this.sectionBean = sectionBean;
	}

	public void doLoadPeSections() {
		SectionTableHeaderLst sectionTHL = (SectionTableHeaderLst) parserEngine.getParameter(SectionTableHeaderLst.class.getSimpleName());
		List<SectionTableHeader> sectionTableLst = sectionTHL.getSectionTableLst();
		for (SectionTableHeader sectionTableHeader : sectionTableLst) {
			loadOneSetion(sectionTableHeader);
		}
	}

	private void loadOneSetion(SectionTableHeader sectionTableHeader) {
		OptionalHeader optionalHeader = (OptionalHeader) parserEngine.getParameter(OptionalHeader.class.getSimpleName());
		DWord sectionRawDataIndex = sectionTableHeader.getPointerToRawData();
		DWord sizeOfRawData = sectionTableHeader.getSizeOfRawData();
		DWord virtualAddress = sectionTableHeader.getVirtualAddress();
		ArrayList<Integer> pefilelst = parserEngine.getPefilelst();
		int peStartIndex = (int) sectionRawDataIndex.getCodeNumber();
		List<Integer> hexCodeLst = new ArrayList<Integer>();
		for (int i = 0; i < sizeOfRawData.getCodeNumber(); i++) {
			hexCodeLst.add(pefilelst.get(peStartIndex + i));
		}
		String virtualAddressAll=MemoryAddressUtil.addressAdd(optionalHeader.getImageBase().toHexString(), virtualAddress.toHexString());
		sectionBean.addSection(virtualAddressAll, hexCodeLst);
	}

}
