package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import assets.Disguise;

public class Vanish implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("gtm.cheat.disguise")) {
				p.getInventory().addItem(Disguise.getItem(p));
			}
		}
		return false;
	}
	
}
