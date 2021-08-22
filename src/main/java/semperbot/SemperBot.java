package semperbot;

import semperbot.annotations.BaseCommand;
import semperbot.command.CommandManager;
import semperbot.command.commands.*;
import semperbot.command.commands.scaling.addScalingCommand;
import semperbot.listeners.Listeners;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.utils.Compression;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import static net.dv8tion.jda.api.interactions.commands.OptionType.STRING;

@BaseCommand(value = "/semper", commands = {
      helpCommand.class,
        listAllCategoriesCommand.class,
        listScaledCategoriesCommand.class,
        sayCommand.class,
        addScalingCommand.class
})
public class SemperBot {
    private static Properties properties = new Properties();
    public static ArrayList<Category> observedCategories = new ArrayList<>();
    public static JDA builder;
    public static void main(String[] args) throws LoginException, InterruptedException {
        if(args.length != 1){
            System.err.println("Path to properties file with token required!");
            System.exit(-1);
        }
        loadProperties(new File(args[0]));
        builder = JDABuilder.createDefault(properties.getProperty("token"))
        //.disableCache(CacheFlag.VOICE_STATE) // Disable parts of the cache
        .setBulkDeleteSplittingEnabled(false) // Enable the bulk delete event
        .setCompression(Compression.NONE) // Disable compression (not recommended)
        .setActivity(Activity.playing("New World"))// Set activity (like "playing Something")
        .addEventListeners(new Listeners())
        .build();

        builder.awaitReady();

        observedCategories.add(builder.getCategoryById(878228543329435678L));
        observedCategories.add(builder.getCategoryById(878598620696416287L));

        CommandManager.loadCommands(SemperBot.class);

        CommandListUpdateAction commands = builder.updateCommands();

        commands.addCommands(new CommandData("say", "Makes the bot say what you tell it to")
                .addOptions(new OptionData(STRING, "content", "What the bot should say").setRequired(true)));

        commands.addCommands(new CommandData("semperbot", "Access to all the bot features")
                .addOptions(new OptionData(STRING, "say", "replay the given string")));

        commands.queue();
    }



    private static void loadProperties(File file){
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static Properties getProperties(){
        return properties;
    }
}
