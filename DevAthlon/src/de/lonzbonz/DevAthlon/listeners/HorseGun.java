package de.lonzbonz.DevAthlon.listeners;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.bukkit.scheduler.BukkitRunnable;

import de.lonzbonz.DevAthlon.main.GameState;
import de.lonzbonz.DevAthlon.main.main;

public class HorseGun implements Listener {

	private main plugin;
	
	public HorseGun(main plugin) {
		this.plugin = plugin;
	}
	
	//Ressources
	private List<String> cooldown = new ArrayList<>();
	private HashMap<String, BukkitRunnable> exp = new HashMap<>();
	private HashMap<String, Integer> actual = new HashMap<>();
	int max = 20;
	
	@EventHandler
	public void onShoot(PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		
		if(!(plugin.state == GameState.RUNNING)) return;
		
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
				
				startExp(p);

				
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
	
	
	public void startExp(final Player p) {
		actual.put(p.getName(), 20);
		
		exp.put(p.getName(), new BukkitRunnable() {
			@Override
			public void run() {
				double set = (double) 1/max*actual.get(p.getName());
				
				p.setExp((float)set);
				
				actual.put(p.getName(), actual.get(p.getName())-1);
			}
		});
		exp.get(p.getName()).runTaskTimer(plugin, 1, 1);
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				exp.get(p.getName()).cancel();
				exp.remove(p.getName());
				p.setExp(0);
			}
		}, 20);
	}
}
