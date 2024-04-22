package cpu.bean;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class GroupOpcodeBeanLst {
	 @XmlElement(name = "groupopcode")
	 List<GroupOpcodeBean> groupOpcodeBeanLst;

	 public GroupOpcodeBeanLst() {
		 groupOpcodeBeanLst=new ArrayList<GroupOpcodeBean>();
	}
	 
	public List<GroupOpcodeBean> getGroupOpcodeBeanLst() {
		return groupOpcodeBeanLst;
	}
	 
	public void addGroupOpCode(GroupOpcodeBean groupOpcode){
		groupOpcodeBeanLst.add(groupOpcode);
	}
	 
	
	private void test_1() throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(GroupOpcodeBeanLst.class);
		// 下面代码演示将对象转变为xml
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);// 设置编组属性，使得输出的XML文档进行格式化（提供缩进）
		GroupOpcodeModRmBean bean1 = new GroupOpcodeModRmBean("000","add");
		GroupOpcodeModRmBean bean2 = new GroupOpcodeModRmBean("001","or");
		GroupOpcodeBean g=new GroupOpcodeBean("80","1","11","");
		g.addGroupOpcodeModRmBean(bean1);
		g.addGroupOpcodeModRmBean(bean2);
		
		GroupOpcodeBean g2=new GroupOpcodeBean("81","1","11","");
		g2.addGroupOpcodeModRmBean(bean1);
		g2.addGroupOpcodeModRmBean(bean2);
		
		GroupOpcodeBeanLst gL=new GroupOpcodeBeanLst();
		gL.addGroupOpCode(g);
		gL.addGroupOpCode(g2);
		
        FileWriter fw = new FileWriter("test2.xml");
		marshaller.marshal(gL, fw);
	 }
	
	public static void main(String[] args) throws JAXBException, IOException {
		GroupOpcodeBeanLst g=new GroupOpcodeBeanLst();
		g.test_1();
	}
}
