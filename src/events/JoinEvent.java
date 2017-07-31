package events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import assets.PlayerData;
import assets.Ranks;
import assets.Sidebar;
import assets.TexturePack;
import de.marvnet.gtm.Main;
import gui.Phone;
import me.confuser.barapi.BarAPI;


public class JoinEvent implements Listener {

	
	private int sched;

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		e.setJoinMessage(null);
		BarAPI.setMessage(p,"§aWelcome to §8GTM§a, "+Ranks.getRankPrefix(PlayerData.getRank(p))+(String) PlayerData.getRank(p)+p.getName()+"§a!", 20);
		Bukkit.broadcast(Main.prefix+"§8"+e.getPlayer().getDisplayName()+" §fhas joined the game!", "gtm.main");
		PlayerData.init(e.getPlayer());
		TexturePack.sendGTM(e.getPlayer());
		Sidebar.updateSidebar(p);
		
		
		if(!Bukkit.getScheduler().isCurrentlyRunning(sched)) {
			sched = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
				@Override
				public void run() {
					Sidebar.updateSidebar(p);
				}
			}, 20, 20);
		}
		
		e.getPlayer().getInventory().addItem(Phone.createItem("§6§lPhone",Material.COMPASS));
		e.getPlayer().sendMessage(Main.prefix+"§aYour rank is: "+Ranks.getRankPrefix(PlayerData.getRank(e.getPlayer()))+PlayerData.getRank(e.getPlayer()));
	}
}
