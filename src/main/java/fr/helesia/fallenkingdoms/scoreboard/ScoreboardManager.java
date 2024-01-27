package fr.helesia.fallenkingdoms.scoreboard;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import fr.helesia.fallenkingdoms.Main;
import fr.helesia.fallenkingdoms.game.HearthManager;
import fr.helesia.fallenkingdoms.runnables.GameRunnable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

public class ScoreboardManager {

	public Player player;
	public ScoreboardSign scoreboardSign;
	public static Map<Player, ScoreboardSign> scoreboardGame = new HashMap<>();
  
  	public ScoreboardManager(Player player) {
  		this.player = player;
  		this.scoreboardSign = new ScoreboardSign(player, player.getName());
  		scoreboardGame.put(player, this.scoreboardSign);
  	}
  
  	public void loadScoreboard() {
  		this.scoreboardSign.setObjectiveName(ChatColor.YELLOW + " " + ChatColor.GOLD + ChatColor.BOLD + "Fallen Kingdoms");
  		this.scoreboardSign.create();
    
  		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(10, ChatColor.DARK_GRAY + "-----------------");
  		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(9, ChatColor.WHITE + "Début dans: " + ChatColor.GREEN + new SimpleDateFormat("mm:ss").format(new Date(Main.getINSTANCE().lobbyRunnable.timer * 1000)));
  		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(8, ChatColor.WHITE + "Joueurs: " + ChatColor.YELLOW + Bukkit.getOnlinePlayers().size() + "§8/§e" + Bukkit.getMaxPlayers());
  		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(7, "§3");
  		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(6, ChatColor.RED + "En attente de");
  		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(5, ChatColor.RED + "joueurs...");;
  		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(4, "§1");
	    ((ScoreboardSign) scoreboardGame.get(this.player)).setLine(1, "§8-----------------");
	    ((ScoreboardSign) scoreboardGame.get(this.player)).setLine(0, ChatColor.GOLD + "mc.helesia.fr");
  	}
  	
  	public void loadScoreboardGame() {
  		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(9, "§6 ");
  		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(8, "§fJour: §a" + GameRunnable.day);
  		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(7, ChatColor.WHITE + "Temps: " + ChatColor.AQUA + new SimpleDateFormat("mm:ss").format(new Date(GameRunnable.timer * 1000)));
  		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(6, ChatColor.WHITE + "Kills: " + ChatColor.RED + player.getStatistic(Statistic.PLAYER_KILLS));
  		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(5, ChatColor.WHITE + "Rouge: " + ChatColor.RED + HearthManager.enderCrystals.get("rouge").getLife() + ".0");
  		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(4, ChatColor.WHITE + "Bleu: " + ChatColor.RED + HearthManager.enderCrystals.get("bleu").getLife() + ".0");
  		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(3, "§7------------");
  		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(2, Main.getINSTANCE().pvp ? "§fPVP: §a✓" : "§fPVP: §c✗");
  		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(1, Main.getINSTANCE().assaut ? "§fAssauts: §a✓" : "§fAssauts: §c✗");
  	}
}