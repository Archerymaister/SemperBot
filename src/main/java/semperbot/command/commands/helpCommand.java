package semperbot.command.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import semperbot.command.ICommand;

public class helpCommand implements ICommand {

    @Override
    public void onCall(MessageReceivedEvent event, String... arguments) {
        String msg = "Verfügbare Commands:\n";

        msg += " - help\n";
        msg += " - say [Nachricht]\n";
        msg += " - listcategories all\n";
        msg += " - listcategories scaled\n";

        event.getChannel().sendMessage(msg).queue();
    }
}
