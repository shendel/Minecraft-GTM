package assets.permissions;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Permissions {
	
	
	public static void addPermission(Player p, String permission) {
		PermissionUser pexp = PermissionsEx.getUser(p);
		pexp.addPermission(permission);
	}
	
	public static void removePermission(Player p, String permission) {
		PermissionUser pexp = PermissionsEx.getUser(p);
		pexp.removePermission(permission);
	}

	@SuppressWarnings("deprecation")
	public static void setRank(Player p,String rank) {
		File playerFile = new File("plugins/GTM/player/"+p.getUniqueId()+".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(playerFile);
		PermissionUser pexp = PermissionsEx.getUser(p);
		if(cfg.getBoolean("special.hasrank")) {
			for(String group : pexp.getGroupNames()) {
				pexp.removeGroup(group);
			}
			pexp.addGroup(rank);
			pexp.addGroup(cfg.getString("special.rank"));
			
		} else {
			for(String group : pexp.getGroupNames()) {
				pexp.removeGroup(group);
			}
			pexp.addGroup(rank);
		}
		cfg.set("rank", rank);
		try {
			cfg.save(playerFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
