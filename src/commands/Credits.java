package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.marvnet.gtm.Main;

public class Credits implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command command, String arg2, String[] arg3) {
		
		for(String info : Main.aboutPlugin) {
			arg0.sendMessage(Main.prefix+info);
		}
		return false;
	}

}
