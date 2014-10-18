package de.lonzbonz.DevAthlon.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.lonzbonz.DevAthlon.main.main;
import de.lonzbonz.DevAthlon.main.randomGetter;

public class playerQuit implements Listener {
	
	private main plugin;
	
	public playerQuit(main plugin) {
		this.plugin = plugin;
	}
	
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		
		randomGetter rG = new randomGetter();
		e.setQuitMessage(rG.setStringToRandomColor(p.getName()) + "§r §ehat den Server verlassen");
		
		if(plugin.joinRun.containsKey(p.getName())) {
			plugin.joinRun.get(p.getName()).cancel();
			plugin.joinRun.remove(p.getName());
		}
		
		if(plugin.chatRun.containsKey(p.getName())) {
			plugin.chatRun.get(p.getName()).cancel();
			plugin.chatRun.remove(p.getName());
		}
		
		if(Bukkit.getOnlinePlayers().length == 0) {
			Bukkit.broadcastMessage(plugin.prefix + "§c§lAlle Spieler haben den Server verlassen! Starte neu!");
			Bukkit.getScheduler().cancelAllTasks();
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				@Override
				public void run() {
					Bukkit.shutdown();
				}
			}, 20);
		}
		
		if(Bukkit.getOnlinePlayers().length == 1 | Bukkit.getOnlinePlayers().length == 2) {
			Bukkit.broadcastMessage(plugin.prefix + "§c§lAlle Spieler haben den Server verlassen! Starte neu!");
			Bukkit.getScheduler().cancelAllTasks();
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				@Override
				public void run() {
					Bukkit.shutdown();
				}
			}, 20);
		}
		
		
	}

}
