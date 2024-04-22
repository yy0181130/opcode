package cpu.register;

public enum SegementRegister {
	cs("cs") {
		@Override
		public String getSegCode() {
			// TODO Auto-generated method stub
			return "001";
		}
	},
	ds("ds") {
		@Override
		public String getSegCode() {
			// TODO Auto-generated method stub
			return "011";
		}
	},
	ss("ss") {
		@Override
		public String getSegCode() {
			return "010";
		}
	},
	es("es") {
		@Override
		public String getSegCode() {
			// TODO Auto-generated method stub
			return "000";
		}
	},
	fs("fs") {
		@Override
		public String getSegCode() {
			// TODO Auto-generated method stub
			return "100";
		}
	},
	gs("gs") {
		@Override
		public String getSegCode() {
			// TODO Auto-generated method stub
			return "101";
		}
	};
	
    private String registerName;
    public abstract String getSegCode();
    private SegementRegister(String registerName){
   	 this.registerName=registerName;
    }
	public String getRegisterName() {
		return registerName;
	}
	
	public static SegementRegister getSegByCode(String code){
		for (SegementRegister reg : SegementRegister.values()){
	        if(reg.getSegCode().equals(code))return reg;
		}
		return null;
	}
	
}
