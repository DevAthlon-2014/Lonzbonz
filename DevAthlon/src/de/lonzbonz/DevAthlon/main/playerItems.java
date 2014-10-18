package de.lonzbonz.DevAthlon.main;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class playerItems {

	public void setJoinItems(Player p) {
		
		ItemStack item = new ItemStack(Material.GOLD_SPADE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§6Pferde-Flare");
		item.setItemMeta(meta);
		
		p.getInventory().clear();
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);
		p.getInventory().setItem(0, item);
		
	}
}
