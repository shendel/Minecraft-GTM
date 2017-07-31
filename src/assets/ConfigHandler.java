package assets;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import de.marvnet.gtm.Main;

public class ConfigHandler {
	
	public static void initFiles() {
		File mainDir = new File("plugins/GTM");
		if(mainDir.exists()) {
			if(!mainDir.isDirectory()) {
				mainDir.delete();
				mainDir.mkdir();
				Bukkit.getConsoleSender().sendMessage(Main.prefix+"§aCreated 'GTM' directory!");
			}
		} else {
			mainDir.mkdir();
		}
		
		
		File playerDir = new File("plugins/GTM/player");
		if(playerDir.exists()) {
			if(!playerDir.isDirectory()) {
				playerDir.delete();
				playerDir.mkdir();
				Bukkit.getConsoleSender().sendMessage(Main.prefix+"§aCreated 'GTM/player' directory!");
			}
		} else {
			playerDir.mkdir();
		}
		
		File configFile = new File("plugins/GTM/config.yml");
		if(!configFile.exists()) {
			try {
				configFile.createNewFile();
				setConfigDefaults();
				Bukkit.getConsoleSender().sendMessage(Main.prefix+"§aCreated config file!");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		File rankFile = new File("plugins/GTM/ranks.yml");
		if(!rankFile.exists()) {
			try {
				rankFile.createNewFile();
				setRankDefaults();
				Bukkit.getConsoleSender().sendMessage(Main.prefix+"§aCreated ranks file!");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		File weaponFile = new File("plugins/GTM/weapons.yml");
		if(!weaponFile.exists()) {
			try {
				weaponFile.createNewFile();
				setWeaponDefaults();
				Bukkit.getConsoleSender().sendMessage(Main.prefix+"§aCreated weapon file!");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private static void setRankDefaults() {
		File rankFile = new File("plugins/GTM/ranks.yml");
		YamlConfiguration ranks = YamlConfiguration.loadConfiguration(rankFile);
		ranks.set("HOBO.name", "HOBO");
		ranks.set("HOBO.prefix", "§6§l");
		ranks.set("HOBO.pexname", "hobo");
		ranks.set("HOBO.shopname", "§6§lHOBO§f: §1000$");
		ranks.set("HOBO.price", 1000);
		
		ranks.set("GODFATHER.name", "GODFATHER");
		ranks.set("GODFATHER.prefix", ChatColor.DARK_PURPLE+"§l");
		ranks.set("GODFATHER.pexname", "godfather");
		ranks.set("GODFATHER.shopname", ChatColor.DARK_PURPLE+"§lGODFATHER§f: §810000$");
		ranks.set("GODFATHER.price", 10000);
		
		
		
		try {
			ranks.save(rankFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void setWeaponDefaults() {
		File weaponFile = new File("plugins/GTM/weapons.yml");
		YamlConfiguration weapons = YamlConfiguration.loadConfiguration(weaponFile);
		weapons.set("sniper.name", "§3Sniper");
		weapons.set("sniper.damage", 14);
		weapons.set("sniper.shopname", "§3Sniper§f: §82500$");
		weapons.set("sniper.price", 2500);
		weapons.set("sniper.loaddelay", 5);
		
		weapons.set("rocketlauncher.name", "§4Rocketlauncher");
		weapons.set("rocketlauncher.damage", 25);
		weapons.set("rocketlauncher.shopname", "§4Rocketlauncher§f: §810000$");
		weapons.set("rocketlauncher.price", 10000);
		weapons.set("rocketlauncher.loaddelay", 10);
		
		weapons.set("pistol.name", "§8Pistol");
		weapons.set("pistol.damage", 4);
		weapons.set("pistol.shopname", "§8Pistol§f: §81000$");
		weapons.set("pistol.price", 1000);
		weapons.set("pistol.loaddelay", 1);
		
		try {
			weapons.save(weaponFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Object getWeaponValue(String weapon, String property) {
		File weaponFile = new File("plugins/GTM/weapons.yml");
		YamlConfiguration weapons = YamlConfiguration.loadConfiguration(weaponFile);
		return weapons.get(weapon+"."+property);
	}

	public static Object getConfigValue(String property) {
		File configFile = new File("plugins/GTM/config.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(configFile);
		return cfg.get(property);
	}
	
	public static Object getConfigValueString(String property) {
		File configFile = new File("plugins/GTM/config.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(configFile);
		return cfg.getString(property);
	}

	private static void setConfigDefaults() {
		File configFile = new File("plugins/GTM/config.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(configFile);
		cfg.set("test.test", "yeah");
		cfg.set("default.money", 3500);
		cfg.set("plugin.prefix", "§f[§8GTM§f] ");
		cfg.set("default.rank", "HOBO");
		cfg.set("default.prefix.player", "§8");
		cfg.set("default.prefix.message", "§f");
		cfg.set("messages.killmessage.message", "§aFor the kill on %s(victim) you received %s(money)!");
		cfg.set("messages.killmessage.send", true);
		cfg.set("messages.killmessage.mobkill.message", "§aFor the kill on a Mob, you received $200!");
		cfg.set("messages.killmessage.mobkill.send", true);
		cfg.set("messages.nomoney.message", "§cNot enough money!");
		cfg.set("messages.order.orderdelivered.message", ChatColor.YELLOW+"Your order is delivered!");
		cfg.set("messages.order.orderwait.message", ChatColor.YELLOW+"Please wait 5 seconds...");
		cfg.set("messages.unhandlederror.message", "§cAn unknown error occured!");
		cfg.set("messages.nopermission.message", "§cYou do not have a permission to do that!");
		cfg.set("messages.weapon.load.message", "§cThis weapon is loading!");
		cfg.set("order.waitdelay", 5);
		
		try {
			cfg.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
