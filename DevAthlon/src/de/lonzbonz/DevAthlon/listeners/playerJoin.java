package de.lonzbonz.DevAthlon.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.lonzbonz.DevAthlon.main.GameStart;
import de.lonzbonz.DevAthlon.main.chatAnimation;
import de.lonzbonz.DevAthlon.main.main;
import de.lonzbonz.DevAthlon.main.playerItems;
import de.lonzbonz.DevAthlon.main.randomGetter;

public class playerJoin implements Listener {

	/**
	 * @author Lonzbonz
	 * @date 18.10.2014
	 */
	
	private main plugin;
	
	public playerJoin(main plugin) {
		this.plugin = plugin;
	}
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		final Player p = e.getPlayer();
		
		p.setGameMode(GameMode.CREATIVE);
		p.setAllowFlight(false);
		p.setFlying(false);
		p.setHealth(20);
		p.setFoodLevel(20);
		p.setNoDamageTicks(Integer.MAX_VALUE);
		p.setFireTicks(0);
		
		String name = p.getName();
		if(name.length() > 14) {
			name = name.substring(0, 14);
		}
		p.setPlayerListName("§e" + name);
		
		for(Entity ent : p.getWorld().getEntities()) {
			if(ent.getType() == EntityType.CHICKEN | ent.getType() == EntityType.COW
					 | ent.getType() == EntityType.DROPPED_ITEM | ent.getType() == EntityType.PIG
					 | ent.getType() == EntityType.HORSE) {
				ent.remove();
			}
		}
		
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
		
		if(Bukkit.getOnlinePlayers().length == 2) {
			GameStart GS = new GameStart(plugin);
			GS.startGame();
		}
	}

}
