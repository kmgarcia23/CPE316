/*
CPE-315 Lab2
Kevin Garcia and Peter Phillips
 */
public class nullType implements Instruction{
    private final String type;

    public nullType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "invalid instruction: " + type;
    }
}
