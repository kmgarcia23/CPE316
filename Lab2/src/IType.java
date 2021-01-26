public class IType implements Instruction{
    private String opcode;
    private String rs;
    private String rt;
    private String imm;


    public IType(String opcode, String rs, String rt, String imm) {
        Util util = new Util();
        this.opcode = opcode;
        this.rs = util.registerParser(rs);
        this.rt = util.registerParser(rt);
        this.imm = util.binaryFormater(Integer.parseInt(imm), 0);
    }

    @Override
    public String toString() {
        return opcode + " " + rs + " " + rt + " " + imm;
    }
}
