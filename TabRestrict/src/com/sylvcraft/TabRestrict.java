package com.sylvcraft;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.sylvcraft.commands.Tabrst;
import com.sylvcraft.events.PlayerCommandSend;
import java.util.Map;

public class TabRestrict extends JavaPlugin implements Listener {
	
  @Override
  public void onEnable() {
  	PluginManager pm = getServer().getPluginManager();
  	pm.registerEvents(new PlayerCommandSend(this), this);
  	getCommand("tabrst").setExecutor(new Tabrst(this));
  	saveDefaultConfig();
	}
  
  public void msg(String msgCode, CommandSender sender) {
  	String tmp = getConfig().getString("messages." + msgCode, msgCode) + " ";
  	if (tmp.trim().equals("")) return;
  	for (String m : tmp.split("%br%")) {
  		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', m));
  	}
  }

  public void msg(String msgCode, CommandSender sender, Map<String, String> data) {
  	String tmp = getConfig().getString("messages." + msgCode, msgCode);
  	if (tmp.trim().equals("")) return;
  	for (Map.Entry<String, String> mapData : data.entrySet()) {
  	  tmp = tmp.replace(mapData.getKey(), mapData.getValue());
  	}
  	msg(tmp, sender);
  }
}