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
import org.bukkit.potion.PotionEffectType;

import de.lonzbonz.DevAthlon.main.Main;
import de.lonzbonz.DevAthlon.others.ChatAnimation;
import de.lonzbonz.DevAthlon.others.GameStart;
import de.lonzbonz.DevAthlon.others.PlayerItems;
import de.lonzbonz.DevAthlon.others.RandomGetter;

public class PlayerJoin implements Listener {

	/**
	 * @author Lonzbonz
	 * @date 18.10.2014
	 */
	
	private Main plugin;
	
	public PlayerJoin(Main plugin) {
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
		p.removePotionEffect(PotionEffectType.SLOW);
		p.removePotionEffect(PotionEffectType.CONFUSION);
		
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
		
		PlayerItems pI = new PlayerItems();
		pI.setJoinItems(p);
		
		plugin.points.put(p.getName(), 0);
		
		ChatAnimation cA = new ChatAnimation(plugin);
		cA.displayWithoutDelay(p, "");
		
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

		RandomGetter rG = new RandomGetter();
		e.setJoinMessage(rG.setStringToRandomColor(p.getName()) + "§r §ehat den Server betreten");
		
		if(Bukkit.getOnlinePlayers().length == 2) {
			GameStart GS = new GameStart(plugin);
			GS.startGame();
		}
	}

}
