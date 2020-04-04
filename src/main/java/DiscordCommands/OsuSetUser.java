package DiscordCommands;

import Utils.DiscordOsu;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.oopsjpeg.osu4j.GameMode;
import com.oopsjpeg.osu4j.OsuUser;
import com.oopsjpeg.osu4j.backend.EndpointUsers;
import com.oopsjpeg.osu4j.backend.Osu;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class OsuSetUser extends Command {

    public OsuSetUser() {
        this.name = "osuset";
        this.help = "Link osu! profile to Discord account";
        this.arguments = "<username>";
        this.guildOnly = false;
    }

    @Override
    public void execute(CommandEvent event) {
        // check if they inputted a username
        if(event.getArgs().isEmpty()) {
            event.replyWarning("No osu! username was inputted!");
            return;
        }
        String discordId = event.getAuthor().getId();
        String[] arg = event.getArgs().split("\\s+");
        String osuUsername = "";

        // parse their osu username if it's more than one word
        if(arg.length == 1) {
            osuUsername = arg[0];
        } else {
            for(int i=0; i<arg.length; i++) {
                osuUsername += arg[i];
                if(i == arg.length-1) continue;
                osuUsername += ' ';
            }
        }

        List<String> info = null;
        try {
            info = Files.readAllLines(Paths.get("config.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // query for their osu! ID
        Osu osu = Osu.getAPI(info.get(1));
        OsuUser user = null;
        try {
            user = osu.users.query(new EndpointUsers.ArgumentsBuilder(osuUsername).setMode(GameMode.STANDARD).build());
        } catch (Exception e) {
            event.replyError("Could not find this osu! username.");
//            e.printStackTrace();
        }

        int osuId = user.getID();
        osuUsername = user.getUsername();

        DiscordOsu account = new DiscordOsu(discordId, osuId, osuUsername);

        try {
            DiscordOsu.update(discordId, osuId, osuUsername);
            event.replySuccess("Success!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
