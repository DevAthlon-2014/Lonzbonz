package de.lonzbonz.DevAthlon.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

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
			list.add("�c�lEs sind nun �b�l" + plugin.minPlayer + " �c�lSpieler online");
			list.add("�c�lDas Spiel startet in wenigen Augenblicken");
			list.add("");
			list.add("�6�lViel Gl�ck w�nscht �a�lLonzbonz");
			list.add("");
			list.add("�nAnleitung:�r �eTreffe die fliegenden Ghasts mit der �6�lPferde-Flare");
			list.add("�nAnleitung:�r �eTreffe die fliegenden Ghasts mit der �6�lPferde-Flare");
			list.add("");
			list.add("�nWer gewinnt?:�r �eDer der nach 3 Minuten die meisten Treffer erzielt hat");
			list.add("�nWer gewinnt?:�r �eDer der nach 3 Minuten die meisten Treffer erzielt hat");
			list.add("");
			list.add("             �4�l3");
			list.add("             �4�l2");
			list.add("             �4�l1");
			list.add("             �4�lGo!");
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
				
				for(Player players : Bukkit.getOnlinePlayers()) {
					@SuppressWarnings("deprecation")
					Pig p = (Pig) players.getWorld().spawnCreature(players.getLocation(), EntityType.PIG);
					p.setSaddle(true);
					players.getWorld().playEffect(players.getLocation(), Effect.POTION_BREAK, 3);
					players.playSound(players.getLocation(), Sound.ITEM_BREAK, 3, 3);
					randomGetter rG = new randomGetter();
					p.setCustomName(rG.setStringToRandomColor("Hansi"));
					p.setCustomNameVisible(true);
					p.setPassenger(players);
					startDiscoArmor(players, p);
				}
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
				
				for(Entity ent : Bukkit.getWorld(plugin.worldName).getEntities()) {
					if(ent instanceof Pig) {
						ent.remove();
					}
				}
				
				
				for(Player players : Bukkit.getOnlinePlayers()) {
					chatAnimation cA = new chatAnimation(plugin);
					cA.display(players, plugin.prefix + "�c�lDas Spiel ist vorbei!");
					plugin.state = GameState.RESTARTING;
					players.removePotionEffect(PotionEffectType.SLOW);
					players.removePotionEffect(PotionEffectType.CONFUSION);
					players.getInventory().clear();
					players.getInventory().setHelmet(null);
					players.getInventory().setChestplate(null);
					players.getInventory().setLeggings(null);
					players.getInventory().setBoots(null);
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
					list.add("�6�lJetzt wird's spannend!");
					list.add("�b�lGewonnen hat...");
					
					randomGetter rG = new randomGetter();
					list.add("        " + rG.setStringToRandomColor(winner) + " �rmit �e" + max + " �rTreffern");
					list.add("�a�lGl�ckwunsch! <3");
					list.add("");
					list.add("�4�lServer startet neu!");
					
					cA.displayWithDelay(players, list, 3);
				}
				
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					@Override
					public void run() {
						Bukkit.shutdown();
					}
				}, 20*23);
				
				
			}
		}, 20*60*1);
	}
	
	public void startDiscoArmor(final Player player, final Pig p) {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				
				DiscoArmor DA = new DiscoArmor();
				DA.setColoredHelmet(player, DA.getRandomColor());
				
				DA.setColoredChestplate(player, DA.getRandomColor());

				DA.setColoredLeggings(player, DA.getRandomColor());

				DA.setColoredBoots(player, DA.getRandomColor());
			}
		}, 3, 3);
	}
}
