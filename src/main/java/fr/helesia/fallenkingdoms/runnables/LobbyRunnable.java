package fr.helesia.fallenkingdoms.runnables;

import java.text.SimpleDateFormat;
import java.util.Date;

import fr.helesia.fallenkingdoms.GameStatus;
import fr.helesia.fallenkingdoms.Main;
import fr.helesia.fallenkingdoms.game.GameManager;
import fr.helesia.fallenkingdoms.scoreboard.ScoreboardSign;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.helesia.fallenkingdoms.scoreboard.ScoreboardManager;

public class LobbyRunnable extends BukkitRunnable {
	
	public int timer = 20;
	public boolean start = false;

	@Override
	public void run() {
		
		if (!(GameStatus.isStatus(GameStatus.ATTENTE))) {
			timer = 121;
			start = false;
			this.cancel();
			return;
		}
		
		if (Bukkit.getOnlinePlayers().size() < 1) {
			Bukkit.broadcastMessage("§cIl n'y a pas assez de joueurs pour lancer la partie.");
			timer = 121;
			start = false;
			setLevel();
			this.cancel();
			return;
		}
		timer--;
		setLevel();
		
		if (timer == 0) {
			this.cancel();
			GameStatus.setStatus(GameStatus.GAME);
			// load game
			new GameManager().loadGame();
			new GameRunnable().runTaskTimer(Main.getINSTANCE(), 0L, 20L);
		}
		
		if ((timer == 120) || (timer == 90) || (timer == 60) || (timer == 30) || (timer == 15) || (timer == 10) || (timer <= 5 && timer != 0)) {
			Bukkit.broadcastMessage("§8➵ §fDébut de la partie dans §b" + timer + " " + getSecond() + "§f.");
			for (Player players : Bukkit.getOnlinePlayers()) {
				ScoreboardManager.scoreboardGame.get(players).setLine(7, "§8FK ┃ fk-1");
				ScoreboardManager.scoreboardGame.get(players).setLine(6, "§f§k");
				ScoreboardManager.scoreboardGame.get(players).setLine(5, "§8➵ §7Jeu: §fFallen Kingdoms");
				ScoreboardManager.scoreboardGame.get(players).setLine(4, "§8➵ §7Joueurs: §a" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
				ScoreboardManager.scoreboardGame.get(players).setLine(3, "§9§k");
				ScoreboardManager.scoreboardGame.get(players).setLine(2, "§8➵ §7En attente: §f" + new SimpleDateFormat("mm:ss").format(new Date(Main.getINSTANCE().lobbyRunnable.timer * 1000))+"s");
				ScoreboardManager.scoreboardGame.get(players).setLine(1, "§9§k");
				ScoreboardManager.scoreboardGame.get(players).setLine(0, ChatColor.GOLD + "mc.helesia.fr");
				players.playSound(players.getLocation(), Sound.ORB_PICKUP, 10f, 1f);
				players.sendTitle("§f", "§7Début dans " + timer + " " + getSecond());
			}
		}
	}

	private String getSecond() { return timer == 1 ? "seconde" : "secondes"; }
	
	private void setLevel() {
		for (Player players : Bukkit.getOnlinePlayers()) {
			players.setLevel(timer);
			ScoreboardManager.scoreboardGame.get(players).setLine(7, "§8FK ┃ fk-1");
			ScoreboardManager.scoreboardGame.get(players).setLine(6, "§f§k");
			ScoreboardManager.scoreboardGame.get(players).setLine(5, "§8➵ §7Jeu: §fFallen Kingdoms");
			ScoreboardManager.scoreboardGame.get(players).setLine(4, "§8➵ §7Joueurs: §a" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
			ScoreboardManager.scoreboardGame.get(players).setLine(3, "§9§k");
			ScoreboardManager.scoreboardGame.get(players).setLine(2, "§8➵ §7En attente: §f" + new SimpleDateFormat("mm:ss").format(new Date(Main.getINSTANCE().lobbyRunnable.timer * 1000))+"s");
			ScoreboardManager.scoreboardGame.get(players).setLine(1, "§9§k");
			ScoreboardManager.scoreboardGame.get(players).setLine(0, ChatColor.GOLD + "mc.helesia.fr");
		}
	}
}