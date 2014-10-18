package de.lonzbonz.DevAthlon.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class randomGetter {

	/**
	 * 
	 * To set a String in a random color
	 * 
	 * @param name - to set in a random color
	 * @return the colored String
	 */
	public String setStringToRandomColor(String name) {
		String[] array = name.split("");
		String build = "";
		
		for(int i = 0; i < array.length; i++) {
			build = build + getRandomColor() + array[i];
		}
		
		return build;
		
	}
	
	public String getRandomColor() {
		List<String> colors = new ArrayList<>();
		colors.add("�1");
		colors.add("�2");
		colors.add("�3");
		colors.add("�4");
		colors.add("�5");
		colors.add("�6");
		colors.add("�7");
		colors.add("�8");
		colors.add("�9");
		colors.add("�a");
		colors.add("�d");
		colors.add("�c");
		colors.add("�b");
		
		Random r = new Random();
		
		return colors.get(r.nextInt(colors.size()));
	}
	
}