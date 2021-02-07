/*
CPE-315 Lab3
Kevin Garcia and Peter Phillips
 */
public class IType implements Instruction{
    private final String opcode;
    private final String rs;
    private final String rt;
    private final String imm;
    private Util util;
    private final String name;



    public IType(String name, String opcode, String rs, String rt, String imm, Util util) {
        this.opcode = opcode;
        if (rs.equals("zero")) rs = "0";
        if (rt.equals("zero")) rt = "0";
        if(name.equals("lw") || name.equals("sw")){
            this.imm = rt;
            this.rt = rs;
            this.rs = imm;
        }
        else{
            this.imm = imm;
            this.rt = rt;
            this.rs = rs;
        }
        this.util = util;
        this.name = name;
    }

    @Override
    public String toString() {
        return opcode + " " + rs + " " + rt + " " + imm;
    }

    @Override
    public int execute(int pc) {
        if(rt.equals("0")) return pc;
        switch (this.name){
            case "addi":
                util.regData.replace(rt, util.regData.get(rs) + Integer.parseInt(imm));
                break;
            case "beq":
                if(util.regData.get(rs).equals(util.regData.get(rt))) return Integer.parseInt(this.imm);
                else return pc;
            case "bne":
                if(!util.regData.get(rs).equals(util.regData.get(rt))) return Integer.parseInt(this.imm);
                else return pc;
            case "lw":
                util.regData.replace(rt, util.memData.get(util.regData.get(rs) + Integer.parseInt(imm)));
                break;
            case "sw":
                util.memData.set()
        }
        return pc;
    }
}
