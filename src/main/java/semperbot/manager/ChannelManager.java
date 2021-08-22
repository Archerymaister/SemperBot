package semperbot.manager;

import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.exceptions.ContextException;
import semperbot.SemperBot;

import java.util.ArrayList;

public class ChannelManager {

    public static void testCategory(VoiceChannel voiceChannel){
        Category category = getCategoryFromChannel(voiceChannel);

        if(SemperBot.observedCategories.contains(category))
            switch (checkCategory(category)){
                case 1:
                    category.getGuild().createVoiceChannel("test " + (category.getVoiceChannels().size()+1))
                            .setParent(category).queue();
                    break;
                case 2:
                    deleteChannels(category);
                    break;
            }

    }

    private static void deleteChannels(Category category){
        int channelCount = category.getVoiceChannels().size();
        boolean emptyChannel = false;
        ArrayList<Long> channelsToBePurged = new ArrayList<>();

        //Test if empty channels are
        for(VoiceChannel voiceChannel : category.getVoiceChannels()){
            if(voiceChannel.getMembers().size() == 0 && !emptyChannel){
                emptyChannel = true;
                continue;
            }

            if(voiceChannel.getMembers().size() == 0 && emptyChannel)
                channelsToBePurged.add(voiceChannel.getIdLong());


            if(voiceChannel.getMembers().size() != 0){
                emptyChannel = false;
                channelsToBePurged.clear();
            }
        }

        for(long id : channelsToBePurged){

            try{
                category.getGuild().getVoiceChannelById(id).delete().reason("Unused").queue();
            }catch (Exception e){}
        }
    }

    /**
     *
     * @param category
     * @return 0 = nothing, 1 = add, 2 = delete
     */
    public static int checkCategory(Category category){
        int channelCount = category.getVoiceChannels().size();
        int filledChannels = 0;

        for(VoiceChannel voiceChannel : category.getVoiceChannels())
            if(voiceChannel.getMembers().size() != 0)
                filledChannels++;

        if(channelCount == filledChannels)
            return 1;

        if((channelCount - filledChannels) == 1)
            return 0;

        return 2;
    }

    public static Category getCategoryFromChannel(VoiceChannel voiceChannel){
        for(Category cat : SemperBot.builder.getCategories())
            for(VoiceChannel channel : cat.getVoiceChannels())
                if(channel.getIdLong() == voiceChannel.getIdLong())
                    return cat;
        return null;
    }
}
