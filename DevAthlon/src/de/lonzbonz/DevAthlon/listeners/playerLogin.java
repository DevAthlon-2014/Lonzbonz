package de.lonzbonz.DevAthlon.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import de.lonzbonz.DevAthlon.main.GameState;
import de.lonzbonz.DevAthlon.main.main;

public class playerLogin implements Listener {

	private main plugin;
	
	public playerLogin(main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		if(plugin.state == GameState.RESTARTING) {
			e.disallow(null, "§4Server restartet gerade :)");
		}
		if(plugin.state == GameState.RUNNING) {
			e.disallow(null, "§cSpiel läuft im Moment schon :)");
		}
	}
}
