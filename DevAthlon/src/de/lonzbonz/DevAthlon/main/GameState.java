package de.lonzbonz.DevAthlon.main;

public enum GameState {

	WAITING("§eWarten..."),
	RUNNING("§4In-Game..."),
	RESTARTING("§cRestarting");
	
	String title;
	
	GameState(String title) {
		this.title = title;
	}
	
}
