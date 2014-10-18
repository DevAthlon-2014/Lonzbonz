package de.lonzbonz.DevAthlon.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.lonzbonz.DevAthlon.main.Main;
import de.lonzbonz.DevAthlon.others.RandomGetter;

public class ChatListeners implements Listener {

	/**
	 * @author Lonzbonz
	 * @date 18.10.2014
	 */

	public ChatListeners(Main plugin) {
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		e.setCancelled(true);
		
		RandomGetter rG = new RandomGetter();
		Bukkit.broadcastMessage(rG.setStringToRandomColor(e.getPlayer().getName()) + " §8» §r" + e.getMessage());
	}
}
