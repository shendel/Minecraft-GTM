package events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


import assets.PlayerData;
import assets.Ranks;

public class ChatEvent implements Listener {
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = (Player) e.getPlayer();
		if(PlayerData.isSpecial(p)) {
			e.setFormat(Ranks.getRankPrefix(PlayerData.getSpecialRank(p))+PlayerData.getSpecialRank(p)+"§f> "+Ranks.getRankPrefix(PlayerData.getRank(p))+PlayerData.getRank(p)+" "+PlayerData.getValue(p, "prefix.player")+p.getDisplayName()+"§f: "+PlayerData.getValue(p, "prefix.message")+e.getMessage());
		} else {
			e.setFormat(Ranks.getRankPrefix(PlayerData.getRank(p))+PlayerData.getRank(p)+" "+PlayerData.getValue(p,"prefix.player")+p.getDisplayName()+"§f: "+PlayerData.getValue(p,"prefix.message")+e.getMessage());
		}
		
	}
}
