package assets;



import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.marvnet.gtm.Main;
import de.robingrether.idisguise.disguise.DisguiseType;
import de.robingrether.idisguise.disguise.RabbitDisguise;
import de.robingrether.idisguise.disguise.RabbitDisguise.RabbitType;

public class Disguise {
	
	public static ArrayList<Player> disguisedPlayer = new ArrayList<Player>();
	
	public static ItemStack createItem(String name, Material material) {
		ItemStack item = new ItemStack(material);
		ItemMeta imeta = (ItemMeta) item.getItemMeta();
		imeta.setDisplayName(name);
		item.setItemMeta(imeta);
		return item;
	}
	
	@SuppressWarnings("deprecation")
	public static void unDisguise(Player p) {
		for(Player play : Bukkit.getOnlinePlayers()) {
			play.showPlayer(p);
		}
		if(Main.disguiseapi.isDisguised(p)) {
			disguisedPlayer.remove(p);
			Main.disguiseapi.undisguise(p);
		}
	}
	
	public static Inventory getGUI(Player p) {
		Inventory inv = Bukkit.createInventory(null, 4*9, "§8§lDisguise");
		
		if(isDisguised(p)) {
			inv.addItem(createItem("§c§lUndisguise", Material.BARRIER));
		}
		inv.addItem(createItem("§4§lRabbit",Material.RABBIT_FOOT));
		if(p.hasPermission("gtm.vanish")) {
			inv.addItem(createItem("§8§lVanish",Material.GOLDEN_APPLE));
		}
		
		
		return inv;
	}
	
	public static ItemStack getItem(Player p) {
		return createItem("§8Disguise",Material.CHAINMAIL_CHESTPLATE);
	}
	
	public static void disguiseRabbit(Player p) {
		RabbitDisguise disguise = (RabbitDisguise)DisguiseType.RABBIT.newInstance();
		disguise.setAdult(false);
		disguise.setRabbitType(RabbitType.THE_KILLER_BUNNY);
		disguise.setCustomName(p.getDisplayName());
		disguisedPlayer.add(p);
		Main.disguiseapi.disguise(p, disguise);
	}

	public static boolean isDisguised(Player p) {
		if(disguisedPlayer.contains(p)) {
			return true;
		} else {
			return false;
		}
	}

}
