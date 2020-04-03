package Utils;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"discordId", "osuId", "osuUsername"})
public class DiscordOsu {

    private String discordId;
    private String osuUsername;
    private int osuId;

    public DiscordOsu(String discordId, String osuUsername, int osuId) {
        this.discordId = discordId;
        this.osuUsername = osuUsername;
        this.osuId = osuId;
    }

    public String getDiscordId() {
        return this.discordId;
    }

    public String getOsuUsername() {
        return this.osuUsername;
    }

    public int getOsuId() {
        return this.osuId;
    }

    public String toString() {
        return this.discordId + "\n" + this.osuUsername + "\n" + this.osuId + "\n";
    }
}
