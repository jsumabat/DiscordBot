package Utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DiscordOsu {

    private String discordId;
    private String osuUsername;
    private int osuId;
    public static HashMap<String, Pair<Integer, String>> accounts = new HashMap<>();

    public DiscordOsu(String discordId, int osuId, String osuUsername) {
        this.discordId = discordId;
        this.osuId = osuId;
        this.osuUsername = osuUsername;
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

    public static boolean exists(String discordId) {
        return accounts.containsKey(discordId);
    }

    public static void update(String discordId, int osuId, String osuUsername) {
        accounts.put(discordId, new Pair<>(osuId, osuUsername));
    }

    public static void load() {
        File file = new File("accounts.json");
        if(file.length() == 0) {
            System.out.println(ColorConstants.ANSI_BLUE + "No accounts are stored." + ColorConstants.ANSI_RESET);
            return;
        }
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("accounts.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray accountsList = (JSONArray) obj;
            for(int i=0; i<accountsList.size(); i++) {
                JSONObject account = (JSONObject) accountsList.get(i);
                update((String)account.get("DiscordId"), Math.toIntExact((Long) account.get("OsuId")), (String)account.get("OsuUsername"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
        for(Map.Entry<String, Pair<Integer, String>> x : DiscordOsu.accounts.entrySet()) {
            JSONObject obj = new JSONObject();
            obj.put("DiscordId", x.getKey());
            obj.put("OsuId", x.getValue().getFirst());
            obj.put("OsuUsername", x.getValue().getSecond());
            arr.add(obj);
        }

        JSONArray accountList = new JSONArray();
        for(JSONObject x : arr) {
            accountList.add(x);
        }

        try(FileWriter file = new FileWriter("accounts.json")) {
            file.write(accountList.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return this.discordId + "\n" + this.osuUsername + "\n" + this.osuId + "\n";
    }
}
