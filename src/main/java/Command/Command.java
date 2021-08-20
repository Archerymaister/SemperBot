package Command;

public class Command {
    public static void received(net.dv8tion.jda.api.interactions.commands.Command command){
        System.out.println(command.toString());
    }
}
