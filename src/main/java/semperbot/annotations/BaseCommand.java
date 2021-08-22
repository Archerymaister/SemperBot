package semperbot.annotations;

import semperbot.SemperBot;
import semperbot.command.ICommand;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(BaseCommands.class)
public @interface BaseCommand {
    String value();
    Class<? extends ICommand>[] commands();
}
