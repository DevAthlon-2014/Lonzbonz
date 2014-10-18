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
		
		if(plugin.chatRun.containsKey(p.getName())) {
			plugin.chatRun.get(p.getName()).cancel();
			plugin.chatRun.remove(p.getName());
		}
	}

}
