package assets;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.configuration.file.YamlConfiguration;

public class Ranks {
	
	private static ArrayList<String> ranks = new ArrayList<String>();
	
	public static ArrayList<String> getRanks() {
		ranks.clear();
		ranks.add("HOBO");
		ranks.add("GODFATHER");
		
		return ranks;
	}
	
	public static String getShopName(String rank) {
		File rankFile = new File("plugins/GTM/ranks.yml");
		YamlConfiguration ranks = YamlConfiguration.loadConfiguration(rankFile);
		return ranks.getString(rank+".shopname");
	}
	
	public static int getPrice(String rank) {
		File rankFile = new File("plugins/GTM/ranks.yml");
		YamlConfiguration ranks = YamlConfiguration.loadConfiguration(rankFile);
		return ranks.getInt(rank+".price");
	}

	public static String getRankPrefix(String rank) {
		File rankFile = new File("plugins/GTM/ranks.yml");
		YamlConfiguration ranks = YamlConfiguration.loadConfiguration(rankFile);
		return ranks.getString(rank+".prefix");
	}
}
