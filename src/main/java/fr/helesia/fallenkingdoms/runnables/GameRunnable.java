package fr.helesia.fallenkingdoms.runnables;

import java.text.SimpleDateFormat;
import java.util.Date;

import fr.helesia.fallenkingdoms.GameStatus;
import fr.helesia.fallenkingdoms.Main;
import fr.helesia.fallenkingdoms.game.HearthManager;
import fr.helesia.fallenkingdoms.scoreboard.ScoreboardSign;
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
					Bukkit.broadcastMessage(Main.getINSTANCE().getPrefix() + "§fLe PVP est désormais §aactif§f.");
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
				Bukkit.broadcastMessage(Main.getINSTANCE().getPrefix() + "§fTout les joueurs reçoit une ender pearl !");
				return;
			}
		}
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (ScoreboardManager.scoreboardGame.containsKey(player)) {
				ScoreboardManager.scoreboardGame.get(player).setLine(12, "§8FK ┃ fk-1");
				ScoreboardManager.scoreboardGame.get(player).setLine(11, "§f§k");
				ScoreboardManager.scoreboardGame.get(player).setLine(10, "§8➵ §7Equipe: §f" + Main.getINSTANCE().getTeam(player));
				if(Main.getINSTANCE().getTeam(player).equalsIgnoreCase("§cRouge")){
					ScoreboardManager.scoreboardGame.get(player).setLine(9, "§8➵ §7Ton nexus: §f" + HearthManager.enderCrystals.get("rouge").getLife()+"§c❤");
				} else if(Main.getINSTANCE().getTeam(player).equalsIgnoreCase("§9Bleu")){
					ScoreboardManager.scoreboardGame.get(player).setLine(9, "§8➵ §7Ton nexus: §f" + HearthManager.enderCrystals.get("bleu").getLife()+"§c❤");
				} else if(Main.getINSTANCE().getTeam(player).equalsIgnoreCase("§aVert")){
					ScoreboardManager.scoreboardGame.get(player).setLine(9, "§8➵ §7Ton nexus: §f" + HearthManager.enderCrystals.get("vert").getLife()+"§c❤");
				}
				ScoreboardManager.scoreboardGame.get(player).setLine(8, "§f§k");
				ScoreboardManager.scoreboardGame.get(player).setLine(7, Main.getINSTANCE().assaut ? "§8➵ §7Assaut: §a✔" : "§8➵ §7Assaut: §c✘");
				ScoreboardManager.scoreboardGame.get(player).setLine(6, Main.getINSTANCE().pvp ? "§8➵ §7PVP: §a✔" : "§8➵ §7PVP: §c✘");
				ScoreboardManager.scoreboardGame.get(player).setLine(5, "§f§k");
				ScoreboardManager.scoreboardGame.get(player).setLine(4, "§8➵" + " §cRouge §7"+HearthManager.enderCrystals.get("rouge").getLife()+"❤");
				ScoreboardManager.scoreboardGame.get(player).setLine(3, "§8➵" + " §3Bleu §7"+HearthManager.enderCrystals.get("bleu").getLife()+"❤");
				ScoreboardManager.scoreboardGame.get(player).setLine(2, "§8➵" + " §aVert §7"+HearthManager.enderCrystals.get("vert").getLife()+"❤");
				ScoreboardManager.scoreboardGame.get(player).setLine(1, "§f§k");
				ScoreboardManager.scoreboardGame.get(player).setLine(0, "§8➵" + " §7Jour: §f" + GameRunnable.day);
			}
		}
	}
}