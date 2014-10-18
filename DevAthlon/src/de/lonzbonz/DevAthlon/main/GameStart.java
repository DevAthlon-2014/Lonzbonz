package de.lonzbonz.DevAthlon.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Player;

public class GameStart {

	/**
	 * @author Lonzbonz
	 * @date 18.10.2014
	 */

	private main plugin;
	
	public GameStart(main plugin) {
		this.plugin = plugin;
	}
	
	/**
	 * all things to do if the game starts
	 */
	public void startGame() {

		for(Player players : Bukkit.getOnlinePlayers()) {
			
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
			
			chatAnimation cA = new chatAnimation(plugin);
			cA.displayWithDelay(players, list, 2);
		}
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				plugin.state = GameState.RUNNING;
				MobSpawner MS = new MobSpawner(plugin);
				MS.startSpawning();
				startIngameTimer();
			}
		}, 20*30);
		
	}
	
	/**
	 * 
	 * starts the ingame timer to finish the game
	 * 
	 */
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
				
				String winner = "";
				int max = 0;
				for(int i : plugin.points.values()) {
					if(i > max) {
						max = i;
					}
				}
				
				for(String all : plugin.points.keySet()) {
					if(plugin.points.get(all) == max) {
						winner = all;
					}
				}
				
				for(Player players : Bukkit.getOnlinePlayers()) {
					chatAnimation cA = new chatAnimation(plugin);
					List<String> list = new ArrayList<>();
					list.add("");
					list.add("§6§lJetzt wird's spannend!");
					list.add("§b§lGewonnen hat...");
					
					randomGetter rG = new randomGetter();
					list.add("        " + rG.setStringToRandomColor(winner) + " §rmit §e" + max + " §rTreffern");
					list.add("§a§lGlückwunsch! <3");
					list.add("");
					list.add("§4§lServer startet neu!");
					
					cA.displayWithDelay(players, list, 3);
				}
				
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					@Override
					public void run() {
						Bukkit.shutdown();
					}
				}, 20*20);
				
				
			}
		}, 20*60*1);
	}
}
