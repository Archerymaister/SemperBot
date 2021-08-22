package semperbot.command.commands;

import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import semperbot.SemperBot;
import semperbot.command.ICommand;

public class listAllCategoriesCommand implements ICommand {
    @Override
    public void onCall(MessageReceivedEvent event, String... arguments) {
        String msg = "Available categories:\n";

        for(Category cat : SemperBot.builder.getCategories())
            msg += " - " + cat.getName() + "\n";

        event.getChannel().sendMessage(msg).queue();
    }
}
