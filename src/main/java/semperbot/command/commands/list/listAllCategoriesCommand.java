package semperbot.command.commands.list;

import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import semperbot.SemperBot;
import semperbot.annotations.SubCommand;
import semperbot.command.ICommand;

@SubCommand("list")
@SubCommand("all")
public class listAllCategoriesCommand implements ICommand {
    @Override
    public void onCall(MessageReceivedEvent event, String... arguments) {
        String msg = "Available categories:\n";

        for(Category cat : SemperBot.builder.getCategories())
            msg += " - " + cat.getName() + "\n";

        event.getChannel().sendMessage(msg).queue();
    }
}
