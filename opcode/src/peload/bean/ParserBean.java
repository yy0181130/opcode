package peload.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ParserBean {
	@XmlAttribute(required = true)
    private String parserName;
	
   public ParserBean() {
   }
	
	
	public String getParserName() {
		return parserName;
	}

	public ParserBean(String parserName) {
		this.parserName = parserName;
	}
	
	
	
	
}
