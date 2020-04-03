import DiscordCommands.OsuSetUser;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, LoginException {

        List<String> info = Files.readAllLines(Paths.get("config.txt"));

        String token = info.get(0);
        String osuApiKey = info.get(1);
        String ownerId = info.get(2);

        EventWaiter waiter = new EventWaiter();

        CommandClientBuilder client = new CommandClientBuilder();

        client.useDefaultGame();

        client.setOwnerId(ownerId);

        client.setPrefix("!");

        client.addCommands(
                new OsuSetUser()
        );

        JDA bot = JDABuilder.createDefault(token).addEventListeners(waiter, client.build()).build();
    }
}
