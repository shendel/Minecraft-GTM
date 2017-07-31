package commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.marvnet.gtm.Main;
import gui.Phone;

public class Items implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("items")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				p.getInventory().addItem(Phone.createItem("§6§lPhone",Material.COMPASS));
				p.sendMessage(Main.prefix+"§aYou've received the Main-Items!");
			}
		}
		return false;
	}

}
