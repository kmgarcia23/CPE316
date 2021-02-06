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
                return new RType(inst.get(2), inst.get(3), inst.get(1), "0", "100100");
            case "or":
                return new RType(inst.get(2), inst.get(3), inst.get(1), "0", "100101");
            case "add":
                return new RType(inst.get(2), inst.get(3), inst.get(1), "0", "100000");
            case "addi":
                return new IType("addi", "001000", inst.get(2), inst.get(1), inst.get(3));
            case "sll":
                return new RType("0", inst.get(2), inst.get(1), inst.get(3), "000000");
            case "sub":
                return new RType(inst.get(2), inst.get(3), inst.get(1), "0", "100010");
            case "slt":
                return new RType(inst.get(2), inst.get(3), inst.get(1), "0", "101010");
            case "beq":
                jump = label.get(inst.get(3));
                if (pc > jump) jump = -1 *(pc - jump + 1);
                else jump = jump -pc -1;
                return new IType("beq","000100", inst.get(1), inst.get(2), Integer.toString(jump));
            case "bne":
                jump = label.get(inst.get(3));
                if (pc > jump) jump = -1 *(pc - jump + 1);
                else jump = jump -pc - 1;
                return new IType("bne","000101", inst.get(1), inst.get(2), Integer.toString(jump));
            case "lw":
                return new IType("lw", "100011", inst.get(1), inst.get(2), inst.get(3));
            case "sw":
                return new IType("sw", "101011", inst.get(1), inst.get(2), inst.get(3));
            case "j":
                return new JType("000010", Integer.toString(label.get(inst.get(1))));
            case "jr":
                return new RType(inst.get(1), "0", "0", "0", "001000"); //register hard coded to 0
            case "jal":
                return new JType("000011", Integer.toString(label.get(inst.get(1))));
            default:
                return new nullType(inst.get(0));
        }
    }
}
