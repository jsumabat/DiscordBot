package Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Configuration {

    private static String token;
    private static String osuApiKey;
    private static String ownerId;

    public static void loadConfig() throws IOException {
        List<String> info = Files.readAllLines(Paths.get("config.txt"));
        token = info.get(0);
        osuApiKey = info.get(1);
        ownerId = info.get(2);
    }

    public static String getToken() {
        return token;
    }

    public static String getOsuApiKey() {
        return osuApiKey;
    }

    public static String getOwnerId() {
        return ownerId;
    }
}
