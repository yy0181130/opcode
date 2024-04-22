package cpu.bean;

import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"p2", "p1", "handler", "modrm","reg","opcodeName"})
public class FpuMapContextBean {
    @XmlAttribute(required = false)
    protected String opcodeName;
    @XmlAttribute(required = false)
    protected String reg;
    @XmlAttribute(required = false)
    protected String modrm;
    @XmlAttribute(required = true)
    protected String handler;
    @XmlAttribute(required = false)
    protected String p1;
    @XmlAttribute(required = false)
    protected String p2;
    
    
    public FpuMapContextBean() {
    }
    public FpuMapContextBean(
            
            String reg,
            String modrm,
            String handler,
            String p1,
            String p2) {
        this.reg = reg;
        this.modrm = modrm;
        this.handler = handler;
        this.p1 = p1;
        this.p2 = p2;
    }

    public String getReg() {
        return reg;
    }
    
    
    public String getModrm() {
        return modrm;
    }
    public String getHandler() {
        return handler;
    }
    
    public String getP1() {
        return p1;
    }
    
    public String getP2() {
        return p2;
    }
    
    
    
    
    public String getOpcodeName() {
        return opcodeName;
    }
    public static void main(String[] args) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(FpuMapContextBean.class);
        // 下面代码演示将对象转变为xml
        Marshaller m = context.createMarshaller();
        FpuMapContextBean f=new FpuMapContextBean("000",null,"test",null,null);
        FileWriter fw = new FileWriter("test.xml");
        m.marshal(f, fw);
    }
   
    
}
