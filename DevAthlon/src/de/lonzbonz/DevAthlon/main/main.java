package de.lonzbonz.DevAthlon.main;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import de.lonzbonz.DevAthlon.listeners.foodLevelChange;
import de.lonzbonz.DevAthlon.listeners.playerJoin;
import de.lonzbonz.DevAthlon.listeners.playerQuit;

public class main extends JavaPlugin {
	
	/**
	 * plugin created on 18.10.2014
	 * 
	 */
	
	public void onEnable() {
		registerEvents();
		registerCommands();
		registerClasses();
		
		worldManager wM = new worldManager(worldName);
		boolean success = wM.generateWorld();
		if(success) {
			System.out.println("[Devathlon] '" + wM.getWorld() + "' world generated!");
		} else {
			System.err.println("[Devathlon] '" + wM.getWorld() + "' world couldn't generate!");
		}
		
		System.out.println("[DevAthlon] Plugin enabled!");
	}
	
	public void onDisable() {
		Bukkit.unloadWorld(worldName, true);
	}
	
	public void registerEvents() {
		this.getServer().getPluginManager().registerEvents(new playerJoin(this), this);
		this.getServer().getPluginManager().registerEvents(new playerQuit(this), this);
		this.getServer().getPluginManager().registerEvents(new foodLevelChange(this), this);
	}
	
	public void registerCommands() {
		
	}
	
	public void registerClasses() {
		new chatAnimation(this);
	}
	
	
	
	
	//Ressources
	public HashMap<String, BukkitRunnable> joinRun = new HashMap<>();
	public HashMap<String, BukkitRunnable> chatRun = new HashMap<>();
	public String worldName = "Devathlon";
	
	

}
