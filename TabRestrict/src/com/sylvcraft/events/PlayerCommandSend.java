package com.sylvcraft.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

import com.sylvcraft.TabRestrict;

public class PlayerCommandSend implements Listener {
	TabRestrict plugin;
	
	public PlayerCommandSend(TabRestrict instance) {
		plugin = instance;
	}

	@EventHandler
	public void onTC(PlayerCommandSendEvent e) {
		for (String cmd : plugin.getConfig().getStringList("blocklist")) {
			if (!e.getCommands().contains(cmd)) continue;
			e.getCommands().remove(cmd);
		}
	}
}
