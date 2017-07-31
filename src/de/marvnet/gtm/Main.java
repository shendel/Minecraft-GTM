package de.marvnet.gtm;



import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import assets.ConfigHandler;
import commands.Credits;
import commands.Items;
import commands.SetPurse;
import commands.Troll;
import commands.Vanish;
import de.robingrether.idisguise.api.DisguiseAPI;
import events.ChatEvent;
import events.DeathEvent;
import events.DisguiseEvent;
import events.JoinEvent;
import events.PhoneEvent;
import events.VoteEvent;
import weapons.Pistol;
import weapons.RocketLauncher;
import weapons.Sniper;


@SuppressWarnings({ "unchecked", "rawtypes" })
public class Main extends JavaPlugin {

	public static Main plugin;
	
	
	public static ArrayList<String> pluginDependencies = new ArrayList();
	public static ArrayList<String> aboutPlugin = new ArrayList();
	public static DisguiseAPI disguiseapi;
	
	public static String prefix = (String) ConfigHandler.getConfigValueString("plugin.prefix"),
			noperm = prefix + (String) ConfigHandler.getConfigValueString("messages.nopermission.message"),
			nomoney = prefix + (String) ConfigHandler.getConfigValueString("messages.nomoney.message"),
			orderWaitMessage = prefix + (String) ConfigHandler.getConfigValueString("messages.order.orderwait.message"),
			orderDelivered = prefix + (String) ConfigHandler.getConfigValueString("messages.order.orderdelivered.message"),
			unhandledError = prefix + (String) ConfigHandler.getConfigValueString("messages.unhandlederror.message"),
			weaponLoad = prefix + (String) ConfigHandler.getConfigValueString("messages.weapon.load.message"),
			trollpre = prefix + "§f[§4TROLL§f] ";

	
	public static boolean servermuted = false;


	
	
	
	public void onEnable() {
		plugin = this;
		
		
		
		
		ConfigHandler.initFiles();
		Bukkit.getConsoleSender().sendMessage(prefix+"§aDas Plugin wird aktiviert...");
		
		PluginManager pm = Bukkit.getPluginManager();
		
		pluginDependencies.add("PermissionsEx");
		pluginDependencies.add("Votifier");
		pluginDependencies.add("BarAPI");
		// pluginDependencies.add("TabAPI");
		pluginDependencies.add("iDisguise");
		// pluginDependencies.add("GameAPI");
		// pluginDependencies.add("HoloAPI");
		
		aboutPlugin.clear();
		
		aboutPlugin.add("§aGTM-Plugin: GTA in Minecraft -- coded by MarvNet");
		aboutPlugin.add("§aMain plugin coded by MarvMan");
		aboutPlugin.add("§aPlugin §crequires:");
		for(String plugin : pluginDependencies) {
			aboutPlugin.add("§a- "+plugin);
		}
		aboutPlugin.add("§aFeatures: Many weapons in Minecraft! Texture Pack! Voting Rewards! Shopping!");
		aboutPlugin.add("§aWebsite: marvnet.de/java/gtm");
		
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {

			@Override
			public void run() {
				if(checkPlugin(checkPluginDependencies(pluginDependencies))) {
					Bukkit.getConsoleSender().sendMessage(Main.prefix+"§3Plugin: §f[§2OK§f]");
				} else {
					Bukkit.getConsoleSender().sendMessage(Main.prefix+"§3Plugin: §f[§4FAILED§f]");
				}

				disguiseapi = getServer().getServicesManager().getRegistration(DisguiseAPI.class).getProvider();
				Bukkit.getConsoleSender().sendMessage(prefix+"§aDas Plugin wurde aktiviert!");
				
			}
			
		}, 1*20);
		
		
		
		
		this.getCommand("items").setExecutor(new Items());
		this.getCommand("credits").setExecutor(new Credits());
		this.getCommand("setpurse").setExecutor(new SetPurse());
		this.getCommand("troll").setExecutor(new Troll());
		this.getCommand("vanish").setExecutor(new Vanish());
		
		pm.registerEvents(new JoinEvent(), this);
		pm.registerEvents(new PhoneEvent(), this);
		pm.registerEvents(new ChatEvent(), this);
		pm.registerEvents(new VoteEvent(), this);
		pm.registerEvents(new DeathEvent(), this);
		pm.registerEvents(new Sniper(), this);
		pm.registerEvents(new RocketLauncher(), this);
		pm.registerEvents(new Pistol(), this);
		pm.registerEvents(new DisguiseEvent(), this);
		
		Bukkit.getConsoleSender().sendMessage(prefix+"§aDas Plugin wurde aktiviert!");
	}
	
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(prefix+"§cDas Plugin wurde deaktiviert!");
	}
	
	public boolean checkPluginDependencies(ArrayList<String> dependencies) {
		boolean correct = true;
		for(String plugin : dependencies) {
			if(Bukkit.getPluginManager().isPluginEnabled(plugin)) {
				Bukkit.getConsoleSender().sendMessage(Main.prefix+"§aDetected §2"+plugin+"§a!");
			} else {
				correct = false;
				Bukkit.getConsoleSender().sendMessage(Main.prefix+"§cPlugin dependency §4"+plugin+"§c was not found, but is needed! Please install!");
			}
		}
		return correct;
	}
	
	public boolean checkPlugin(boolean dependencies) {
		if(dependencies == true) {
			return true;
		} else {
			return false;
		}
	}
	
}
	
	
