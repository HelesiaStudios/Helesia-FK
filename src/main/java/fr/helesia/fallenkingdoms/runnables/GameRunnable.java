package fr.helesia.fallenkingdoms.runnables;

import java.text.SimpleDateFormat;
import java.util.Date;

import fr.helesia.fallenkingdoms.GameStatus;
import fr.helesia.fallenkingdoms.Main;
import fr.helesia.fallenkingdoms.game.HearthManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import fr.helesia.fallenkingdoms.scoreboard.ScoreboardManager;

public class GameRunnable extends BukkitRunnable {
	
	public static int timer = 0;
	public static int day = 1;

	@Override
	public void run() {
		timer++;
		
		if (timer == 1200) { // après 20 minutes
			day++;
			timer = 0;
			Bukkit.broadcastMessage(Main.getINSTANCE().getPrefix() + "§bDébut du jour §6" + day + "§b.");
			return;
		}
		
		// activation du pvp
		if (day == 1) {
			if (!Main.getINSTANCE().pvp) {
				if (timer == 60) {
					Main.getINSTANCE().pvp = true;
					Bukkit.getWorld("world").setPVP(true);
					Bukkit.broadcastMessage(Main.getINSTANCE().getPrefix() + "§fLe pvp est désormais §aactivé§f.");
					return;
				}
			}
		}
		
		if (day == 2) {
			if (!Main.getINSTANCE().assaut) {
				Main.getINSTANCE().assaut = true;
				Bukkit.broadcastMessage(Main.getINSTANCE().getPrefix() + "§fLes assauts sont désormais §aactivés§f.");
				GameStatus.setStatus(GameStatus.ASSAUTS);
				return;
			}
		}
		
		if (day == 3) {
			if (!Main.getINSTANCE().deathmatch) {
				Main.getINSTANCE().deathmatch = true;
				GameStatus.setStatus(GameStatus.DEATHMATCH);
				Bukkit.broadcastMessage(Main.getINSTANCE().getPrefix() + "§cDébut du deathmatch.");
				Bukkit.getOnlinePlayers().forEach(players -> {
					players.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 2));
				});
				return;
			}
		}
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (ScoreboardManager.scoreboardGame.containsKey(player)) {
				ScoreboardManager.scoreboardGame.get(player).setLine(8, "§fJour: §a" + GameRunnable.day);
				ScoreboardManager.scoreboardGame.get(player).setLine(7, ChatColor.WHITE + "Temps " + ChatColor.YELLOW + new SimpleDateFormat("mm:ss").format(new Date(GameRunnable.timer * 1000)));
				ScoreboardManager.scoreboardGame.get(player).setLine(6, ChatColor.WHITE + "Kills: " + ChatColor.RED + player.getStatistic(Statistic.PLAYER_KILLS));
				if (HearthManager.enderCrystals.get("rouge") != null) ScoreboardManager.scoreboardGame.get(player).setLine(5, ChatColor.WHITE + "Rouge: " + ChatColor.RED + HearthManager.enderCrystals.get("rouge").getLife() + ".0");
				if (HearthManager.enderCrystals.get("bleu") != null) ScoreboardManager.scoreboardGame.get(player).setLine(4, ChatColor.WHITE + "Bleu: " + ChatColor.RED + HearthManager.enderCrystals.get("bleu").getLife() + ".0");
				ScoreboardManager.scoreboardGame.get(player).setLine(2, Main.getINSTANCE().pvp ? "§fPVP: §a✓" : "§fPVP: §c✗");
				ScoreboardManager.scoreboardGame.get(player).setLine(1, Main.getINSTANCE().assaut ? "§fAssauts: §a✓" : "§fAssauts: §c✗");
			}
		}
	}
}