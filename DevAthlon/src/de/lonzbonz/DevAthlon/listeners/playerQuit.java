package de.lonzbonz.DevAthlon.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.lonzbonz.DevAthlon.main.main;

public class playerQuit implements Listener {
	
	private main plugin;
	
	public playerQuit(main plugin) {
		this.plugin = plugin;
	}
	
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		
		if(plugin.joinRun.containsKey(p.getName())) {
			plugin.joinRun.get(p.getName()).cancel();
			plugin.joinRun.remove(p.getName());
		}
		
		if(plugin.run.containsKey(p.getName())) {
			plugin.run.get(p.getName()).cancel();
			plugin.run.remove(p.getName());
		}
	}

}
