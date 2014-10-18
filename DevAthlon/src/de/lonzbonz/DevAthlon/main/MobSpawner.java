package de.lonzbonz.DevAthlon.main;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;

public class MobSpawner {

	/**
	 * @author Lonzbonz
	 * @date 18.10.2014
	 */

	private main plugin;
	
	public MobSpawner(main plugin) {
		this.plugin = plugin;
	}
	
	/**
	 * starts the mob spawning in a certain world
	 */
	public void startSpawning() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				
				Location middle = Bukkit.getWorld(plugin.worldName).getSpawnLocation();
				
				Location loc = middle.add(getRandom(25), getRandom(25), getRandom(25));
				
				Ghast ghast = (Ghast) loc.getWorld().spawnEntity(loc, EntityType.GHAST);
				plugin.ghasts.add(ghast);
				
				for(Ghast g : plugin.ghasts) {
					g.getWorld().playEffect(g.getLocation(), Effect.MOBSPAWNER_FLAMES, 3);
				}
				
			}
		}, 0, 40);
	}
	
	/**
	 * 
	 * @param max the maximum Random 
	 * @return the random in an Integer
	 */
	public Integer getRandom(int max) {
		int erg = 0;
		Random r = new Random();
		
		erg = r.nextInt(max);
		
		return erg;
	}
}