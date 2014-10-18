package de.lonzbonz.DevAthlon.main;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

public class fireworkManager {
	
	public void startJoinFirework(Player p) {
		
		Firework fw = p.getLocation().getWorld().spawn(p.getLocation(), Firework.class);
		
		FireworkEffect effect = FireworkEffect.builder()
				.trail(true)
				.withColor(Color.AQUA)
				.withFade(Color.RED)
				.with(Type.BURST)
				.build();
		
		FireworkMeta meta = fw.getFireworkMeta();
		meta.addEffect(effect);
		meta.setPower(10);
		
		fw.setPassenger(p);
		
	}
	
}
