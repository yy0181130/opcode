package cpu.bean;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import cpu.parent.CpuBeanObj;



@XmlRootElement
@XmlType(propOrder = {"modrmBeanLst", "pfx", "mod", "group","opCode"})
public class GroupOpcodeBean {
	@XmlAttribute
	protected String opCode;
	@XmlAttribute
	protected String group;
	@XmlAttribute
	protected String mod;
	@XmlAttribute(required = false)
	protected String pfx;
	
	@XmlElement(name = "encoding")
	protected List<GroupOpcodeModRmBean> modrmBeanLst=new ArrayList<GroupOpcodeModRmBean>();
    
	public GroupOpcodeBean() {
		}
	
	public GroupOpcodeBean(String opCode, String group, String mod, String pfx) {
		
		this.opCode = opCode;
		this.group = group;
		this.mod = mod;
		this.pfx = pfx;
	}



	public String getOpCode() {
		return opCode;
	}

	public String getGroup() {
		return group;
	}

	public String getMod() {
		return mod;
	}

	public String getPfx() {
		return pfx;
	}

	public List<GroupOpcodeModRmBean> getModrmBeanLst() {
		return modrmBeanLst;
	}
    
	public void addGroupOpcodeModRmBean(GroupOpcodeModRmBean groupModBean){
		modrmBeanLst.add(groupModBean);
	}
	
	private void test_1() throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(GroupOpcodeBean.class);
		// 下面代码演示将对象转变为xml
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);// 设置编组属性，使得输出的XML文档进行格式化（提供缩进）
		GroupOpcodeModRmBean bean1 = new GroupOpcodeModRmBean("000","add");
		GroupOpcodeModRmBean bean2 = new GroupOpcodeModRmBean("001","or");
		GroupOpcodeBean g=new GroupOpcodeBean("80","1","11","");
		g.addGroupOpcodeModRmBean(bean1);
		g.addGroupOpcodeModRmBean(bean2);
         FileWriter fw = new FileWriter("test2.xml");
		marshaller.marshal(g, fw);
	 }
	public static void main(String[] args) throws JAXBException, IOException {
		GroupOpcodeBean g=new GroupOpcodeBean();
		g.test_1();
	}
	
	
}
