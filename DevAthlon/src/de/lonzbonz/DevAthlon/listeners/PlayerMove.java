package de.lonzbonz.DevAthlon.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import de.lonzbonz.DevAthlon.main.main;

public class PlayerMove implements Listener {

	/**
	 * @author Lonzbonz
	 * @date 18.10.2014
	 */

	private main plugin;
	
	public PlayerMove(main plugin) {
		this.plugin = plugin;
	}
	
	//Ressources
	public List<String> cooldown = new ArrayList<>();
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		final Player p = e.getPlayer();
		
		Location pLoc = p.getLocation();
		Location middle = Bukkit.getWorld(plugin.worldName).getSpawnLocation();
		
		if(pLoc.distance(middle) < 50) return;
		
		int aX = pLoc.getBlockX();
		int aY = pLoc.getBlockY();
		int aZ = pLoc.getBlockZ();
		
		int bX = middle.getBlockX();
		int bY = middle.getBlockY();
		int bZ = middle.getBlockZ();
		
		int x = bX - aX;
		int y = bY - aY;
		int z = bZ - aZ;
		
		Vector vector = new Vector(x, y, z).normalize();
		vector = vector.setY(0.4D);
		vector = vector.multiply(0.6D);
		
		p.setVelocity(vector);
		
		if(!cooldown.contains(p.getName())) {
			p.sendMessage(plugin.prefix + "§eDu hast §adas Ende der Arena §eerreicht!");
			p.getWorld().playEffect(p.getLocation(), Effect.GHAST_SHRIEK, 3, 1);
			p.getWorld().playSound(p.getLocation(), Sound.ANVIL_BREAK, 1, 3);
			p.getWorld().playEffect(p.getLocation(), Effect.POTION_BREAK, 3);
			cooldown.add(p.getName());
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				@SuppressWarnings("unused")
				@Override
				public void run() {
					if(p != null) {
						cooldown.remove(p.getName());
					} else if(p == null) {
						cooldown.clear();
					}
				}
			}, 20);
		}
	}
}
