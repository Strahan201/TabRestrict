package com.sylvcraft.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.sylvcraft.TabRestrict;

public class Tabrst implements CommandExecutor {
	TabRestrict plugin;
	
	public Tabrst(TabRestrict instance) {
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Map<String, String> data = new HashMap<String, String>();
		List<String> blocklist = plugin.getConfig().getStringList("blocklist");
		
		if (args.length == 0) {
			plugin.msg("list-header", sender);
			for (String command : blocklist) {
				data.put("%command%", command);
				plugin.msg("list-data", sender, data);
			}
			return true;
		}
		
		data.put("%command%", args[0].toLowerCase());
		if (blocklist.contains(args[0].toLowerCase())) {
			blocklist.remove(args[0].toLowerCase());
			plugin.msg("removed", sender, data);
		} else {
			blocklist.add(args[0].toLowerCase());
			plugin.msg("added", sender, data);
		}
		plugin.getConfig().set("blocklist", blocklist);
		plugin.saveConfig();
		return true;
	}
}
