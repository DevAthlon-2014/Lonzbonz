package de.lonzbonz.DevAthlon.listeners;

import org.bukkit.Effect;
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
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		final Player p = e.getPlayer();
		
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
		cA.showInChat(p, "§3§lLasset");
		
		
	}

}
