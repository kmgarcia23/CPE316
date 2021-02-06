/*
CPE-315 Lab3
Kevin Garcia and Peter Phillips
 */
public class JType implements Instruction{
    private final String opcode;
    private final String address;



    public JType(String opcode, String address) {
        Util util = new Util();
        this.opcode = opcode;
        this.address = util.binaryFormater(Integer.parseInt(address), 1);
    }

    @Override
    public String toString() {
        return opcode + " " + address;
    }
}
