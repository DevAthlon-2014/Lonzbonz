package de.lonzbonz.DevAthlon.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Player;

public class GameStart {

	private main plugin;
	
	public GameStart(main plugin) {
		this.plugin = plugin;
	}
	
	public void startGame() {

		for(Player players : Bukkit.getOnlinePlayers()) {

			if(plugin.joinRun.containsKey(players.getName())) {
				plugin.joinRun.get(players.getName()).cancel();
				plugin.joinRun.clear();
			}
			
			List<String> list = new ArrayList<>();
			list.add("§c§lEs sind nun §b§l" + plugin.minPlayer + " §c§lSpieler online");
			list.add("§c§lDas Spiel startet in wenigen Augenblicken");
			list.add("");
			list.add("§6§lViel Glück wünscht §a§lLonzbonz");
			list.add("");
			list.add("§nAnleitung:§r §eTreffe die fliegenden Ghasts mit der §6§lPferde-Flare");
			list.add("§nAnleitung:§r §eTreffe die fliegenden Ghasts mit der §6§lPferde-Flare");
			list.add("");
			list.add("§nWer gewinnt?:§r §eDer der nach 3 Minuten die meisten Treffer erzielt hat");
			list.add("§nWer gewinnt?:§r §eDer der nach 3 Minuten die meisten Treffer erzielt hat");
			list.add("");
			list.add("             §4§l3");
			list.add("             §4§l2");
			list.add("             §4§l1");
			list.add("             §4§lGo!");
			list.add("");
			
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				@Override
				public void run() {
					plugin.state = GameState.RUNNING;
					MobSpawner MS = new MobSpawner(plugin);
					MS.startSpawning();
					startIngameTimer();
				}
			}, 20*30);
			
			chatAnimation cA = new chatAnimation(plugin);
			cA.displayWithDelay(players, list, 2);
		}
	}
	
	
	public void startIngameTimer() {
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				Bukkit.getScheduler().cancelAllTasks();
				
				for(Ghast g : plugin.ghasts) {
					g.remove();
				}
				
				for(Player players : Bukkit.getOnlinePlayers()) {
					chatAnimation cA = new chatAnimation(plugin);
					cA.display(players, plugin.prefix + "§c§lDas Spiel ist vorbei!");
				}
				
			}
		}, 20*60*3);
	}
}
