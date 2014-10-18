package de.lonzbonz.DevAthlon.main;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class chatAnimation {
	
	private main plugin;
	
	public chatAnimation(main plugin) {
		this.plugin = plugin;
	}
	
	//Ressources
	int i;
	
	public void showInChat(Player p, String message) {
		
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
	}
	
	public void showInChatWithDelay(final Player p, final List<String> message, int delayinSec) {
		i = 0;
		if(!plugin.run.containsKey(p.getName())) {
			plugin.run.put(p.getName(), new BukkitRunnable() {
				@Override
				public void run() {
					String msg = message.get(i);
					showInChat(p, msg);
					i++;
				}
			});
			plugin.run.get(p.getName()).runTaskTimer(plugin, delayinSec*20, delayinSec*20);
		}
	}

}
