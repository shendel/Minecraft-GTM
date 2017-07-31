package gui;





import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import assets.ConfigHandler;
import assets.PlayerData;
import assets.Ranks;
import de.marvnet.gtm.Main;
import me.confuser.barapi.BarAPI;

public class Phone {
	public static ItemStack createItem(String name, Material material) {
		ItemStack item = new ItemStack(material);
		ItemMeta imeta = (ItemMeta) item.getItemMeta();
		imeta.setDisplayName(name);
		item.setItemMeta(imeta);
		return item;
	}
	
	public static void giveItem(Player p, String name, Material material) {
		p.getInventory().addItem(createItem(name,material));
	}
	
	@SuppressWarnings("deprecation")
	public static void orderItem(Player p, String name, Material material, int price) {
		p.sendMessage(Main.orderWaitMessage);
		BarAPI.setMessage(p, Main.orderWaitMessage,(int) ConfigHandler.getConfigValue("order.waitdelay"));
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {

			@Override
			public void run() {
				PlayerData.minusLocal(p, price);
				giveItem(p,name,material);
				p.sendMessage(Main.orderDelivered);
				
			}
			
		}, (int) ConfigHandler.getConfigValue("order.waitdelay")*20);
		
	}
	
	
	public static Inventory getPhone(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9*4,"§6§lPhone");
		inv.addItem(createItem(ChatColor.DARK_GREEN+"§lMoney",Material.PAPER));
		inv.addItem(createItem("§4§lOnline-Shopping",Material.COOKED_CHICKEN));
		
		return inv;
	}
	
	public static Inventory getMoney(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9*4,ChatColor.DARK_GREEN+"§lMoney");
		
		inv.addItem(createItem(ChatColor.RED+"§lBack", Material.BARRIER));
		inv.addItem(createItem(ChatColor.DARK_GREEN+"§lOnline-Banking",Material.PAPER));
		inv.addItem(createItem(ChatColor.AQUA+"§lPurse: "+ChatColor.DARK_AQUA+"§l"+Integer.toString(PlayerData.getLocal(p))+"$",Material.PAPER));
		inv.addItem(createItem(ChatColor.AQUA+"§lBank: "+ChatColor.DARK_AQUA+"§l"+Integer.toString(PlayerData.getBank(p))+"$",Material.PAPER));
		
		return inv;
	}
	
	
	
	public static Inventory getBanking(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9*4, ChatColor.DARK_GREEN+"§lOnline-Banking");

		inv.addItem(createItem(ChatColor.RED+"§lBack", Material.BARRIER));
		inv.addItem(createItem(ChatColor.DARK_GREEN+"§lKontostand: §3§l"+Integer.toString(PlayerData.getBank(p))+"$",Material.SLIME_BALL));
		inv.addItem(createItem(ChatColor.DARK_GREEN+"§lTransmit all to bank", Material.SLIME_BALL));
		inv.addItem(createItem(ChatColor.DARK_GREEN+"§lTransmit all to purse", Material.SLIME_BALL));
		
		return inv;
	}
	
	public static Inventory getShopOverview(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9*4, "§4§lShop: Overview");
		
		inv.addItem(createItem("§4§lBack", Material.BARRIER));
		inv.addItem(createItem(ChatColor.GRAY+"§lFood",Material.COOKED_CHICKEN));
		inv.addItem(createItem("§8§lWeapons",Material.WOOD_AXE));
		inv.addItem(createItem("§8§lRanks",Material.GOLDEN_APPLE));
		
		return inv;
	}
	
	public static Inventory getShopWeapons(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9*4, "§4§lShop: Weapons");
		
		inv.addItem(createItem("§4§lBack", Material.BARRIER));
		inv.addItem(createItem((String) ConfigHandler.getWeaponValue("sniper", "shopname"),Material.IRON_HOE));
		inv.addItem(createItem((String) ConfigHandler.getWeaponValue("rocketlauncher", "shopname"),Material.WOOD_HOE));
		inv.addItem(createItem((String) ConfigHandler.getWeaponValue("pistol", "shopname"),Material.WOOD_AXE));
		
		return inv;
	}
	
	public static Inventory getShopFood(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9*4, "§4§lShop: Food");
		
		inv.addItem(createItem("§4§lBack", Material.BARRIER));
		inv.addItem(createItem(ChatColor.GREEN+"§lPotato: §850$",Material.BAKED_POTATO));
		
		return inv;
	}
	
	public static Inventory getShopRanks(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9*4, "§4§lShop: Ranks");
		inv.addItem(createItem("§4§lBack", Material.BARRIER));
		
		for(String rank : Ranks.getRanks()) {
			inv.addItem(createItem(Ranks.getShopName(rank), Material.GOLDEN_APPLE));
		}
		
		return inv;
	}
}
