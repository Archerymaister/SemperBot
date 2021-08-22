package semperbot.listeners;

import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import semperbot.SemperBot;
import semperbot.command.CommandManager;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import semperbot.manager.ChannelManager;

import javax.annotation.Nonnull;

public class Listeners extends ListenerAdapter {
        @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        CommandManager.process(event);
    }

    @Override
    public void onGuildVoiceJoin(@Nonnull GuildVoiceJoinEvent event) {
        ChannelManager.testCategory(event.getChannelJoined());
    }

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event){
        ChannelManager.testCategory(event.getChannelLeft());
    }

    @Override
    public void onGuildVoiceMove(@Nonnull GuildVoiceMoveEvent event) {
        if (event.getChannelJoined().getIdLong() != event.getChannelLeft().getIdLong())
            ChannelManager.testCategory(event.getChannelJoined());
        ChannelManager.testCategory(event.getChannelLeft());
    }

}
