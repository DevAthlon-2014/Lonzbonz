package de.lonzbonz.DevAthlon.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.lonzbonz.DevAthlon.main.GameState;
import de.lonzbonz.DevAthlon.main.main;
import de.lonzbonz.DevAthlon.main.randomGetter;

public class playerQuit implements Listener {

	/**
	 * @author Lonzbonz
	 * @date 18.10.2014
	 */
	
	private main plugin;
	
	public playerQuit(main plugin) {
		this.plugin = plugin;
	}
	
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		
		randomGetter rG = new randomGetter();
		e.setQuitMessage(rG.setStringToRandomColor(p.getName()) + "§r §ehat den Server verlassen");
		
		if(plugin.chatRun.containsKey(p.getName())) {
			plugin.chatRun.get(p.getName()).cancel();
			plugin.chatRun.remove(p.getName());
		}
		
		if(Bukkit.getOnlinePlayers().length == 1 | Bukkit.getOnlinePlayers().length == 2 | Bukkit.getOnlinePlayers().length == 0) {
			Bukkit.broadcastMessage(plugin.prefix + "§c§lAlle Spieler haben den Server verlassen! Starte neu!");
			plugin.state = GameState.RESTARTING;
			Bukkit.getScheduler().cancelAllTasks();
			for(Ghast g : plugin.ghasts) {
				g.remove();
			}
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				@Override
				public void run() {
					Bukkit.shutdown();
				}
			}, 20);
		}
		
		
	}

}
