package de.lonzbonz.DevAthlon.main;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class worldManager {

	private main plugin;
	public String world;
	
	public worldManager(String world, main plugin) {
		this.world = world;
		this.plugin = plugin;
	}
	
	public boolean generateWorld() {
		try {
			WorldCreator wc = new WorldCreator(world);
			wc.seed(765105775);
			wc.createWorld();
		} catch(Exception e) {
			return false;
		}
		return true;
	}

	public String getWorld() {
		return world;
	}
	
	public void startGoodWeather() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				World w = Bukkit.getWorld(plugin.worldName);
				w.setTime(1000);
				w.setStorm(false);
				w.setThunderDuration(0);
				w.setThundering(false);
				w.setAnimalSpawnLimit(0);
				w.setMonsterSpawnLimit(0);
			}
		}, 20*3, 20*3);
	}
	
}
