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
    private final String name;
    private Util util;

    public RType(String name, String rs, String rt, String rd, String shamt, String funct, Util util) {
        this.opcode = "000000";
        if (rs.equals("zero")) rs = "0";
        if (rt.equals("zero")) rt = "0";
        if (rd.equals("zero")) rd = "0";
        this.rs = rs;
        this.rt = rt;
        this.rd = rd;
        this.shamt = shamt;
        this.funct = funct;
        this.util = util;
        this.name = name;
    }

    @Override
    public String toString() {
        return opcode + " " + rs + " " + rt + " " + rd + " " + shamt + " " + funct;
    }

    @Override
    public int execute(int pc) {
        if(rd.equals("0")) return pc;
        switch (this.name){
            case "and":
                util.regData.replace(rd, util.regData.get(rs) & util.regData.get(rt));
                break;
            case "or":
                util.regData.replace(rd, util.regData.get(rs) | util.regData.get(rt));
                break;
            case "add":
                util.regData.replace(rd, util.regData.get(rs) + util.regData.get(rt));
                break;
            case "sub":
                util.regData.replace(rd, util.regData.get(rs) - util.regData.get(rt));
                break;
            case "sll":
                util.regData.replace(rd, util.regData.get(rt) << Integer.parseInt(shamt));
                break;
            case "slt":
                if (util.regData.get(rs) < util.regData.get(rt))  util.regData.replace(rd, 1);
                else util.regData.replace(rd, 0);
                break;
            case "jr":
                return util.regData.get(rs);

        }
        return pc;
    }
}
