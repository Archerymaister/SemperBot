package semperbot.command.commands.scaling;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import semperbot.annotations.SubCommand;
import semperbot.command.ICommand;

@SubCommand("scaling")
@SubCommand("add")
public class addScalingCommand implements ICommand {
    @Override
    public void onCall(MessageReceivedEvent event, String... arguments) {

    }
}
