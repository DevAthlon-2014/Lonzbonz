package de.lonzbonz.DevAthlon.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Ghast;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import de.lonzbonz.DevAthlon.enums.GameState;
import de.lonzbonz.DevAthlon.listeners.HorseGun;
import de.lonzbonz.DevAthlon.listeners.PigInteract;
import de.lonzbonz.DevAthlon.listeners.PlayerMove;
import de.lonzbonz.DevAthlon.listeners.CancelListeners;
import de.lonzbonz.DevAthlon.listeners.ChatListeners;
import de.lonzbonz.DevAthlon.listeners.FoodLevelChange;
import de.lonzbonz.DevAthlon.listeners.PlayerJoin;
import de.lonzbonz.DevAthlon.listeners.PlayerLogin;
import de.lonzbonz.DevAthlon.listeners.PlayerQuit;
import de.lonzbonz.DevAthlon.others.ChatAnimation;
import de.lonzbonz.DevAthlon.others.GameStart;
import de.lonzbonz.DevAthlon.others.MobSpawner;
import de.lonzbonz.DevAthlon.others.WorldManager;

public class Main extends JavaPlugin {
	
	/**
	 * 
	 * @author Lonzbonz
	 * plugin created on 18.10.2014
	 * 
	 */
	
	public void onEnable() {
		registerEvents();
		registerCommands();
		registerClasses();
		
		WorldManager wM = new WorldManager(worldName, this);
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
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "save-off");
		
		System.out.println("[DevAthlon] Plugin enabled!");
	}
	
	public void onDisable() {
		Bukkit.unloadWorld(worldName, true);
		Bukkit.getScheduler().cancelAllTasks();
	}
	
	public void registerEvents() {
		this.getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
		this.getServer().getPluginManager().registerEvents(new PlayerQuit(this), this);
		this.getServer().getPluginManager().registerEvents(new FoodLevelChange(this), this);
		this.getServer().getPluginManager().registerEvents(new PlayerMove(this), this);
		this.getServer().getPluginManager().registerEvents(new PlayerLogin(this), this);
		this.getServer().getPluginManager().registerEvents(new HorseGun(this), this);
		this.getServer().getPluginManager().registerEvents(new CancelListeners(this), this);
		this.getServer().getPluginManager().registerEvents(new ChatListeners(this), this);
		this.getServer().getPluginManager().registerEvents(new PigInteract(this), this);
	}
	
	public void registerCommands() {
		
	}
	
	public void registerClasses() {
		new ChatAnimation(this);
		new GameStart(this);
		new MobSpawner(this);
	}
	
	public void loadConfig() {
		
		getConfig().options().copyDefaults(true);
		getConfig().addDefault("minPlayers", 2);
		saveConfig();
	}
	
	
	
	
	//Ressources
	public HashMap<String, BukkitRunnable> chatRun = new HashMap<>();
	public String worldName = "Devathlon";
	public int minPlayer = 0;
	public GameState state;
	public String prefix = "§7[§bDevathlon§7] ";
	public HashMap<String, Integer> points = new HashMap<>();
	public List<Ghast> ghasts = new ArrayList<>();
	
	

}
