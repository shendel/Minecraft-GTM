package commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import assets.troll.Utils;
import assets.troll.Var;
import de.marvnet.gtm.Main;

public class Troll implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("troll")) {
			if(args.length == 0) {
				
				if(sender.hasPermission("gtm.troll")) {
					for(String s : Var.getTrollHelp()) {
						sender.sendMessage(Main.prefix+s);
					}
				} else {
					sender.sendMessage(Main.noperm);
				}
				
			} else if(args.length == 2) {
				
				if(args[0] == "crash") {
					try {
						if(sender.hasPermission("gtm.troll")) {
							Player p = Bukkit.getPlayer(args[1]);
							Utils.crash(p);
							sender.sendMessage(Main.trollpre+"§aCrashed §2"+p.getName()+"§a!");
						} else {
							sender.sendMessage(Main.noperm);
						}
					} catch(Exception e) {
						sender.sendMessage(Main.unhandledError);
					}
					
				}
				
			} else {
				sender.sendMessage(Main.prefix+"§cInvalid arguments!");
			}
		} else {
			sender.sendMessage(Main.unhandledError);
		}
		
		return false;
	}

}
