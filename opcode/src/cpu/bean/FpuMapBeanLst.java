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
public class FpuMapBeanLst {
    
    @XmlElement(name = "fpubean")
    protected List<FpuMapBean> fpuMapBeanLst=new ArrayList<FpuMapBean>();

    public FpuMapBeanLst() {
    }
    
    public List<FpuMapBean> getFpuMapBeanLst() {
        return fpuMapBeanLst;
    }
    
    public void addFpuMapBean(FpuMapBean fpuMapBean){
        fpuMapBeanLst.add(fpuMapBean);
    }
    
    
    
    private void test_1() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(FpuMapBeanLst.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);// 设置编组属性，使得输出的XML文档进行格式化（提供缩进）
  
        FpuMapContextBean f=new FpuMapContextBean("000",null,"test",null,null);
        FpuMapContextBean f2=new FpuMapContextBean("001",null,"test",null,null);
        FpuMapContextBean f3=new FpuMapContextBean(null,"d1","test",null,null);
        
        
        FpuMapBean fbean=new FpuMapBean("dd");
       
        fbean.addContext(f);
        fbean.addContext(f2);
        fbean.addContext(f3);
        
        FpuMapBeanLst fLst=new FpuMapBeanLst();
        fLst.addFpuMapBean(fbean);
        FileWriter fw = new FileWriter("test2.xml");
        marshaller.marshal(fLst, fw);
        
    }
    
    public static void main(String[] args) throws JAXBException, IOException {
        FpuMapBeanLst f=new FpuMapBeanLst();
        f.test_1();
    }
    
}
