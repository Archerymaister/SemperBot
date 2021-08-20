import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SemperBot extends ListenerAdapter {
    private static Properties properties = new Properties();
    public static void main(String[] args) throws LoginException {
        if(args.length != 1){
            System.err.println("Path to properties file with token required!");
            System.exit(-1);
        }
        loadProperties(new File(args[0]));
        JDABuilder builder = JDABuilder.createDefault(properties.getProperty("token"));

        // Disable parts of the cache
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        // Enable the bulk delete event
        builder.setBulkDeleteSplittingEnabled(false);
        // Disable compression (not recommended)
        builder.setCompression(Compression.NONE);
        // Set activity (like "playing Something")
        builder.setActivity(Activity.playing("New World"));
        builder.addEventListeners(new SemperBot());

        builder.build();
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event){
        System.out.println(event.toString());
        //Command.Command.received(event);
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
}
