package assets;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class Sidebar implements Listener {

	public static void updateSidebar(Player p) {
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = board.registerNewObjective("aaa", "bbb");
		
		obj.setDisplayName("§8§l>>>§3§lGTM§8§l<<<");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		Score purse = obj.getScore("§f"+Integer.toString(PlayerData.getLocal(p))+"$");
		Score bank = obj.getScore("§f"+Integer.toString(PlayerData.getBank(p))+"$");
		Score pursetit = obj.getScore("§3§lPurse:");
		Score banktit = obj.getScore("§3§lBank:");
		Score ranktit = obj.getScore("§6§lRank:");
		String rankpre = Ranks.getRankPrefix(PlayerData.getRank(p));
		Score rank = obj.getScore(rankpre+PlayerData.getRank(p));
		Score empty1 = obj.getScore("§8§lScores:");
		Score empty2 = obj.getScore("===========");
		Score empty3 = obj.getScore("");
		
		empty1.setScore(8);
		ranktit.setScore(7);
		rank.setScore(6);
		empty2.setScore(5);
		pursetit.setScore(4);
		purse.setScore(3);
		empty3.setScore(2);
		banktit.setScore(1);
		bank.setScore(0);
		
		p.setScoreboard(board);
	}
}	
