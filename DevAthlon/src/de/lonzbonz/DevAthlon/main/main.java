package de.lonzbonz.DevAthlon.main;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import de.lonzbonz.DevAthlon.listeners.HorseGun;
import de.lonzbonz.DevAthlon.listeners.InventoryClick;
import de.lonzbonz.DevAthlon.listeners.PlayerMove;
import de.lonzbonz.DevAthlon.listeners.cancelListeners;
import de.lonzbonz.DevAthlon.listeners.chatListener;
import de.lonzbonz.DevAthlon.listeners.foodLevelChange;
import de.lonzbonz.DevAthlon.listeners.playerJoin;
import de.lonzbonz.DevAthlon.listeners.playerLogin;
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
		
		worldManager wM = new worldManager(worldName, this);
		wM.startGoodWeather();
		boolean success = wM.generateWorld();
		if(success) {
			System.out.println("[Devathlon] '" + wM.getWorld() + "' world generated!");
		} else {
			System.err.println("[Devathlon] '" + wM.getWorld() + "' world couldn't generate!");
		}
		state = GameState.WAITING;
		loadConfig();
		minPlayer = getConfig().getInt("minPlayers");
		
		System.out.println("[DevAthlon] Plugin enabled!");
	}
	
	public void onDisable() {
		Bukkit.unloadWorld(worldName, true);
		Bukkit.getScheduler().cancelAllTasks();
	}
	
	public void registerEvents() {
		this.getServer().getPluginManager().registerEvents(new playerJoin(this), this);
		this.getServer().getPluginManager().registerEvents(new playerQuit(this), this);
		this.getServer().getPluginManager().registerEvents(new foodLevelChange(this), this);
		this.getServer().getPluginManager().registerEvents(new PlayerMove(this), this);
		this.getServer().getPluginManager().registerEvents(new playerLogin(this), this);
		this.getServer().getPluginManager().registerEvents(new InventoryClick(this), this);
		this.getServer().getPluginManager().registerEvents(new HorseGun(this), this);
		this.getServer().getPluginManager().registerEvents(new cancelListeners(this), this);
		this.getServer().getPluginManager().registerEvents(new chatListener(this), this);
	}
	
	public void registerCommands() {
		
	}
	
	public void registerClasses() {
		new chatAnimation(this);
		new GameStart(this);
	}
	
	public void loadConfig() {
		
		getConfig().options().copyDefaults(true);
		getConfig().addDefault("minPlayers", 2);
		saveConfig();
	}
	
	
	
	
	//Ressources
	public HashMap<String, BukkitRunnable> joinRun = new HashMap<>();
	public HashMap<String, BukkitRunnable> chatRun = new HashMap<>();
	public String worldName = "Devathlon";
	public int minPlayer = 0;
	public GameState state;
	public String prefix = "§7[§bDevathlon§7] ";
	public HashMap<String, Integer> points = new HashMap<>();
	
	

}
