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

import de.lonzbonz.DevAthlon.main.GameStart;
import de.lonzbonz.DevAthlon.main.chatAnimation;
import de.lonzbonz.DevAthlon.main.main;
import de.lonzbonz.DevAthlon.main.playerItems;
import de.lonzbonz.DevAthlon.main.randomGetter;

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
		
		playerItems pI = new playerItems();
		pI.setJoinItems(p);
		
		plugin.points.put(p.getName(), 0);
		
		chatAnimation cA = new chatAnimation(plugin);
		cA.display(p, "");
		
		Location loc = Bukkit.getWorld(plugin.worldName).getSpawnLocation();
		loc = loc.add(0, 10, 0);
		p.teleport(loc);
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				p.setVelocity(p.getVelocity().setY(1D));
				for(int i = 0; i < 25; i++) {
					p.getWorld().playEffect(p.getLocation(), Effect.SMOKE, 3);
				}
			}
		}, 20);
		
		randomGetter rG = new randomGetter();
		e.setJoinMessage(rG.setStringToRandomColor(p.getName()) + "§r §ehat den Server betreten");
		
		if(!plugin.joinRun.containsKey(p.getName())) {
			plugin.joinRun.put(p.getName(), new BukkitRunnable() {
				@Override
				public void run() {
					startJoinShow(p);				}
			});
			plugin.joinRun.get(p.getName()).runTaskLater(plugin, 20*3);
		}
		
		if(Bukkit.getOnlinePlayers().length == 2) {
			GameStart GS = new GameStart(plugin);
			GS.startGame();
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
		list.add("§b§lDas Spiel startet bei §c§l" + plugin.minPlayer + " §b§lSpielern!");
		cA.displayWithDelay(p, list, 3);

		
		
	}

}
