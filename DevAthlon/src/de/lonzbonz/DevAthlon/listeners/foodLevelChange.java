package de.lonzbonz.DevAthlon.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import de.lonzbonz.DevAthlon.main.main;

public class foodLevelChange implements Listener {

	public foodLevelChange(main plugin) {
	}
	
	@EventHandler
	public void onFoodLevel(FoodLevelChangeEvent e) {
		if(e.getEntity() instanceof Player) {
			e.setCancelled(true);
		}
	}
}
