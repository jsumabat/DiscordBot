package Main;

import Listeners.Listener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) throws LoginException {
        try {
            String token = "";
            JDA bot = JDABuilder.createDefault(token).addEventListeners(new Listener()).build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
