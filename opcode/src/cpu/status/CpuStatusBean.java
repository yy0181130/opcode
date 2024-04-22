package cpu.status;

import structs.DWord;
import structs.Word;

public class CpuStatusBean {
    private DWord eax; 
    private DWord ecx; 
    private DWord edx;
    private DWord ebx;
    private DWord esp;
    private DWord ebp;
    private DWord esi;
    private DWord edi;
    //
    private DWord eip;//指令的位置
    
    //
    private Word  es;
    private Word  cs;
    private Word  ss;
    private Word  ds;
    private Word  fs;
    private Word  gs;
    
    //
    private boolean c;
    private boolean p;
    private boolean a;
    private boolean z;
    private boolean s;
    private boolean t;
    private boolean d;
    private boolean o;
    
    //
    private DWord lastError;
    private DWord efl;
	public DWord getEax() {
		return eax;
	}
	public void setEax(DWord eax) {
		this.eax = eax;
	}
	public DWord getEcx() {
		return ecx;
	}
	public void setEcx(DWord ecx) {
		this.ecx = ecx;
	}
	public DWord getEdx() {
		return edx;
	}
	public void setEdx(DWord edx) {
		this.edx = edx;
	}
	public DWord getEbx() {
		return ebx;
	}
	public void setEbx(DWord ebx) {
		this.ebx = ebx;
	}
	public DWord getEsp() {
		return esp;
	}
	public void setEsp(DWord esp) {
		this.esp = esp;
	}
	public DWord getEbp() {
		return ebp;
	}
	public void setEbp(DWord ebp) {
		this.ebp = ebp;
	}
	public DWord getEsi() {
		return esi;
	}
	public void setEsi(DWord esi) {
		this.esi = esi;
	}
	public DWord getEdi() {
		return edi;
	}
	public void setEdi(DWord edi) {
		this.edi = edi;
	}
	public DWord getEip() {
		if(eip==null)eip=new DWord();
		return eip;
	}
	public void setEip(DWord eip) {
		this.eip = eip;
	}
	public Word getEs() {
		return es;
	}
	public void setEs(Word es) {
		this.es = es;
	}
	public Word getCs() {
		return cs;
	}
	public void setCs(Word cs) {
		this.cs = cs;
	}
	public Word getSs() {
		return ss;
	}
	public void setSs(Word ss) {
		this.ss = ss;
	}
	public Word getDs() {
		return ds;
	}
	public void setDs(Word ds) {
		this.ds = ds;
	}
	public Word getFs() {
		return fs;
	}
	public void setFs(Word fs) {
		this.fs = fs;
	}
	public Word getGs() {
		return gs;
	}
	public void setGs(Word gs) {
		this.gs = gs;
	}
	public boolean isC() {
		return c;
	}
	public void setC(boolean c) {
		this.c = c;
	}
	public boolean isP() {
		return p;
	}
	public void setP(boolean p) {
		this.p = p;
	}
	public boolean isA() {
		return a;
	}
	public void setA(boolean a) {
		this.a = a;
	}
	public boolean isZ() {
		return z;
	}
	public void setZ(boolean z) {
		this.z = z;
	}
	public boolean isS() {
		return s;
	}
	public void setS(boolean s) {
		this.s = s;
	}
	public boolean isT() {
		return t;
	}
	public void setT(boolean t) {
		this.t = t;
	}
	public boolean isD() {
		return d;
	}
	public void setD(boolean d) {
		this.d = d;
	}
	public boolean isO() {
		return o;
	}
	public void setO(boolean o) {
		this.o = o;
	}
	public DWord getLastError() {
		return lastError;
	}
	public void setLastError(DWord lastError) {
		this.lastError = lastError;
	}
	public DWord getEfl() {
		return efl;
	}
	public void setEfl(DWord efl) {
		this.efl = efl;
	}
    
    
}
