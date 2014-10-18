package de.lonzbonz.DevAthlon.listeners;

import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.lonzbonz.DevAthlon.main.Main;
import de.lonzbonz.DevAthlon.others.ChatAnimation;


public class PigInteract implements Listener {

	private Main plugin;
	
	public PigInteract(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPigLeave(PlayerToggleSneakEvent e) {
		Player p = e.getPlayer();
		
		if(p.isInsideVehicle()) {
			ChatAnimation cA = new ChatAnimation(plugin);
			cA.displayWithoutDelay(p, "§cSie wurden vergiftet! Steigen sie schnell wieder auf das Schwein!");
			p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, Integer.MAX_VALUE, 3));
		}
	}
	
	@EventHandler
	public void onPigEnter(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if(e.getRightClicked() instanceof Pig) {
			if(!p.isInsideVehicle()) {
				p.removePotionEffect(PotionEffectType.CONFUSION);
			}
		}
	}
}
