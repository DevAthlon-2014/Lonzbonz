package de.lonzbonz.DevAthlon.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.lonzbonz.DevAthlon.main.main;
import de.lonzbonz.DevAthlon.main.randomGetter;

public class chatListener implements Listener {

	public chatListener(main plugin) {
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		e.setCancelled(true);
		
		randomGetter rG = new randomGetter();
		Bukkit.broadcastMessage(rG.setStringToRandomColor(e.getPlayer().getName()) + " §8» §r" + e.getMessage());
	}
}
