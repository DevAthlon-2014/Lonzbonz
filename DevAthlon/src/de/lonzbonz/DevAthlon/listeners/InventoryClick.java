package de.lonzbonz.DevAthlon.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.lonzbonz.DevAthlon.main.main;

public class InventoryClick implements Listener {

	private main plugin;
	
	public InventoryClick(main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		e.setCancelled(true);
	}
}
