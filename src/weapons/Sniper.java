package weapons;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import assets.ConfigHandler;
import de.marvnet.gtm.Main;



public class Sniper implements Listener {

	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ArrayList<String> shotSniper = new ArrayList();
	
	@SuppressWarnings("deprecation")
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		int loadDelay = (int) ConfigHandler.getWeaponValue("sniper", "loaddelay");
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(p.getItemInHand().getItemMeta() != null) {
				if(p.getItemInHand().getItemMeta().getDisplayName().equals((String) ConfigHandler.getWeaponValue("sniper", "name"))) {
					if(!shotSniper.contains(p.getName())) {
						Snowball s = p.getWorld().spawn(p.getEyeLocation(), Snowball.class);
						s.setVelocity(p.getEyeLocation().getDirection().multiply(10));
						s.setShooter(p);
						s.setBounce(false);
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 1, 1);
						shotSniper.add(p.getName());
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {

							@Override
							public void run() {
								shotSniper.remove(p.getName());
								
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
		if(e.getDamager() instanceof Snowball) {
			if(e.getEntity() instanceof LivingEntity) {
				Snowball s = (Snowball) e.getDamager();
				if(s.getShooter() != null) {
					if(s.getShooter() instanceof Player) {
						Player p = (Player) s.getShooter();
						if(p.getItemInHand().getItemMeta() != null) {
							if(p.getItemInHand().getItemMeta().getDisplayName().equals(getSniper().getItemMeta().getDisplayName())) {
								e.setDamage((int) ConfigHandler.getWeaponValue("sniper", "damage"));
							}
						}
					}
				}
			}
		}
	}
	
	public ItemStack getSniper() {
		ItemStack item = new ItemStack(Material.IRON_HOE);
		ItemMeta imeta = item.getItemMeta();
		imeta.setDisplayName((String) ConfigHandler.getWeaponValue("sniper", "name"));
		item.setItemMeta(imeta);
		return item;
	}
}
