package memory.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import peload.headerbean.OptionalHeader;

/**
 * 节Bean
 * @author youy
 *
 */
public class SectionsBean {
     private Map<String,List<Integer>> sectionMap=new HashMap<String,List<Integer>>();
  

	public void addSection(String sectionStartIndex,List<Integer> hexCodeLst){
    	 sectionMap.put(sectionStartIndex, hexCodeLst);
     }

	public Map<String, List<Integer>> getSectionMap() {
		return sectionMap;
	}
     
}
