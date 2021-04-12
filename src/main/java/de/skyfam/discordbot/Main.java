package main.java.de.skyfam.discordbot;

import main.java.de.skyfam.discordbot.listener.L_JoinQuit;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static Main instance;


    public ShardManager shardManager;
    public Thread loopThread;

    public JDA jda;
    public DefaultShardManagerBuilder shardManagerBuilder = new DefaultShardManagerBuilder();

    public static void main(String[] args) {

        try {
            new Main();
        } catch(LoginException | IllegalArgumentException exception) {
            exception.printStackTrace();
        }

    }

    public Main() throws LoginException, IllegalArgumentException {
        instance = this;

        shardManagerBuilder.setToken("Nzc0MzM4MDk5ODE5NDQ2Mjcz.X6WUjQ._or2lQ9deWQH-rbgj0TofMohZ2M");
        shardManagerBuilder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        shardManagerBuilder.setActivity(Activity.playing("with the Maintenance"));
        shardManagerBuilder.setAutoReconnect(true);


        shardManagerBuilder.addEventListeners(new L_JoinQuit());


        shardManager = shardManagerBuilder.build();

        shutdown();
    }

    void shutdown() {

        new Thread(() -> {

            String line = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try {
                while((line = reader.readLine()) != null) {

                    if(line.equalsIgnoreCase("shutdown")) {
                        if(shardManager != null) {
                            shardManager.setStatus(OnlineStatus.OFFLINE);
                            shardManager.shutdown();
                            System.out.println("SkyFam Bot gestoppt...");
                        }

                        reader.close();
                    } else {
                        System.out.println("Nutze [shutdown] um den Bot herunterzufahren!");
                    }

                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }

        }).start();

    }



    public static Main getInstance() {
        return instance;
    }
}
