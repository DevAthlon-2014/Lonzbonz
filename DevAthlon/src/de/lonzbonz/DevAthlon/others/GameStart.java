package de.lonzbonz.DevAthlon.others;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.lonzbonz.DevAthlon.enums.GameState;
import de.lonzbonz.DevAthlon.main.Main;

public class GameStart {

	/**
	 * @author Lonzbonz
	 * @date 18.10.2014
	 */

	private Main plugin;
	
	public GameStart(Main plugin) {
		this.plugin = plugin;
	}
	
	//Ressources
	String winner;
	Location loc;
	
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
			
			ChatAnimation cA = new ChatAnimation(plugin);
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
					RandomGetter rG = new RandomGetter();
					p.setCustomName(rG.setStringToRandomColor("Rudi Rüssel"));
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
					ChatAnimation cA = new ChatAnimation(plugin);
					cA.displayWithoutDelay(players, plugin.prefix + "§c§lDas Spiel ist vorbei!");
					plugin.state = GameState.RESTARTING;
					players.removePotionEffect(PotionEffectType.SLOW);
					players.removePotionEffect(PotionEffectType.CONFUSION);
					players.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 10));
					players.getInventory().clear();
					players.getInventory().setHelmet(null);
					players.getInventory().setChestplate(null);
					players.getInventory().setLeggings(null);
					players.getInventory().setBoots(null);
				}
				
				winner = "";
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
					ChatAnimation cA = new ChatAnimation(plugin);
					List<String> list = new ArrayList<>();
					list.add("");
					list.add("§6§lJetzt wird's spannend!");
					list.add("§b§lGewonnen hat...");
					
					RandomGetter rG = new RandomGetter();
					list.add("        " + rG.setStringToRandomColor(winner) + " §rmit §e" + max + " §rTreffern");
					list.add("§a§lGlückwunsch! <3");
					list.add("");
					list.add("§4§lServer startet neu!");
					
					cA.displayWithDelay(players, list, 3);
				}
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					@SuppressWarnings("deprecation")
					@Override
					public void run() {
						spawnWinnerPrice(Bukkit.getPlayer(winner));
					}
				}, 20*8);
				
				
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
	
	public void spawnWinnerPrice(Player p) {
		
		loc = p.getLocation();
		
		loc = loc.add(0, 4, 0);
		
		loc.getWorld().spawnEntity(loc, EntityType.ENDER_CRYSTAL);
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				loc.getWorld().playEffect(loc, Effect.MOBSPAWNER_FLAMES, 3);
				loc.getWorld().playSound(loc, Sound.LEVEL_UP, 3, 3);
				loc.getWorld().spawnEntity(loc, EntityType.EXPERIENCE_ORB);
			}
		}, 10, 10);
		
	}
}
