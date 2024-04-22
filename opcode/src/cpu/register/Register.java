package cpu.register;

import structs.DWord;
import structs.operator.IOperatorNum;

public enum Register {
    // ------8位---------//
    al("al") {

        @Override
        public int getRegSize() {
            return 8;
        }
    },
    ah("ah") {

        @Override
        public int getRegSize() {
            return 8;
        }
    },
    bl("bl") {

 @Override
        public int getRegSize() {
            return 8;
        }
    },
    bh("bh") {

        @Override
        public int getRegSize() {
            return 8;
        }

       
    },
    cl("cl") {

        @Override
        public int getRegSize() {
            return 8;
        }
    },
    ch("ch") {

        @Override
        public int getRegSize() {
            return 8;
        }
    },
    dl("dl") {

        @Override
        public int getRegSize() {
            return 8;
        }
    },

    // ------16位-------//
    ax("ax") {

        int regSize = 16;
        @Override
        public int getRegSize() {
            return 16;
        }
    },
    bx("bx") {

        @Override
        public int getRegSize() {
            return 16;
        }
    },
    cx("cx") {
        @Override
        public int getRegSize() {
            return 16;
        }
    },
    dx("dx") {
        @Override
        public int getRegSize() {
            return 16;
        }
    },
    di("di") {

        @Override
        public int getRegSize() {
            return 16;
        }
    },

    // ------32位---------//
    eax("eax") {

        @Override
        public int getRegSize() {
            return 32;
        }
    },
    ecx("ecx") {
        @Override
        public int getRegSize() {
            return 32;
        }
    },
    edx("edx") {

        @Override
        public int getRegSize() {
            return 32;
        }
    },
    ebx("ebx") {
        @Override
        public int getRegSize() {
            return 32;
        }
    },
    esp("esp") {

        @Override
        public int getRegSize() {
            return 32;
        }
    },
    ebp("ebp") {

        @Override
        public int getRegSize() {
            return 32;
        }
    },
    esi("esi") {

        @Override
        public int getRegSize() {
            return 32;
        }
    },
    edi("edi") {

        @Override
        public int getRegSize() {
            return 32;
        }
    };

    private String registerName;

    private Register(String registerName) {
        this.registerName = registerName;
    }

    public String getRegisterName() {
        return registerName;
    }

    public abstract int getRegSize();

    // ----------------------
    private IOperatorNum value;

    public void setValue(IOperatorNum value) {
        this.value = value;
    };

    public IOperatorNum getValue() {
        return value;
    }

    public DWord getDword() {
        return (DWord) value;
    }

    public static Register getRegisterByName(String registerName) {
        for (Register reg : Register.values()) {
            if (reg.getRegisterName().equals(registerName))
                return reg;
        }
        return null;
    }

    public static void main(String[] args) {
        int reg = Register.ax.getRegSize();
        System.out.println(reg);
    }

}
