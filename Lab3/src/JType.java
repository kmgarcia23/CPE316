/*
CPE-315 Lab3
Kevin Garcia and Peter Phillips
 */
public class JType implements Instruction{
    private final String opcode;
    private final String address;
    private final String name;
    private Util util;



    public JType(String name, String opcode, String address, Util util) {

        this.opcode = opcode;
        this.address = util.binaryFormater(Integer.parseInt(address), 1);
        this.util = util;
        this.name = name;
    }

    @Override
    public String toString() {
        return opcode + " " + address;
    }

    @Override
    public int execute(int pc) {
        switch (this.name){
            case "j":
                return Integer.parseInt(this.address);
            case "jal":
                util.regData.replace("ra", pc);
                return Integer.parseInt(this.address);

        }
        return pc;
    }
}
