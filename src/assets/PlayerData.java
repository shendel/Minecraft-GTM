package assets;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.marvnet.gtm.Main;

public class PlayerData {

	public static YamlConfiguration getConfig(Player p) {
		File playerFile = new File("plugins/GTM/player/"+p.getUniqueId()+".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(playerFile);
		return cfg;
	}
	
	public static File getConfigFile(Player p) {
		File playerFile = new File("plugins/GTM/player/"+p.getUniqueId()+".yml");
		return playerFile;
	}
	
	public static void init(Player p) {
		File playerFile = new File("plugins/GTM/player/"+p.getUniqueId()+".yml");
		if(!playerFile.exists() == true) {
			try {
				playerFile.createNewFile();
				createDefaultValues(p);
				p.sendMessage(Main.prefix+"§aYou've received §2$"+ConfigHandler.getConfigValue("default.money")+"§a from the state!");
				Bukkit.getConsoleSender().sendMessage(Main.prefix+"§aCreated player file for '"+p.getName()+"'!");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	

	private static void createDefaultValues(Player p) {
		YamlConfiguration cfg = getConfig(p);
		cfg.set("money.bank", 0);
		cfg.set("money.local", ConfigHandler.getConfigValue("default.money"));
		cfg.set("rank.name", ConfigHandler.getConfigValue("default.rank"));
		cfg.set("prefix.player", ConfigHandler.getConfigValue("default.prefix.player"));
		cfg.set("prefix.message", ConfigHandler.getConfigValue("default.prefix.message"));
		cfg.set("special.hasrank", false);
		cfg.set("special.rank", "");
		
		try {
			cfg.save(getConfigFile(p));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static int getBank(Player p) {
		File playerFile = new File("plugins/GTM/player/"+p.getUniqueId()+".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(playerFile);
		return cfg.getInt("money.bank");
	}
	
	public static int getLocal(Player p) {
		File playerFile = new File("plugins/GTM/player/"+p.getUniqueId()+".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(playerFile);
		return cfg.getInt("money.local");
	}
	
	public static void resetBank(Player p) {
		File playerFile = new File("plugins/GTM/player/"+p.getUniqueId()+".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(playerFile);
		cfg.set("money.bank", 0);
		try {
			cfg.save(playerFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void resetLocal(Player p) {
		File playerFile = new File("plugins/GTM/player/"+p.getUniqueId()+".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(playerFile);
		cfg.set("money.local", 0);
		try {
			cfg.save(playerFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addBank(Player p, int money) {
		File playerFile = new File("plugins/GTM/player/"+p.getUniqueId()+".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(playerFile);
		int moneya = cfg.getInt("money.bank");
		int summe = Integer.sum(moneya, money);
		cfg.set("money.bank", summe);
		try {
			cfg.save(playerFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void minusBank(Player p, int money) {
		File playerFile = new File("plugins/GTM/player/"+p.getUniqueId()+".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(playerFile);
		int moneya = cfg.getInt("money.bank");
		int summe = moneya - money;
		cfg.set("money.bank", summe);
		try {
			cfg.save(playerFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addLocal(Player p, int money) {
		File playerFile = new File("plugins/GTM/player/"+p.getUniqueId()+".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(playerFile);
		int moneya = cfg.getInt("money.local");
		int summe = Integer.sum(moneya, money);
		cfg.set("money.local", summe);
		try {
			cfg.save(playerFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void minusLocal(Player p, int money) {
		File playerFile = new File("plugins/GTM/player/"+p.getUniqueId()+".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(playerFile);
		int moneya = cfg.getInt("money.local");
		int summe = moneya - money;
		cfg.set("money.local", summe);
		try {
			cfg.save(playerFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void localToBank(Player p) {
		addBank(p, getLocal(p));
		int moneylocal = getLocal(p);
		resetLocal(p);
		p.sendMessage(Main.prefix+"§aTransmitted §2"+moneylocal+"$ §ato your Bank!");
	}
	
	public static void bankToLocal(Player p) {
		addLocal(p, getBank(p));
		int moneylocal = getBank(p);
		resetBank(p);
		p.sendMessage(Main.prefix+"§aTransmitted §2"+moneylocal+"$ §ato your purse!");
	}

	public static String getRank(Player p) {
		File playerFile = new File("plugins/GTM/player/"+p.getUniqueId()+".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(playerFile);	
		return cfg.getString("rank.name");
	}

	public static Object getValue(Player p,String string) {
		File playerFile = new File("plugins/GTM/player/"+p.getUniqueId()+".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(playerFile);
		return cfg.get(string);
	}
	
	
	public static boolean hasEnoughLocal(Player p, int money) {
		File playerFile = new File("plugins/GTM/player/"+p.getUniqueId()+".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(playerFile);
		int currmoney = cfg.getInt("money.local");
		if(currmoney >= money) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean hasEnoughBank(Player p, int money) {
		File playerFile = new File("plugins/GTM/player/"+p.getUniqueId()+".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(playerFile);
		int currmoney = cfg.getInt("money.bank");
		if(currmoney >= money) {
			return true;
		} else {
			return false;
		}
	}

	public static void setLocal(Player p, int money) {
		File playerFile = new File("plugins/GTM/player/"+p.getUniqueId()+".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(playerFile);
		cfg.set("money.local", money);
		try {
			cfg.save(playerFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void setbank(Player p, int money) {
		File playerFile = new File("plugins/GTM/player/"+p.getUniqueId()+".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(playerFile);
		cfg.set("money.bank", money);
		try {
			cfg.save(playerFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean isSpecial(Player p) {
		File playerFile = new File("plugins/GTM/player/"+p.getUniqueId()+".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(playerFile);
		return cfg.getBoolean("special.hasrank");
	}
	
	public static String getSpecialRank(Player p) {
		File playerFile = new File("plugins/GTM/player/"+p.getUniqueId()+".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(playerFile);
		return cfg.getString("special.rank");
	}
}
