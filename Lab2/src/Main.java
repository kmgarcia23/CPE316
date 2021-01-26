import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

public class Main {
    private static final String[] valid_cmds = new String[] {"and", "or", "add", "addi", "sll", "sub", "slt", "beq", "bne", "lw", "sw", "j", "jr", "jal"};

    public static String cleanCMD(String[] cmd_array)
    {
        if(cmd_array.length == 0)
        {
            return null;
        }
        String cmd = cmd_array[0];
        if (cmd.contains("#"))
        {
            return null;
        }
        else if (cmd.length() > 0)
        {
            return cmd;
        }
        return null;
    }

    public static void getLabels(String filename, Map<String, Integer> labels)
            throws FileNotFoundException
    {
        int line_count = 1;
        try(Scanner in = new Scanner(new File(filename)))
        {
            while(in.hasNextLine())
            {
                String input = in.nextLine();
                String[] cmd_array = input.split("\\s+");
                String cmd = cleanCMD(cmd_array);
                if(cmd != null) {
                    if (!Arrays.asList(valid_cmds).contains(cmd) && cmd.contains(":"))
                    {
                        int index = cmd.indexOf(":");
                        cmd = cmd.substring(0, index);
                        labels.put(cmd, line_count);
                    }
                }
                line_count+=1;
            }
        }
    }

    public static void getCommandS(String filename, List<List<String>> cmds)
            throws FileNotFoundException
    {
        try(Scanner in = new Scanner(new File(filename)))
        {
            while(in.hasNextLine())
            {
                String input = in.nextLine();
                String[] cmd_array = input.split("\\s+");
                String cmd = cleanCMD(cmd_array);
                if(cmd != null)
                {
                    if (!Arrays.asList(valid_cmds).contains(cmd))
                    {
                        int index = cmd.indexOf(":");
                        cmd = cmd.substring(0, index);
                    }
                }
            }
        }
    }

    public static void main(String[] args)
    {
        Util util = new Util();
        Map <String, Integer> labels = new HashMap<>();
        List<List<String>> cmds = new ArrayList<>();
//        System.out.println(util.binaryFormater(10, 1));
        System.out.println(util.registerParser("$t4"));
        try
        {
            getLabels(args[0], labels);
            //getCommandS(args[0], cmds);
        }
        catch (FileNotFoundException e)
        {
            System.err.println(e.getMessage());
        }
    }
}

