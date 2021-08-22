package semperbot.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PACKAGE)
@Repeatable(SubCommands.class)
public @interface SubCommand {
    String value();
}
