/*
CPE-315 Lab3
Kevin Garcia and Peter Phillips
 */
public class IType implements Instruction{
    private final String opcode;
    private final String rs;
    private final String rt;
    private String imm;


    public IType(String label, String opcode, String rs, String rt, String imm) {
        Util util = new Util();
        this.opcode = opcode;
        if(label.equals("lw") || label.equals("sw")){
            this.imm = util.binaryFormater(Integer.parseInt(rt), 0);
            this.rt = util.registerParser(rs);
            this.rs = util.registerParser(imm);
        }
        else{
            this.imm = util.binaryFormater(Integer.parseInt(imm), 0);
            this.rt = util.registerParser(rt);
            this.rs = util.registerParser(rs);
        }
    }

    @Override
    public String toString() {
        return opcode + " " + rs + " " + rt + " " + imm;
    }
}
