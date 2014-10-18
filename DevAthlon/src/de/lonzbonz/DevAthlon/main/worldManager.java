package de.lonzbonz.DevAthlon.main;

import org.bukkit.WorldCreator;

public class worldManager {

	public String world;
	
	public worldManager(String world) {
		this.world = world;
	}
	
	public boolean generateWorld() {
		try {
			WorldCreator wc = new WorldCreator(world);
			wc.seed(765105775);
			wc.createWorld();
		} catch(Exception e) {
			return false;
		}
		return true;
	}

	public String getWorld() {
		return world;
	}
	
}
