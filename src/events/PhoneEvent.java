package events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import assets.ConfigHandler;
import assets.PlayerData;
import assets.Ranks;
import assets.permissions.Permissions;
import de.marvnet.gtm.Main;
import gui.Phone;




public class PhoneEvent implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			e.setCancelled(true);
			ItemMeta iteminhandmeta = p.getItemInHand().getItemMeta();
			String iteminhand = iteminhandmeta.getDisplayName();
			if(iteminhand.equals("§6§lPhone") && p.getItemInHand().getType().equals(Material.COMPASS)) {
				e.setCancelled(true);
				p.openInventory(Phone.getPhone(p));
				
			}
			
			
			
			
		}
		
	}
		
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getWhoClicked() instanceof Player) {
			Player p = (Player) e.getWhoClicked();
			if(e.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.DARK_GREEN+"§lMoney")) {
				e.setCancelled(true);
				if(e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem() != null) {
						
					ItemStack item = (ItemStack) e.getCurrentItem();
					ItemMeta meta = (ItemMeta) item.getItemMeta();
					if(meta.getDisplayName().equals(ChatColor.DARK_GREEN+"§lOnline-Banking")) {
						e.setCancelled(true);
						p.openInventory(Phone.getBanking(p));
					}
					
					if(meta.getDisplayName().startsWith(ChatColor.AQUA+"§lPurse:")) {
						e.setCancelled(true);
						p.sendMessage(Main.prefix+ChatColor.AQUA+"§lPurse: §3"+PlayerData.getLocal(p)+"$");
					}
					
					if(meta.getDisplayName().startsWith(ChatColor.AQUA+"§lBank:")) {
						e.setCancelled(true);
						p.sendMessage(Main.prefix+ChatColor.AQUA+"§lBank: §3"+PlayerData.getBank(p)+"$");
					}
					
					if(meta.getDisplayName().equals(ChatColor.RED+"§lBack")) {
						e.setCancelled(true);
						p.openInventory(Phone.getPhone(p));
					}
							
							
						
				}
			} else if(e.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.DARK_GREEN+"§lOnline-Banking")) {
				e.setCancelled(true);
				if(e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem() != null) {
					
					ItemStack item = (ItemStack) e.getCurrentItem();
					ItemMeta meta = (ItemMeta) item.getItemMeta();
					if(meta.getDisplayName().equals(ChatColor.DARK_GREEN+"§lTransmit all to bank")) {
						e.setCancelled(true);
						PlayerData.localToBank(p);
						p.openInventory(Phone.getBanking(p));
					}
					
					if(meta.getDisplayName().equals(ChatColor.DARK_GREEN+"§lTransmit all to purse")) {
						e.setCancelled(true);
						PlayerData.bankToLocal(p);
						p.openInventory(Phone.getBanking(p));
					}
					
					if(meta.getDisplayName().equals(ChatColor.RED+"§lBack")) {
						e.setCancelled(true);
						p.openInventory(Phone.getMoney(p));
					}
							
							
						
				}
			} else if(e.getClickedInventory().getTitle().equalsIgnoreCase("§6§lPhone")) {
				e.setCancelled(true);
				if(e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem() != null) {
					
					ItemStack item = (ItemStack) e.getCurrentItem();
					ItemMeta meta = (ItemMeta) item.getItemMeta();
					if(meta.getDisplayName().equals(ChatColor.DARK_GREEN+"§lMoney")) {
						e.setCancelled(true);
						p.openInventory(Phone.getMoney(p));
					}
					
					if(meta.getDisplayName().equals("§4§lOnline-Shopping")) {
						e.setCancelled(true);
						p.openInventory(Phone.getShopOverview(p));
					}
					
				}
			} else if(e.getClickedInventory().getTitle().equalsIgnoreCase("§4§lShop: Overview")) {
				e.setCancelled(true);
				if(e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem() != null) {
					
					ItemStack item = (ItemStack) e.getCurrentItem();
					ItemMeta meta = (ItemMeta) item.getItemMeta();
					if(meta.getDisplayName().equals(ChatColor.GRAY+"§lFood")) {
						e.setCancelled(true);
						p.openInventory(Phone.getShopFood(p));
					}
					
					if(meta.getDisplayName().equals("§4§lBack")) {
						e.setCancelled(true);
						p.openInventory(Phone.getPhone(p));
					}
					
					if(meta.getDisplayName().equals("§8§lWeapons")) {
						e.setCancelled(true);
						p.openInventory(Phone.getShopWeapons(p));
					}
					
					if(meta.getDisplayName().equals("§8§lRanks")) {
						e.setCancelled(true);
						p.openInventory(Phone.getShopRanks(p));
						
					}
					
					
					
				}
			} else if(e.getClickedInventory().getTitle().equalsIgnoreCase("§4§lShop: Food")) {
				e.setCancelled(true);
				if(e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem() != null) {
					
					ItemStack item = (ItemStack) e.getCurrentItem();
					ItemMeta meta = (ItemMeta) item.getItemMeta();
					if(meta.getDisplayName().equals(ChatColor.GREEN+"§lPotato: §850$")) {
						e.setCancelled(true);
						if(PlayerData.hasEnoughLocal(p, 50)) {
							Phone.orderItem(p, ChatColor.GREEN+"§lPotato", Material.BAKED_POTATO, 50);
						} else {
							p.sendMessage(Main.nomoney);
						}
						p.openInventory(Phone.getShopFood(p));
					}
					
					if(meta.getDisplayName().equals("§4§lBack")) {
						e.setCancelled(true);
						p.openInventory(Phone.getShopOverview(p));
					}
					
					
					
				}
			}  else if(e.getClickedInventory().getTitle().equalsIgnoreCase("§4§lShop: Weapons")) {
				e.setCancelled(true);
				if(e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem() != null) {
					
					ItemStack item = (ItemStack) e.getCurrentItem();
					ItemMeta meta = (ItemMeta) item.getItemMeta();
					if(meta.getDisplayName().equals((String) ConfigHandler.getWeaponValue("sniper", "shopname"))) {
						e.setCancelled(true);
						if(PlayerData.hasEnoughLocal(p, (int) ConfigHandler.getWeaponValue("sniper", "price"))) {
							Phone.orderItem(p, (String) ConfigHandler.getWeaponValue("sniper", "name"), Material.IRON_HOE, (int) ConfigHandler.getWeaponValue("sniper", "price"));
						} else {
							p.sendMessage(Main.nomoney);
						}
						p.openInventory(Phone.getShopWeapons(p));
					}
					
					if(meta.getDisplayName().equals((String) ConfigHandler.getWeaponValue("rocketlauncher", "shopname"))) {
						e.setCancelled(true);
						if(PlayerData.hasEnoughLocal(p, (int) ConfigHandler.getWeaponValue("rocketlauncher", "price"))) {
							Phone.orderItem(p, (String) ConfigHandler.getWeaponValue("rocketlauncher", "name"), Material.WOOD_HOE, (int) ConfigHandler.getWeaponValue("rocketlauncher", "price"));
						} else {
							p.sendMessage(Main.nomoney);
						}
						p.openInventory(Phone.getShopWeapons(p));
					}
					
					if(meta.getDisplayName().equals((String) ConfigHandler.getWeaponValue("pistol", "shopname"))) {
						e.setCancelled(true);
						if(PlayerData.hasEnoughLocal(p, (int) ConfigHandler.getWeaponValue("pistol","price"))) {
							Phone.orderItem(p, (String) ConfigHandler.getWeaponValue("pistol", "name"), Material.WOOD_AXE, (int) ConfigHandler.getWeaponValue("pistol", "price"));
						} else {
							p.sendMessage(Main.nomoney);
						}
						p.openInventory(Phone.getShopWeapons(p));
					}
					
					if(meta.getDisplayName().equals("§4§lBack")) {
						e.setCancelled(true);
						p.openInventory(Phone.getShopOverview(p));
					}
					
					
					
				} else if(e.getClickedInventory().getTitle().equalsIgnoreCase("§4§lShop: Ranks")) {
					e.setCancelled(true);
					ItemStack item = (ItemStack) e.getCurrentItem();
					ItemMeta meta = (ItemMeta) item.getItemMeta();
					for(String rank : Ranks.getRanks()) {
						if(meta.getDisplayName().equals(Ranks.getShopName(rank))) {
							e.setCancelled(true);
							Permissions.setRank(p, rank);
						}
					}
					
					if(meta.getDisplayName().equals("§4§lBack")) {
						e.setCancelled(true);
						p.openInventory(Phone.getShopOverview(p));
					}
				}
			}
		}
		
	}

}
