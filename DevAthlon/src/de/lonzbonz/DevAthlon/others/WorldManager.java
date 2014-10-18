package de.lonzbonz.DevAthlon.others;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import de.lonzbonz.DevAthlon.main.Main;

public class WorldManager {

	/**
	 * @author Lonzbonz
	 * @date 18.10.2014
	 */
	
	private Main plugin;
	public String world;
	
	public WorldManager(String world, Main plugin) {
		this.world = world;
		this.plugin = plugin;
	}
	
	/**
	 * 
	 * @return false if there was a error or anything else
	 */
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
	
	/**
	 * starts a timer to set the weather
	 */
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
