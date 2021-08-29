package semperbot.command.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import semperbot.annotations.SubCommand;
import semperbot.command.ICommand;
import semperbot.command.commandtree.CommandTree;
import semperbot.command.commandtree.CommandTreeEntity;

import java.util.Map;

@SubCommand("help")
public class helpCommand implements ICommand {

    @Override
    public void onCall(MessageReceivedEvent event, String... arguments) {
        String msg = "Verfügbare Commands:\n";

        msg += " - help\n";
        msg += " - say [Nachricht]\n";
        msg += " - listcategories all\n";
        msg += " - listcategories scaled\n";

        event.getChannel().sendMessage(msg).queue();
        CommandTree.printTree();
    }

    public void showHelpForEntity(CommandTreeEntity entity){
        String msg = "Verfügbare Commands:\n";

        for(Map.Entry e : entity.getNodes().entrySet()){

        }
    }
}
