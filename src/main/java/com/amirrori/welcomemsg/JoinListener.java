package com.amirrori.welcomemsg;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import java.util.logging.Logger;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class JoinListener implements Listener {

    private final String message;
    
    private final Logger logger;

    public JoinListener(String message, Logger logger) {
        this.message = message;
        this.logger = logger;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        // player.sendMessage("§d §l§nwelcome to wyn's plugin testing server!");

        player.sendMessage(MiniMessage.miniMessage().deserialize(message, Placeholder.unparsed("player", player.getName())));
        logger.info("Greeted " + player.getName());
    }
}
