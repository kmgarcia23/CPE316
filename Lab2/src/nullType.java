public class nullType implements Instruction{
    private String type;

    public nullType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "invalid instruction: " + type;
    }
}
