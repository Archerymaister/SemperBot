package semperbot.command.commands.list;

import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import semperbot.SemperBot;
import semperbot.annotations.SubCommand;
import semperbot.command.ICommand;

@SubCommand("list")
@SubCommand("scaled")
public class listScaledCategoriesCommand implements ICommand {
    @Override
    public void onCall(MessageReceivedEvent event, String... arguments) {
        String msg = "Skalierende Kategorien:\n";

        for(Category c : SemperBot.observedCategories)
            msg += " - " + c.getName() + "\n";

        event.getChannel().sendMessage(msg).queue();
    }
}
