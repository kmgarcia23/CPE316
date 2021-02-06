/*
CPE-315 Lab3
Kevin Garcia and Peter Phillips
 */
public class RType implements Instruction{
    private final String opcode;
    private final String rs;
    private final String rt;
    private final String rd;
    private final String shamt;
    private final String funct;

    public RType(String rs, String rt, String rd, String shamt, String funct) {
        Util util = new Util();
        this.opcode = "000000";
        this.rs = util.registerParser(rs);
        this.rt = util.registerParser(rt);
        this.rd = util.registerParser(rd);
        this.shamt = util.binaryFormater(Integer.parseInt(shamt), 2);
        this.funct = funct;
    }

    @Override
    public String toString() {
        return opcode + " " + rs + " " + rt + " " + rd + " " + shamt + " " + funct;
    }
}
