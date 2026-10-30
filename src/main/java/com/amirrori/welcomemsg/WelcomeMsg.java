package com.amirrori.welcomemsg;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class WelcomeMsg extends JavaPlugin {

    @Override
    public void onEnable() {
        // scanner reads message
        String message = readWelcomeMessage();
        getLogger().info("To setup plugin please edit config file or generate configuration.");

        Bukkit.getPluginManager().registerEvents(new JoinListener(message, getLogger()),this); // we hand the message to the constructor(hand it the constructor)
    }

    private String readWelcomeMessage() {
        // extracts the default config from the JAR file to the server's directory.
        saveDefaultConfig();
        File file = new File(getDataFolder(), "config.yml");
        try (Scanner scanner = new Scanner(file, StandardCharsets.UTF_8)) {
            // checking if next line exists
            while (scanner.hasNextLine()) {
                // We remove all the whitespace from the string(start and end).
                String line = scanner.nextLine().trim();

                if (line.startsWith("welcome-message:")) {
                    String value = line.substring("welcome-message:".length()).trim();

                    // were asking if the length is more than 2 and if it starts with and ends with "" because that's what were looking for (THERE HAS TO BE A BETTER WAY SOMEONE FIX THIS)
                    if (value.length() >= 2 && value.startsWith("\"") && value.endsWith("\"")) {
                        // we get the substring from 1(after first " and length - 1 which is before ")
                        value = value.substring(1, value.length() - 1);
                    }
                    return value;
                }
            }
        // error logging for when it doesn't find a file
        } catch (IOException e) {
            getLogger().warning("Could not read config.yml: " + e.getMessage());
        }
        return "Welcome, <player>!";
    }
}
