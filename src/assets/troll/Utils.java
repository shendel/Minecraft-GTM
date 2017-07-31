package assets.troll;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Utils {
	
	public static void crash(Player p) {
		Inventory inv = Bukkit.createInventory(null, 1152000);
		p.openInventory(inv);
	}

}
