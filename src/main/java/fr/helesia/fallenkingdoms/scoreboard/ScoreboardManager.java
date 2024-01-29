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
  		this.scoreboardSign.setObjectiveName("§e❖ §6§lHelesia §e❖");
  		this.scoreboardSign.create();
    
  		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(7, "§8FK ┃ fk-1");
  		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(6, "§f§k");
  		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(5, "§8➵ §7Jeu: §fFallen Kingdoms");
  		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(4, "§8➵ §7Joueurs: §a" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(3, "§9§k");
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(2, "§8➵ §7En attente: §f" + new SimpleDateFormat("mm:ss").format(new Date(Main.getINSTANCE().lobbyRunnable.timer * 1000))+"s");
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(1, "§9§k");
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(0, ChatColor.GOLD + "mc.helesia.fr");
  	}
  	

  	public void loadScoreboardGame() {
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(12, "§8FK ┃ fk-1");
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(11, "§f§k");
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(10, "§8➵ §7Equipe: §f" + Main.getINSTANCE().getTeam(player));
		if(Main.getINSTANCE().getTeam(player).equalsIgnoreCase("§cRouge")){
			((ScoreboardSign) scoreboardGame.get(this.player)).setLine(9, "§8➵ §7Ton nexus: §f" + HearthManager.enderCrystals.get("rouge").getLife()+"§c❤");
		} else if(Main.getINSTANCE().getTeam(player).equalsIgnoreCase("§9Bleu")){
			((ScoreboardSign) scoreboardGame.get(this.player)).setLine(9, "§8➵ §7Ton nexus: §f" + HearthManager.enderCrystals.get("bleu").getLife()+"§c❤");
		} else if(Main.getINSTANCE().getTeam(player).equalsIgnoreCase("§aVert")){
			((ScoreboardSign) scoreboardGame.get(this.player)).setLine(9, "§8➵ §7Ton nexus: §f" + HearthManager.enderCrystals.get("vert").getLife()+"§c❤");
		}
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(8, "§f§k");
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(7, Main.getINSTANCE().assaut ? "§8➵ §7Assaut: §a✔" : "§8➵ §7Assaut: §c✘");
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(6, Main.getINSTANCE().pvp ? "§8➵ §7PVP: §a✔" : "§8➵ §7PVP: §c✘");
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(5, "§f§k");
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(4, "§8➵" + " §cRouge §7"+HearthManager.enderCrystals.get("rouge").getLife()+"❤");
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(3, "§8➵" + " §3Bleu §7"+HearthManager.enderCrystals.get("bleu").getLife()+"❤");
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(2, "§8➵" + " §aVert §7"+HearthManager.enderCrystals.get("vert").getLife()+"❤");
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(1, "§f§k");
		((ScoreboardSign) scoreboardGame.get(this.player)).setLine(0, "§8➵" + " §7Jour: §f" + GameRunnable.day);
  	}
}