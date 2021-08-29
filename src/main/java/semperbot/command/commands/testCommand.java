package semperbot.command.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import semperbot.annotations.SubCommand;
import semperbot.command.CommandManager;
import semperbot.command.ICommand;
import semperbot.command.commandtree.CommandTree;

@SubCommand("test")
public class testCommand implements ICommand {
    @Override
    public void onCall(MessageReceivedEvent event, String... arguments) {
        event.getChannel().sendMessage("msg").queue();
        CommandTree.printTree();
    }
}
