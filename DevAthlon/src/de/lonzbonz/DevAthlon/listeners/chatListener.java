package de.lonzbonz.DevAthlon.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.lonzbonz.DevAthlon.main.main;

public class chatListener implements Listener {

	private main plugin;
	
	public chatListener(main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		
	}
}
