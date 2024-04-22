package cpu.aid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.stereotype.Service;

import util.ConnectionUtil;
import util.GetACT;
import util.HexCodeUtil;
import cpu.bean.InstructParserBean;
import cpu.operand.Operand;
import cpu.operand.impl.Immediate;
import cpu.operand.impl.Point;
import cpu.operand.impl.RegisterOperand;
import cpu.parent.CpuObject;
import cpu.register.SegementRegister;
import cpu.status.CpuStatus;

@Service
public class SaveMovToDb {
   private Operand firstOperand;
   private Operand secondOperand;
   private String code; 
   
   private void save(CpuObject parameterObj,int type){
       CpuStatus cpuStatus = (CpuStatus) GetACT.getApplicationContext().getBean("cpuStatus"); 
       String address=cpuStatus.getEip();
       String firstOperandStr="";
       String secondOperandStr="";
       if(firstOperand!=null)firstOperandStr=firstOperand.toString();
       if(secondOperand!=null)secondOperandStr=secondOperand.toString();
       InstructParserBean instructBean=(InstructParserBean)parameterObj.getParameter(InstructParserBean.class.getSimpleName());
       List<Integer> frameLst=instructBean.getFrameLst();
       String hexValue=HexCodeUtil.bytesLst2HexString(frameLst);
      // String frameStr=ByteUtil.
       Connection conn=ConnectionUtil.getConnection();
       if(conn==null)return;
       try{
           String insertSql = "insert into opcode(address, type,op1,op2,opcode,bytecodes) values(?,?,?,?,?,?)";
           PreparedStatement ps = conn.prepareStatement(insertSql);
           ps.setString(1, address);
           ps.setInt(2, type);
           ps.setString(3, firstOperandStr);
           ps.setString(4, secondOperandStr);
           ps.setString(5, code);
           ps.setString(6, hexValue);
           ps.executeUpdate();
       }catch(Exception ex){
      }
   }
   
   public void saveCallInstruction(CpuObject parameterObj,String code){
	   this.code=code;
	   save(parameterObj,0);
   }
   
   
  public void chooseMovType(CpuObject parameterObj,String code){
      OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
      Operand firstOperand=operandIo.getFirstOperandType(parameterObj);
      Operand secondOperand=operandIo.getSecondOperandType(parameterObj);
      if(!(firstOperand instanceof Point)) return;
       this.firstOperand=firstOperand;
       this.secondOperand=secondOperand;
       this.code=code;
       if(saveType1(parameterObj))return;
       if(saveType2(parameterObj))return;
       if(saveType3(parameterObj))return;
       if(saveType4(parameterObj))return;
       if(saveType5(parameterObj))return;
       if(saveType6(parameterObj))return;
     }
  
  
  // such as:mov dword ptr ds:[00405c00],eax
  private boolean saveType1(CpuObject parameterObj){
      Point point = (Point)firstOperand;
      boolean isIn=point.isDisplacementNotNull();
      if(!isIn)return false;
      SegementRegister reg=point.getSegmentRegister();
      if(!reg.equals(SegementRegister.ds))return false;
      if(!(secondOperand instanceof RegisterOperand))return false;
      save(parameterObj,1);
      return true;
  }
  
  
  // mov dword ptr ds:[esi],00000094
  private boolean saveType2(CpuObject parameterObj){
      Point point = (Point)firstOperand;
      boolean isIn=point.isSibBaseNotNull();
      if(!isIn)return false;
      SegementRegister reg=point.getSegmentRegister();
      if(!reg.equals(SegementRegister.ds))return false;
      if(!(secondOperand instanceof Immediate))return false;
      save(parameterObj,2);
      return true;
  }
  
  // mov dword ptr ds:[esi+4],edi
  private boolean saveType3(CpuObject parameterObj){
      Point point = (Point)firstOperand;
      boolean isIn=point.issibBaseAndDisplacementNotNull();
      if(!isIn)return false;
      SegementRegister reg=point.getSegmentRegister();
      if(!reg.equals(SegementRegister.ds))return false;
      if(!(secondOperand instanceof RegisterOperand))return false;
      save(parameterObj,3);
      return true;
  }
  
  
  //  mov dword ptr ds:[eax+14],00000410
  private boolean saveType4(CpuObject parameterObj){
      Point point = (Point)firstOperand;
      boolean isIn=point.issibBaseAndDisplacementNotNull();
      if(!isIn)return false;
      SegementRegister reg=point.getSegmentRegister();
      if(!reg.equals(SegementRegister.ds))return false;
      if(!(secondOperand instanceof Immediate))return false;
      save(parameterObj,4);
      return true;
  }
  
  //  mov dword ptr ds:[eax+esi*4],ebx
  private boolean saveType5(CpuObject parameterObj){
      Point point = (Point)firstOperand;
      boolean isIn=point.isBISNotNull();
      if(!isIn)return false;
      SegementRegister reg=point.getSegmentRegister();
      if(!reg.equals(SegementRegister.ds))return false;
      if(!(secondOperand instanceof RegisterOperand))return false;
      save(parameterObj,5);
      return true;
  }
  
  // mov byte ptr ds:[eax+esi],7
  private boolean saveType6(CpuObject parameterObj){
      Point point = (Point)firstOperand;
      boolean isIn=point.isBINotNull();
      if(!isIn)return false;
      SegementRegister reg=point.getSegmentRegister();
      if(!reg.equals(SegementRegister.ds))return false;
      if(!(secondOperand instanceof Immediate))return false;
      save(parameterObj,6);
      return true;
  }
  
  
}
