package de.lonzbonz.DevAthlon.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class chatAnimation {
	
	private main plugin;
	
	public chatAnimation(main plugin) {
		this.plugin = plugin;
	}
	
	public void showInChat(Player p, String message) {
		
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage(message);
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
	}
	
	public void showInChatWithDelay(Player p, String message, int delayinSec) {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				
			}
		}, delayinSec*20, delayinSec*20);
	}

}
