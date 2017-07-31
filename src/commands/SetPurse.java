package commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import assets.PlayerData;
import de.marvnet.gtm.Main;

public class SetPurse implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(command.getName().equalsIgnoreCase("setpurse")) {
			if(args.length == 2) {
				if(sender.hasPermission("gtm.setmoney")) {
					int money = Integer.parseInt(args[1]);
					OfflinePlayer pa = Bukkit.getPlayer(args[0]);
					if(pa.isOnline()) {
						Player p = Bukkit.getPlayer(args[0]);
						PlayerData.setLocal(p, money);
						sender.sendMessage(Main.prefix+"§aThe purse of §2"+p.getName()+"§a was set to §2"+args[1]+"$§a!");
					} else {
						sender.sendMessage(Main.unhandledError);
					}
				} else {
					sender.sendMessage(Main.noperm);
				}
			} else {
				sender.sendMessage(Main.prefix+"§cSyntax: /setmoney <Player> <Money>");
			}
		}
		
		
		return false;
	}

}
