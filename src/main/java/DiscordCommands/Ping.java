package DiscordCommands;

import Utils.RateLimiter;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.time.temporal.ChronoUnit;

public class Ping extends Command {

    public Ping() {
        this.name = "ping";
        this.help = "checks the bot's latency";
        this.guildOnly = false;
    }

    @Override
    public void execute(CommandEvent event) {

        // command rate limit
        long uid = event.getAuthor().getIdLong();
        int rTime = RateLimiter.CheckUserRateLimit(uid, RateLimiter.RateLimitLevel.RATE_LIMIT_LEVEL_NORMAL);
        if(rTime > 0) {
            event.replyWarning("Please wait " + String.format("%.1f", rTime / 1000.0) + " second(s) before executing this command again!");
            return;
        }

        event.reply("Ping: ...", m -> {
            long ping = event.getMessage().getTimeCreated().until(m.getTimeCreated(), ChronoUnit.MILLIS);
            m.editMessage("Ping: " + ping  + "ms | Websocket: " + event.getJDA().getGatewayPing() + "ms").queue();
        });
    }
}
