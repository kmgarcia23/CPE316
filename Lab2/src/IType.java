public class IType implements Instruction{
    private final String opcode;
    private final String rs;
    private final String rt;
    private String imm;


    public IType(String label, String opcode, String rs, String rt, String imm) {
        Util util = new Util();
        this.opcode = opcode;
        this.rs = util.registerParser(rs);
        if(label.equals("lw") || label.equals("sw")){
            StringBuilder strbld = new StringBuilder();
            boolean immfound = false;
            for(char i : imm.toCharArray()){
                if(i != '(' && !immfound)strbld.append(i);
                else if (i == '(') {
                    immfound = true;
                    this.imm = util.binaryFormater(Integer.parseInt(strbld.toString()), 0);
                    strbld = new StringBuilder();
                }
                else if(i != ')')strbld.append(i);
                else break;
            }
            this.rt = util.registerParser(strbld.toString());

        }
        else{
            this.imm = util.binaryFormater(Integer.parseInt(imm), 0);
            this.rt = util.registerParser(rt);
        }
    }

    @Override
    public String toString() {
        return opcode + " " + rs + " " + rt + " " + imm;
    }
}
