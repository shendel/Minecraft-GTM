package assets.troll;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class Var {


	public static ArrayList<Player> playersTrollmode = new ArrayList<>();
	public static ArrayList<String> trollHelp = new ArrayList<>();
	
	
	public static ArrayList<String> getTrollHelp() {
		trollHelp.clear();
		trollHelp.add("§c/gtm troll crash <Player>");
		return trollHelp;
	}
}
