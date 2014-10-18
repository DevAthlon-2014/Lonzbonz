package de.lonzbonz.DevAthlon.main;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class chatAnimation {

	/**
	 * @author Lonzbonz
	 * @date 18.10.2014
	 */
	
	private main plugin;
	
	public chatAnimation(main plugin) {
		this.plugin = plugin;
	}
	
	//Ressources
	int i;
	
	/**
	 * 
	 * @param p - the player who should be display to
	 * @param message - the message to display
	 */
	public void display(Player p, String message) {
		
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
		
		p.playSound(p.getLocation(), Sound.BURP, 3, 3);
		p.getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 3);
	}
	
	/**
	 * 
	 * @param p - the player who should be display to
	 * @param message - the message to display
	 * @param delayinSec - the delay between the messages
	 */
	public void displayWithDelay(final Player p, final List<String> message, int delayinSec) {
		i = 0;
		if(!plugin.chatRun.containsKey(p.getName())) {
			plugin.chatRun.put(p.getName(), new BukkitRunnable() {
				@Override
				public void run() {
					String msg = message.get(i);
					display(p, msg);
					i++;
					
					if(i >= message.size()) {
						plugin.chatRun.get(p.getName()).cancel();
						plugin.chatRun.remove(p.getName());
						Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							@Override
							public void run() {
								display(p, "");
							}
						}, 20);
					}
				}
			});
			plugin.chatRun.get(p.getName()).runTaskTimer(plugin, delayinSec*20, delayinSec*20);
		}
	}

}
