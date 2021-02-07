/*
CPE-315 Lab3
Kevin Garcia and Peter Phillips
 */
public class JType implements Instruction{
    private final String opcode;
    private final String address;
    private Util util;



    public JType(String opcode, String address, Util util) {

        this.opcode = opcode;
        this.address = util.binaryFormater(Integer.parseInt(address), 1);
        this.util = util;
    }

    @Override
    public String toString() {
        return opcode + " " + address;
    }

    @Override
    public int execute(int pc) {
        return pc;
    }
}
