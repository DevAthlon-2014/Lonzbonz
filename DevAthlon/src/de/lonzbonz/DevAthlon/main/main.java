package de.lonzbonz.DevAthlon.main;

import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import de.lonzbonz.DevAthlon.listeners.playerJoin;
import de.lonzbonz.DevAthlon.listeners.playerQuit;

public class main extends JavaPlugin {
	
	public void onEnable() {
		registerEvents();
		registerCommands();
		registerClasses();
		System.out.println("[DevAthlon] Plugin enabled!");
	}
	
	public void registerEvents() {
		this.getServer().getPluginManager().registerEvents(new playerJoin(this), this);
		this.getServer().getPluginManager().registerEvents(new playerQuit(this), this);
	}
	
	public void registerCommands() {
		
	}
	
	public void registerClasses() {
		new chatAnimation(this);
	}
	
	
	
	
	//Ressources
	public HashMap<String, BukkitRunnable> joinRun = new HashMap<>();
	
	

}
