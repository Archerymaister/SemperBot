package semperbot.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface ICommand {
    public void onCall(MessageReceivedEvent event, String... arguments);
}
