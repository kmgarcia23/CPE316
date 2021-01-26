public class IType implements Instruction{
    private String label;
    private String opcode;
    private String rs;
    private String rt;
    private String imm;


    public IType(String label, String opcode, String rs, String rt, String imm) {
        Util util = new Util();
        this.label =label;
        this.opcode = opcode;
        this.rs = util.registerParser(rs);
        if(this.label.equals("lw") || this.label.equals("sw")){
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
