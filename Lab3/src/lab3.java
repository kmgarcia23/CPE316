import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;
/*
CPE-315 Lab3
Kevin Garcia and Peter Phillips
 */

public class lab3 {
    private static final String[] valid_cmds = new String[] {"and", "or", "add", "addi", "sll", "sub", "slt", "beq", "bne", "lw", "sw", "j", "jr", "jal"};

    public static String cleanLabel(String[] cmd_array)
    {
        if(cmd_array.length == 0)
        {
            return null;
        }
        String label = cmd_array[0];
        if (label.contains("#"))
        {
            return null;
        }
        else if (label.length() > 0)
        {
            return label;
        }
        return "";
    }

    public static void getLabels(String filename, Map<String, Integer> labels)
            throws FileNotFoundException
    {
        int line_count = 0;
        try(Scanner in = new Scanner(new File(filename)))
        {
            while(in.hasNextLine())
            {
                String input = in.nextLine();
                if(input.isEmpty())continue;
                String[] cmd_array = input.split("\\s+");
                String label = cleanLabel(cmd_array);
                if(label != null) {
                    if (!Arrays.asList(valid_cmds).contains(label) && label.contains(":"))
                    {
                        int index = label.indexOf(":");
                        label = label.substring(0, index);
                        labels.put(label, line_count);
                    }
                    line_count+=1;
                }
            }
        }
    }

    public static ArrayList<String> cleanCommdands(String cmds)
    {
        if(cmds.length() == 0) //Takes care of empty lines after I stripped white space
        {
            return null;
        }
        if (cmds.contains("#")) //Check if line has a comment
        {
            int index = cmds.indexOf("#"); //Get index where comment starts
            if(index == 0) //This means the entire line is a comment & it can be ignored
            {
                return null;
            }
            else
            {
                cmds = cmds.substring(0,index); //Keeps everything up to the comment
            }
        }
        List<String> cmd_array = new LinkedList<>(Arrays.asList(cmds.split("[\\s+$,()]")));
        ArrayList<String> res = new ArrayList<>();
        for(int i = 0; i < cmd_array.size(); i++)
        {
            if(!cmd_array.get(i).equals(""))
            {
                res.add(cmd_array.get(i));
            }
        }
        if(res.isEmpty())
        {
            return null;
        }
        if(res.get(0).contains(":"))
        {
            int index = res.get(0).indexOf(":");
            if(index == (res.get(0)).length()-1)
            {
                res.remove(0);
            }
            else
            {
                res.set(0, res.get(0).substring(index+1, res.get(0).length()));
            }
        }
        return res;
    }

    public static void getCommands(String filename, ArrayList<ArrayList<String>> commands)
            throws FileNotFoundException
    {
        try(Scanner in = new Scanner(new File(filename)))
        {
            while(in.hasNextLine())
            {
                String input = in.nextLine();
                ArrayList<String> cmd_array = cleanCommdands(input);
                if(cmd_array != null && !cmd_array.isEmpty())
                {
                    commands.add(cmd_array);
                }
            }
        }
    }

    public static void main(String[] args)
    {
        Util util = new Util();
        Map <String, Integer> labels = new HashMap<>();
        ArrayList<ArrayList<String>> commands = new ArrayList<>();
//        System.out.println(util.binaryFormater(10, 1));
//        System.out.println(util.registerParser("$t4"));
        try
        {
            getLabels(args[0], labels);
            getCommands(args[0], commands);
//            System.out.println(labels.toString());
//            for (List<String>cmd_array:commands)
//            {
//                System.out.println(cmd_array.toString());
//            }
        }
        catch (FileNotFoundException e)
        {
            System.err.println(e.getMessage());
        }
        int i = 0;
        ArrayList<Instruction> insts = new ArrayList<>();
        for(ArrayList<String> command : commands){
           insts.add(util.instructionFactory(command, labels, i));
           i++;
        }
        for(Instruction in : insts){
            if( in instanceof nullType){
                System.out.println(in);
                System.exit(1);
            }
            System.out.println(in);
        }
    }
}