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
        String str = decimalToBinary(num);
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
        return out.toString() + str;
    }

    public String registerParser(String reg){
        reg = reg.trim();
        switch (reg.charAt(1)){
            case 'a':
                return binaryFormater(Character.getNumericValue(reg.charAt(2)) + 4, 2);
            case 'v':
                return binaryFormater(Character.getNumericValue(reg.charAt(2)) + 2, 2);
            case 's':
                if(reg.charAt(2) == 'p'){
                    return binaryFormater( 29, 2);
                }
                return binaryFormater(Character.getNumericValue(reg.charAt(2)) + 16, 2);
            case 't':
                if(Character.getNumericValue(reg.charAt(2)) <=7){
                    return binaryFormater(Character.getNumericValue(reg.charAt(2)) + 8, 2);
                }
                else{
                    return binaryFormater(Character.getNumericValue(reg.charAt(2)) + 16, 2);
                }
            case 'r':
                return binaryFormater( 31, 2);
            case 'z': case '0':
                return binaryFormater( 0, 2);
            default:
                return "RegParse Error";

        }
    }
}
