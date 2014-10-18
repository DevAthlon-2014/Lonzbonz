package de.lonzbonz.DevAthlon.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import de.lonzbonz.DevAthlon.main.GameState;
import de.lonzbonz.DevAthlon.main.main;

public class HorseGun implements Listener {

	/**
	 * @author Lonzbonz
	 * @date 18.10.2014
	 */

	private main plugin;
	
	public HorseGun(main plugin) {
		this.plugin = plugin;
	}
	
	//Ressources
	private List<String> cooldown = new ArrayList<>();
	private HashMap<String, BukkitRunnable> exp = new HashMap<>();
	private HashMap<String, Integer> actual = new HashMap<>();
	private HashMap<Entity, BukkitRunnable> entRun = new HashMap<>();
	int max = 20;
	public List<String> zoom = new ArrayList<>();
	
	@EventHandler
	public void onShoot(PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		
		if(!(plugin.state == GameState.RUNNING)) return;

		if(e.getAction() == Action.LEFT_CLICK_AIR | e.getAction() == Action.LEFT_CLICK_BLOCK) {
			p.playSound(p.getLocation(), Sound.IRONGOLEM_HIT, 3, 3);
			if(zoom.contains(p.getName())) {
				zoom.remove(p.getName());
				p.removePotionEffect(PotionEffectType.SLOW);
				
			} else {
				zoom.add(p.getName());
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 3));
				
			}
			return;
		}
		
		if(e.getMaterial() == Material.GOLD_SPADE) {
			if(!cooldown.contains(p.getName())) {
				cooldown.add(p.getName());
				e.setCancelled(true);
				
				p.getWorld().playSound(p.getLocation(), Sound.EXPLODE, 3, 3);
				p.getWorld().playEffect(p.getLocation(), Effect.SMOKE, 3);
				
				p.setVelocity(p.getLocation().getDirection().multiply(-0.5D));
				p.setVelocity(p.getVelocity().setY(0.2D));
				
				final Horse horse = (Horse) p.getWorld().spawnEntity(p.getLocation(), EntityType.HORSE);
				horse.setBaby();
				horse.setVelocity(p.getLocation().getDirection().multiply(3D));
				
//				startExp(p);

				
				entRun.put(horse, new BukkitRunnable() {
					@Override
					public void run() {
						
						for(Entity ent : horse.getNearbyEntities(2, 2, 2)) {
							if(ent.getType() == EntityType.GHAST) {
								
								Ghast ghast = (Ghast) ent;
								ghast.setHealth(0D);
								plugin.ghasts.remove(ghast);
								ghast.getWorld().strikeLightningEffect(ghast.getLocation());
								
								p.sendMessage(plugin.prefix + "§a§l+ 1 Treffer");
								plugin.points.put(p.getName(), plugin.points.get(p.getName())+1);
								p.getWorld().playSound(p.getLocation(), Sound.FIREWORK_LARGE_BLAST2, 3, 3);
								p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20*1, 3));
								
								entRun.get(horse).cancel();
								entRun.remove(horse);
								
							}
						}
					}
				});
				entRun.get(horse).runTaskTimer(plugin, 10, 10);
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					@Override
					public void run() {
						if(horse != null) {
							if(entRun.containsKey(horse)) {
								entRun.get(horse).cancel();
								entRun.remove(horse);
							}
							horse.remove();
						}
					}
				}, 20*4);
				
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
	
	
	/**
	 * UNUSED THIS MOMENT
	 * @deprecated
	 * @param p - player to change the exp
	 */
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
