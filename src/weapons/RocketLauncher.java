package weapons;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Fireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import assets.ConfigHandler;
import de.marvnet.gtm.Main;



public class RocketLauncher implements Listener {

	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ArrayList<String> shotrocketlauncher = new ArrayList();
	
	@SuppressWarnings("deprecation")
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		int loadDelay = (int) ConfigHandler.getWeaponValue("rocketlauncher", "loaddelay");
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(p.getItemInHand().getItemMeta() != null) {
				if(p.getItemInHand().getItemMeta().getDisplayName().equals((String) ConfigHandler.getWeaponValue("rocketlauncher", "name"))) {
					if(!shotrocketlauncher.contains(p.getName())) {
						Fireball s = p.getWorld().spawn(p.getEyeLocation(), Fireball.class);
						s.setVelocity(p.getEyeLocation().getDirection().multiply(10));
						s.setShooter(p);
						s.setBounce(false);
						s.setGlowing(true);
						s.setGravity(true);
						s.setIsIncendiary(true);
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 1, 1);
						shotrocketlauncher.add(p.getName());
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {

							@Override
							public void run() {
								shotrocketlauncher.remove(p.getName());
								
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
		if(e.getDamager() instanceof Fireball) {
			if(e.getEntity() instanceof LivingEntity) {
				Fireball s = (Fireball) e.getDamager();
				if(s.getShooter() != null) {
					if(s.getShooter() instanceof Player) {
						Player p = (Player) s.getShooter();
						if(p.getItemInHand().getItemMeta() != null) {
							if(p.getItemInHand().getItemMeta().getDisplayName().equals(getrocketlauncher().getItemMeta().getDisplayName())) {
								e.setDamage((int) ConfigHandler.getWeaponValue("rocketlauncher", "damage"));
							}
						}
					}
				}
			}
		}
	}
	
	public ItemStack getrocketlauncher() {
		ItemStack item = new ItemStack(Material.WOOD_HOE);
		ItemMeta imeta = item.getItemMeta();
		imeta.setDisplayName((String) ConfigHandler.getWeaponValue("rocketlauncher", "name"));
		item.setItemMeta(imeta);
		return item;
	}
}
