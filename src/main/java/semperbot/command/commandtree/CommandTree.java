package semperbot.command.commandtree;

import semperbot.SemperBot;
import semperbot.command.ICommand;

import java.util.HashMap;
import java.util.Map;

public class CommandTree {
    private static HashMap<String, CommandTreeEntity> commands = new HashMap<>();

    public CommandTree(String baseCommand, String path, Class<? extends ICommand> clazz){
        if(!commands.containsKey(baseCommand))
            commands.put(baseCommand, new CommandTreeEntity(baseCommand));
        commands.get(baseCommand).addLeaf(path,clazz);

        System.out.println(path);
    }

    public CommandTree(String path, Class<? extends ICommand> clazz){
        this(SemperBot.getProperties().getProperty("command"),path,clazz);
    }

    public static Class<? extends ICommand> getClassFromPath(String baseCommand, String path) throws Exception {
        return commands.get(baseCommand).getLeaf(path).getClazz();
    }

    public static Class<? extends ICommand> getClassFromPath(String path) throws Exception {
        return getClassFromPath(SemperBot.getProperties().getProperty("command"), path);
    }

    public static void printTree(){
        for(Map.Entry<String, CommandTreeEntity> e : commands.entrySet()){
            System.out.println(e.getKey());
            e.getValue().printTree("  ");
        }
    }

    public static CommandTreeEntity getCommandTree(String baseCommand){
        return commands.get(baseCommand);
    }

    public static boolean isCommand(String command){

        return commands.containsKey(command.split(" ")[0]);
    }
}
