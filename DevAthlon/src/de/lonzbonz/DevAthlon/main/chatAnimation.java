package de.lonzbonz.DevAthlon.main;

import java.util.List;

import org.bukkit.Bukkit;
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
		if(!plugin.chatRun.containsKey(p.getName())) {
			plugin.chatRun.put(p.getName(), new BukkitRunnable() {
				@Override
				public void run() {
					String msg = message.get(i);
					showInChat(p, msg);
					i++;
					
					if(i >= message.size()) {
						plugin.chatRun.get(p.getName()).cancel();
						plugin.chatRun.remove(p.getName());
						Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							@Override
							public void run() {
								showInChat(p, "");
							}
						}, 20);
					}
				}
			});
			plugin.chatRun.get(p.getName()).runTaskTimer(plugin, delayinSec*20, delayinSec*20);
		}
	}

}
