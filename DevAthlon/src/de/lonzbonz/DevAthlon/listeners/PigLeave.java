package de.lonzbonz.DevAthlon.listeners;

import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.lonzbonz.DevAthlon.main.chatAnimation;
import de.lonzbonz.DevAthlon.main.main;


public class PigLeave implements Listener {

	private main plugin;
	
	public PigLeave(main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPigLeave(PlayerToggleSneakEvent e) {
		Player p = e.getPlayer();
		
		if(p.isInsideVehicle()) {
			chatAnimation cA = new chatAnimation(plugin);
			cA.display(p, "�cSie wurden vergiftet! Steigen sie schnell wieder auf das Schwein!");
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
