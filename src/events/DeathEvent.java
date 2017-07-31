package events;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import assets.ConfigHandler;
import assets.PlayerData;
import de.marvnet.gtm.Main;

public class DeathEvent implements Listener {
	
	
	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if(e.getEntityType().equals(EntityType.PLAYER) && e.getEntity().getKiller().getType().equals(EntityType.PLAYER)) {
			PlayerData.addLocal(e.getEntity().getKiller(), (PlayerData.getLocal((Player) e.getEntity())/2));
			if((boolean) ConfigHandler.getConfigValue("messages.killmessage.send") == true) {
				String killmessage = (String) ConfigHandler.getConfigValue("messages.killmessage.message");
				killmessage = String.format("victim", e.getEntity().getName());
				killmessage = String.format("money", (PlayerData.getLocal((Player) e.getEntity())/2));
				
				e.getEntity().getKiller().sendMessage(Main.prefix+killmessage);
			}
		} else {
			if(e.getEntity().getKiller().getType().equals(EntityType.PLAYER)) {
				PlayerData.addLocal(e.getEntity().getKiller(), 200);
				if((boolean) ConfigHandler.getConfigValue("messages.killmessage.mobkill.send") == true) {
					String killmessage = (String) ConfigHandler.getConfigValue("messages.killmessage.mobkill.message");
					
					
					e.getEntity().getKiller().sendMessage(Main.prefix+killmessage);
				}
			}
		}
	}
}
