package de.lonzbonz.DevAthlon.main;

public enum GameState {

	/**
	 * @author Lonzbonz
	 * @date 18.10.2014
	 */

	WAITING("§eWarten..."),
	RUNNING("§4In-Game..."),
	RESTARTING("§cRestarting");
	
	String title;
	
	GameState(String title) {
		this.title = title;
	}
	
}
