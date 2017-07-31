package events;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import assets.Disguise;
import de.marvnet.gtm.Main;

public class DisguiseEvent implements Listener {

	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			e.setCancelled(true);
			ItemMeta iteminhandmeta = p.getItemInHand().getItemMeta();
			String iteminhand = iteminhandmeta.getDisplayName();
			if(iteminhand.equals("§8Disguise") && p.getItemInHand().getType().equals(Material.CHAINMAIL_CHESTPLATE)) {
				e.setCancelled(true);
				p.openInventory(Disguise.getGUI(p));
				
			}
			
			
			
			
		}
		
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getWhoClicked() instanceof Player) {
			Player p = (Player) e.getWhoClicked();
			if(e.getClickedInventory().getTitle().equalsIgnoreCase("§8§lDisguise")) {
				e.setCancelled(true);
				if(e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem() != null) {
						
					ItemStack item = (ItemStack) e.getCurrentItem();
					ItemMeta meta = (ItemMeta) item.getItemMeta();
					if(meta.getDisplayName().equals("§4§lRabbit")) {
						Disguise.disguiseRabbit(p);
						p.sendMessage(Main.prefix+"§aYou were disguised as a §4rabbit§a!");
					}
					
					if(meta.getDisplayName().equals("§c§lUndisguise")) {
						if(Disguise.isDisguised(p)) {
							Disguise.unDisguise(p);
							p.sendMessage(Main.prefix+"§aYou were §cundisguised§a!");
						} else {
							p.sendMessage(Main.prefix+"§cYou aren't disguised!");
						}
						
					}
							
							
						
				}
			}
		}
	}
}
