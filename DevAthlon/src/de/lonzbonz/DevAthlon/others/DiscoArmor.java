package de.lonzbonz.DevAthlon.others;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class DiscoArmor {

	/**
	 * @author Lonzbonz
	 * @date 18.10.2014
	 */

	public void setColoredHelmet(Player p, Color color) {
		
		ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
		helmetMeta.setColor(color);
		helmet.setItemMeta(helmetMeta);
		p.getInventory().setHelmet(helmet);
		
	}
	
	public void setColoredChestplate(Player p, Color color) {
			
		ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
		chestplateMeta.setColor(color);
		chestplate.setItemMeta(chestplateMeta);
		p.getInventory().setChestplate(chestplate);
		
	}

	public void setColoredLeggings(Player p, Color color) {
		
		ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
		leggingsMeta.setColor(color);
		leggings.setItemMeta(leggingsMeta);
		p.getInventory().setLeggings(leggings);
		
	}

	public void setColoredBoots(Player p, Color color) {
		
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
		bootsMeta.setColor(color);
		boots.setItemMeta(bootsMeta);
		p.getInventory().setBoots(boots);
		
	}
	
	/**
	 * 
	 * @return the random color
	 */
	public Color getRandomColor() {
		Color color = null;
		Random r = new Random();
		int c = r.nextInt(24) + 1;
		
		switch(c) {
			case 0:
				color = Color.AQUA;
				break;
			case 1:
				color = Color.BLACK;
				break;
			case 2:
				color = Color.BLUE;
				break;
			case 3:
				color = Color.FUCHSIA;
				break;
			case 4:
				color = Color.GRAY;
				break;
			case 5:
				color = Color.GREEN;
				break;
			case 6:
				color = Color.LIME;
				break;
			case 7:
				color = Color.MAROON;
				break;
			case 8:
				color = Color.NAVY;
				break;
			case 9:
				color = Color.OLIVE;
				break;
			case 10:
				color = Color.ORANGE;
				break;
			case 11:
				color = Color.PURPLE;
				break;
			case 12:
				color = Color.RED;
				break;
			case 13:
				color = Color.SILVER;
				break;
			case 14:
				color = Color.TEAL;
				break;
			case 15:
				color = Color.WHITE;
				break;
			case 16:
				color = Color.YELLOW;
				break;
			default:
				color = Color.WHITE;
			
		}
		return color;
	}
	
}
