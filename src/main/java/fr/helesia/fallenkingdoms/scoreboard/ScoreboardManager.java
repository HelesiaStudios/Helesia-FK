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
    
  		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(7, "§8??/??/24 fk-1aeffs");
  		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(6, "§f§k");
  		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(5, "§7Jeu: §fFallen Kingdoms");
  		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(4, "§7Joueurs: §a" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(3, "§9§k");
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(2, "§fEn attente (" + new SimpleDateFormat("mm:ss").format(new Date(Main.getINSTANCE().lobbyRunnable.timer * 1000)) + ")");
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(1, "§9§k");
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(0, ChatColor.GOLD + "mc.helesia.fr");
  	}
  	

  	public void loadScoreboardGame() {
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(11, "§8??/??/24 fk-1aeffs");
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(10, "§f§k");
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(9, "§7Equipe: §f" + Main.getINSTANCE().getTeam(player));
		if(Main.getINSTANCE().getTeam(player).equalsIgnoreCase("§cRouge")){
			((ScoreboardSign) scoreboardGame.get(this.player)).setLine(8, "§7Ton nexus: §f" + HearthManager.enderCrystals.get("rouge").getLife());
		} else if(Main.getINSTANCE().getTeam(player).equalsIgnoreCase("§9Bleu")){
			((ScoreboardSign) scoreboardGame.get(this.player)).setLine(8, "§7Ton nexus: §f" + HearthManager.enderCrystals.get("bleu").getLife());
		}
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(7, "§f§k");
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(6, Main.getINSTANCE().assaut ? "§7Assaut: §a✔" : "§7Assaut: §c✘");
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(5, Main.getINSTANCE().pvp ? "§7PVP: §a✔" : "§7PVP: §c✘");
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(4, "§f§k");
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(3, "§cRouge §7"+HearthManager.enderCrystals.get("rouge").getLife()+"❤");
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(2, "§3Bleu §7"+HearthManager.enderCrystals.get("bleu").getLife()+"❤");
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(1, "§f§k");
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(0, "§7Temps: §f" + GameRunnable.day);
  	}
}