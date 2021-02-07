import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

/*
CPE-315 Lab3
Kevin Garcia and Peter Phillips
 */
public class Util {

    public HashMap<String, Integer> regData;
    public ArrayList<Integer> memData;

    public Util(HashMap<String, Integer> regData, ArrayList<Integer> memData) {
        this.regData = regData;
        this.memData = memData;
        regSet();
        memSet();
    }

    private String decimalToBinary(int num){
        if (num > 1){
            return decimalToBinary(Math.floorDiv(num, 2)) + String.valueOf(num % 2);
        }
        else{
            return String.valueOf(num);
        }
    }
    // imm =
    // 0 for 16 bit imm
    // 1 for 26 bit address
    // 2 for 5 bit register or shamt
    // 3 for 6 bit opcode or funct
    public String binaryFormater(int num, int imm){
        String str = decimalToBinary(Math.abs(num));
        StringBuilder out = new StringBuilder();
        if(imm == 0){
            for(int i = 0; i< 16 - str.length(); i++){
                out.append("0");
            }
        }
        else if(imm == 1){
            for(int i = 0; i< 26 - str.length(); i++){
                out.append("0");
            }
        }
        else if (imm == 2) {
            for (int i = 0; i < 5 - str.length(); i++) {
                out.append("0");
            }
        }
        else if (imm == 3) {
            for (int i = 0; i < 6 - str.length(); i++) {
                out.append("0");
            }
        }
        else return "Binary Format Error";
        if(num >= 0) return out.toString() + str;
        else {
            String neg = out.toString() + str;
            StringBuilder negout = new StringBuilder();
            for (char bin : neg.toCharArray()) {
                if (bin == '0') negout.append('1');
                else negout.append('0');
            }
            StringBuilder comp2 = new StringBuilder();
            String negout2 = negout.toString();
            boolean addflag = false;
            for (int i = negout2.length() - 1; i >= 0; i--) {
                if(addflag)comp2.append(negout2.charAt(i));

                else if(negout2.charAt(i) == '1') comp2.append('0');
                else{
                    comp2.append('1');
                    addflag = true;
                }
            }
            return comp2.reverse().toString();

        }
    }

    public String registerParser(String reg){
        reg = reg.trim();
        switch (reg.charAt(0)){
            case 'a':
                return binaryFormater(Character.getNumericValue(reg.charAt(1)) + 4, 2);
            case 'v':
                return binaryFormater(Character.getNumericValue(reg.charAt(1)) + 2, 2);
            case 's':
                if(reg.charAt(1) == 'p'){
                    return binaryFormater( 29, 2);
                }
                return binaryFormater(Character.getNumericValue(reg.charAt(1)) + 16, 2);
            case 't':
                if(Character.getNumericValue(reg.charAt(1)) <=7){
                    return binaryFormater(Character.getNumericValue(reg.charAt(1)) + 8, 2);
                }
                else{
                    return binaryFormater(Character.getNumericValue(reg.charAt(1)) + 16, 2);
                }
            case 'r':
                return binaryFormater( 31, 2);
            case 'z': case '0':
                return binaryFormater( 0, 2);
            default:
                return "RegParse Error";

        }
    }

    public Instruction instructionFactory(ArrayList<String> inst, Map<String, Integer> label, int pc){
        int jump;
        switch (inst.get(0)){
            case "and":
                return new RType(inst.get(0), inst.get(2), inst.get(3), inst.get(1), "0", "100100", this);
            case "or":
                return new RType(inst.get(0),inst.get(2), inst.get(3), inst.get(1), "0", "100101", this);
            case "add":
                return new RType(inst.get(0), inst.get(2), inst.get(3), inst.get(1), "0", "100000", this);
            case "addi":
                return new IType("addi", "001000", inst.get(2), inst.get(1), inst.get(3), this);
            case "sll":
                return new RType(inst.get(0), "0", inst.get(2), inst.get(1), inst.get(3), "000000", this);
            case "sub":
                return new RType(inst.get(0), inst.get(2), inst.get(3), inst.get(1), "0", "100010", this);
            case "slt":
                return new RType(inst.get(0), inst.get(2), inst.get(3), inst.get(1), "0", "101010", this);
            case "beq":
                jump = label.get(inst.get(3));
                if (pc > jump) jump = -1 *(pc - jump + 1);
                else jump = jump -pc -1;
                return new IType("beq","000100", inst.get(1), inst.get(2), Integer.toString(jump), this);
            case "bne":
                jump = label.get(inst.get(3));
                if (pc > jump) jump = -1 *(pc - jump + 1);
                else jump = jump -pc - 1;
                return new IType("bne","000101", inst.get(1), inst.get(2), Integer.toString(jump), this);
            case "lw":
                return new IType("lw", "100011", inst.get(1), inst.get(2), inst.get(3), this);
            case "sw":
                return new IType("sw", "101011", inst.get(1), inst.get(2), inst.get(3), this);
            case "j":
                return new JType(inst.get(0), "000010", Integer.toString(label.get(inst.get(1))), this);
            case "jr":
                return new RType(inst.get(0), inst.get(1), "0", "0", "0", "001000", this); //register hard coded to 0
            case "jal":
                return new JType(inst.get(0),"000011", Integer.toString(label.get(inst.get(1))), this);
            default:
                return new nullType(inst.get(0));
        }
    }

    public void memSet(){
        for(int i = 0; i < 8192; i++){
            this.memData.add(0);
        }
    }

    public void memReset(){
        for(int i = 0; i < 8192; i++){
            this.memData.set(i, 0);
        }
    }

    public void regSet(){
        this.regData.put("0", 0);
        this.regData.put("v0", 0);
        this.regData.put("v1", 0);
        this.regData.put("a0", 0);
        this.regData.put("a1", 0);
        this.regData.put("a2", 0);
        this.regData.put("a3", 0);
        this.regData.put("t0", 0);
        this.regData.put("t1", 0);
        this.regData.put("t2", 0);
        this.regData.put("t3", 0);
        this.regData.put("t4", 0);
        this.regData.put("t5", 0);
        this.regData.put("t6", 0);
        this.regData.put("t7", 0);
        this.regData.put("s0", 0);
        this.regData.put("s1", 0);
        this.regData.put("s2", 0);
        this.regData.put("s3", 0);
        this.regData.put("s4", 0);
        this.regData.put("s5", 0);
        this.regData.put("s6", 0);
        this.regData.put("s7", 0);
        this.regData.put("t8", 0);
        this.regData.put("t9", 0);
        this.regData.put("sp", 0);
        this.regData.put("ra", 0);

    }

    public void regReset(){
        regData.replaceAll((key, oldvalue) -> 0);
    }

    public void printReg(int pc){
        System.out.println("pc = " + pc);
        System.out.println("$0 = 0" + "\t\t$v0 = " + this.regData.get("v0") + "\t\t$v1 = " + this.regData.get("v1") + "\t\t$a0 = " + this.regData.get("a0"));
        System.out.println("$a1 = " + this.regData.get("a1") + "\t\t$a2 = " + this.regData.get("a2") + "\t\t$a3 = " + this.regData.get("a3") + "\t\t$t0 = " + this.regData.get("t0"));
        System.out.println("$t1 = " + this.regData.get("t1") + "\t\t$t2 = " + this.regData.get("t2") + "\t\t$t3 = " + this.regData.get("t3") + "\t\t$t4 = " + this.regData.get("t4"));
        System.out.println("$t5 = " + this.regData.get("t5") + "\t\t$t6 = " + this.regData.get("t6") + "\t\t$t7 = " + this.regData.get("t7") + "\t\t$s0 = " + this.regData.get("s0"));
        System.out.println("$s1 = " + this.regData.get("s1") + "\t\t$s2 = " + this.regData.get("s2") + "\t\t$s3 = " + this.regData.get("s3") + "\t\t$s4 = " + this.regData.get("s4"));
        System.out.println("$s5 = " + this.regData.get("s5") + "\t\t$s6 = " + this.regData.get("s6") + "\t\t$s7 = " + this.regData.get("s7") + "\t\t$t8 = " + this.regData.get("t8"));
        System.out.println("$t9 = " + this.regData.get("t9") + "\t\t$sp = " + this.regData.get("sp") + "\t\t$ra = " + this.regData.get("ra"));
    }

    public void printMem(int start, int end){
        for(int i = start; i <= end; i++){
            System.out.println("["+ i + "]" + " = " + this.memData.get(i));
        }
    }


}
