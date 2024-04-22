package cpu.instructions;

import java.util.List;

import org.springframework.stereotype.Component;

import cpu.parent.CpuObject;

@Component
public class Pop extends CpuObject {

	public void pop_rAX(List<Integer> frameLst, CpuObject parameterObj) {
		logger.info("pop eax ");
	}

	public void pop_rCX(List<Integer> frameLst, CpuObject parameterObj) {
		logger.info("pop ecx ");
	}

	public void pop_rDX(List<Integer> frameLst, CpuObject parameterObj) {
		logger.info("pop edx ");
	}

	public void pop_rBX(List<Integer> frameLst, CpuObject parameterObj) {
		logger.info("pop ebx ");
	}

	public void pop_rSP(List<Integer> frameLst, CpuObject parameterObj) {
		logger.info("pop esp ");
	}

	public void pop_rBP(List<Integer> frameLst, CpuObject parameterObj) {
		logger.info("pop ebp ");
	}

	public void pop_rSI(List<Integer> frameLst, CpuObject parameterObj) {
		logger.info("pop esi ");
	}

	public void pop_rDI(List<Integer> frameLst, CpuObject parameterObj) {
		logger.info("pop edi ");
	}

}
