package de.lonzbonz.DevAthlon.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import de.lonzbonz.DevAthlon.main.main;

public class HorseGun implements Listener {

	private main plugin;
	
	public HorseGun(main plugin) {
		this.plugin = plugin;
	}
	
	//Ressources
	private List<String> cooldown = new ArrayList<>();
	
	@EventHandler
	public void onShoot(PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		
		if(!cooldown.contains(p.getName())) {
			cooldown.add(p.getName());
			if(e.getMaterial() == Material.GOLD_SPADE) {
				e.setCancelled(true);
				
				p.getWorld().playSound(p.getLocation(), Sound.EXPLODE, 3, 3);
				p.getWorld().playEffect(p.getLocation(), Effect.SMOKE, 3);
				
				p.setVelocity(p.getLocation().getDirection().multiply(-0.5D));
				p.setVelocity(p.getVelocity().setY(0.2D));
				
				final Horse horse = (Horse) p.getWorld().spawnEntity(p.getLocation(), EntityType.HORSE);
				horse.setBaby();
				horse.setVelocity(p.getLocation().getDirection().multiply(3D));
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					@Override
					public void run() {
						horse.getWorld().createExplosion(horse.getLocation(), 3, false);
					}
				}, 20*3);
				
			}
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				@Override
				public void run() {
					cooldown.remove(p.getName());
				}
			}, 20);
		} else {
			p.sendMessage(plugin.prefix + "§cLade §6Pferde-Flare §cnach!");
		}
	}
}
