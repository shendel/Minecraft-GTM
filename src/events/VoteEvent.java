package events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.vexsoftware.votifier.model.VotifierEvent;

import assets.PlayerData;
import de.marvnet.gtm.Main;

public class VoteEvent implements Listener {
	
	@EventHandler
	public void onVote(VotifierEvent e) {
		Player p = (Player) Bukkit.getPlayer(e.getVote().getUsername());
		String website = e.getVote().getServiceName();
		
		Bukkit.broadcast(Main.prefix+"§2§l"+p.getName()+"§a has voted via §2"+website+"§a!","gtm.main");
		PlayerData.addLocal(p, 2000);
		p.sendMessage(Main.prefix+"§aThank you for voting via §2"+website+"§a! §aYou've received your voting reward!");
	
	}
	
}
