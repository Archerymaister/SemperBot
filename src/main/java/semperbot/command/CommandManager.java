package semperbot.command;

import semperbot.SemperBot;
import semperbot.annotations.BaseCommand;
import semperbot.annotations.SubCommand;
import semperbot.command.commands.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import semperbot.command.commandtree.CommandTree;
import semperbot.command.commandtree.CommandTreeEntity;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class CommandManager {
    private static HashMap<String,Class<? extends ICommand>> commands = new HashMap<>();

    public static void loadCommands(Class<SemperBot> semperBotClass) {
        for(BaseCommand b : semperBotClass.getAnnotationsByType(BaseCommand.class)){
            for(Class<? extends ICommand> clazz : b.commands()){
                registerCommand(b.value(), clazz);
            }
        }
    }

    public static void registerCommand(String baseCommand, Class<? extends ICommand> clazz){
        String path = "";
        for(SubCommand s : clazz.getAnnotationsByType(SubCommand.class))
            path += s.value() + ".";

        if(path.endsWith("."))
            path = path.substring(0,path.length()-1);

        new CommandTree(baseCommand, path, clazz);
    }

    public static void process(MessageReceivedEvent event) {
        if(!CommandTree.isCommand(event.getMessage().getContentRaw())) {
            return;
        }
        String[] command = event.getMessage().getContentRaw().split(" ");
        CommandTreeEntity curEnt = CommandTree.getCommandTree(command[0]);

        for(String s : command){
            System.out.println(s);
            if(s.startsWith("/"))
                continue;

            if(curEnt.isLeaf())
                break;

            curEnt = curEnt.getChild(s);
        }

        try {
            curEnt.getClazz().getConstructor().newInstance().getClass().getDeclaredMethod("onCall", MessageReceivedEvent.class, String[].class).invoke(curEnt.getClazz().getConstructor().newInstance(),event, new String[]{});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
