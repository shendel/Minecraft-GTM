package weapons;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import assets.ConfigHandler;
import de.marvnet.gtm.Main;



public class Pistol implements Listener {

	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ArrayList<String> shotpistol = new ArrayList();
	
	@SuppressWarnings("deprecation")
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		int loadDelay = (int) ConfigHandler.getWeaponValue("pistol", "loaddelay");
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(p.getItemInHand().getItemMeta() != null) {
				if(p.getItemInHand().getItemMeta().getDisplayName().equals((String) ConfigHandler.getWeaponValue("pistol", "name"))) {
					if(!shotpistol.contains(p.getName())) {
						Arrow s = p.getWorld().spawn(p.getEyeLocation(), Arrow.class);
						s.setVelocity(p.getEyeLocation().getDirection().multiply(10));
						s.setShooter(p);
						s.setBounce(false);
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 1, 1);
						shotpistol.add(p.getName());
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {

							@Override
							public void run() {
								shotpistol.remove(p.getName());
								
							}
							
						}, loadDelay*20);
					} else {
						p.sendMessage(Main.weaponLoad);
					}
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Arrow) {
			if(e.getEntity() instanceof LivingEntity) {
				Arrow s = (Arrow) e.getDamager();
				if(s.getShooter() != null) {
					if(s.getShooter() instanceof Player) {
						Player p = (Player) s.getShooter();
						if(p.getItemInHand().getItemMeta() != null) {
							if(p.getItemInHand().getItemMeta().getDisplayName().equals(getPistol().getItemMeta().getDisplayName())) {
								e.setDamage((int) ConfigHandler.getWeaponValue("pistol", "damage"));
							}
						}
					}
				}
			}
		}
	}
	
	public ItemStack getPistol() {
		ItemStack item = new ItemStack(Material.WOOD_AXE);
		ItemMeta imeta = item.getItemMeta();
		imeta.setDisplayName((String) ConfigHandler.getWeaponValue("pistol", "name"));
		item.setItemMeta(imeta);
		return item;
	}
}
