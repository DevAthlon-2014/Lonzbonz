package de.lonzbonz.DevAthlon.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GameStart {

	private main plugin;
	
	public GameStart(main plugin) {
		this.plugin = plugin;
	}
	
	public void startGame() {
		chatAnimation cA = new chatAnimation(plugin);
		
		
		List<String> list = new ArrayList<>();
		list.add("§c§lEs sind nun §b§l" + plugin.minPlayer + " §c§lSpieler online");
		list.add("§c§lDas Spiel startet in wenigen Augenblicken");
		list.add("");
		list.add("§6§lViel Glück wünscht §a§lLonzbonz");
		
		for(Player players : Bukkit.getOnlinePlayers()) {

			plugin.joinRun.get(players.getName()).cancel();
			plugin.joinRun.clear();
			
			cA.displayWithDelay(players, list, 2);
		}
	}
}
