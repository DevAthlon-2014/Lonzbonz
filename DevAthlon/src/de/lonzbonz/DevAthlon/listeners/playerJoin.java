package de.lonzbonz.DevAthlon.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import de.lonzbonz.DevAthlon.main.chatAnimation;
import de.lonzbonz.DevAthlon.main.main;

public class playerJoin implements Listener {
	
	private main plugin;
	
	public playerJoin(main plugin) {
		this.plugin = plugin;
	}
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		final Player p = e.getPlayer();
		
		p.setGameMode(GameMode.ADVENTURE);
		p.setAllowFlight(false);
		p.setFlying(false);
		p.setHealth(20);
		p.setFoodLevel(20);
		p.setNoDamageTicks(Integer.MAX_VALUE);
		p.setFireTicks(0);
		chatAnimation cA = new chatAnimation(plugin);
		cA.showInChat(p, "");
		Location loc = Bukkit.getWorld(plugin.worldName).getSpawnLocation();
		loc = loc.add(0, 10, 0);
		p.teleport(loc);
		e.setJoinMessage("�e�n" + p.getName() + "�r �ehat den Server betreten");
		
		if(!plugin.joinRun.containsKey(p.getName())) {
			plugin.joinRun.put(p.getName(), new BukkitRunnable() {
				@Override
				public void run() {
					startJoinShow(p);				}
			});
			plugin.joinRun.get(p.getName()).runTaskLater(plugin, 20*3);
		}
	}
	
	
	private void startJoinShow(Player p) {
		if(p == null) return;
		
		Location loc = p.getLocation();
		for(int i = 0; i < 3; i++) {
			p.getWorld().playEffect(loc, Effect.MOBSPAWNER_FLAMES, 3);
		}
		p.getWorld().playSound(loc, Sound.FIREWORK_BLAST2, 3, 3);
		
		chatAnimation cA = new chatAnimation(plugin);
		List<String> list = new ArrayList<>();
		list.add("�3�lLasset");
		list.add("�4�ldie");
		list.add("�5�lSpiele");
		list.add("�2�lbeginnen!");
		cA.showInChatWithDelay(p, list, 1);

		
		
	}

}
