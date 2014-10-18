package de.lonzbonz.DevAthlon.main;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;

public class MobSpawner {

	private main plugin;
	
	public MobSpawner(main plugin) {
		this.plugin = plugin;
	}
	
	
	public void startSpawning() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				
				Location middle = Bukkit.getWorld(plugin.worldName).getSpawnLocation();
				
				Location loc = middle.add(getRandom(25), getRandom(25), getRandom(25));
				
				Ghast ghast = (Ghast) loc.getWorld().spawnEntity(loc, EntityType.GHAST);
				plugin.ghasts.add(ghast);
				
				ghast.setCustomNameVisible(true);
				
				randomGetter rG = new randomGetter();
				ghast.setCustomName(rG.setStringToRandomColor("Shot me!"));
				
				for(Ghast g : plugin.ghasts) {
					g.getWorld().playEffect(g.getLocation(), Effect.MOBSPAWNER_FLAMES, 3);
				}
				
			}
		}, 0, 40);
	}
	
	public Integer getRandom(int max) {
		int erg = 0;
		Random r = new Random();
		
		erg = r.nextInt(max);
		
		return erg;
	}
}
