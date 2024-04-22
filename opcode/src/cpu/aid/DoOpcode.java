package cpu.aid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import util.GetACT;
import cpu.parent.CpuObject;

/**
 * 
 * @Description:TODO
 * @author youy
 * @time:2014-3-19
 */

@Service
public class DoOpcode extends CpuObject{
	
	public static Logger logger = LogManager.getLogger(DoOpcode.class);
	
	public void doOpcode(CpuObject parameterObj){
		OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
		String code=operandIo.getOpcode(parameterObj);
		try{
			CpuObject runOpcode=(CpuObject)GetACT.getBean(code);
			runOpcode.doOpcode(parameterObj);
		}catch(Exception ex){
			logger.error("get error:",ex);
			parameterObj.setFindError(true);
		}
		
	}

}
