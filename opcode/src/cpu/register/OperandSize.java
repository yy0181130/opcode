package cpu.register;

public enum OperandSize {
    qwordptr("qword ptr") {
        @Override
        public  int getOperandLength() {
            return 64;
        }
       },
   dwordptr("dword ptr") {
	@Override
	public	int getOperandLength() {
		return 32;
	}
   },
   wordptr("word ptr") {
	@Override
	public	int getOperandLength() {
		return 16;
	}
},
   byteptr("byte ptr") {
	@Override
	public	int getOperandLength() {
		return 8;
	}
};
   
   private String value;
   private OperandSize(String value){
	   this.value=value;
   }
   
   
   
	public String getValue() {
	return value;
    }
	
  public	abstract int getOperandLength();



	public static OperandSize getOperandSize(int size){
		switch(size){
		case 64:return qwordptr;
		case 32:return dwordptr;
		case 16:return wordptr;
		case 8: return byteptr;
		}
		return null;
	}
   
}
