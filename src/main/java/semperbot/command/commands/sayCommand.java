package semperbot.command.commands;

import semperbot.command.ICommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class sayCommand implements ICommand {
    @Override
    public void onCall(MessageReceivedEvent event, String... arguments) {
        event.getChannel().sendMessage(arguments[0]).queue();
    }
}
