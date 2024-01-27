package fr.helesia.fallenkingdoms.runnables;

import java.text.SimpleDateFormat;
import java.util.Date;

import fr.helesia.fallenkingdoms.GameStatus;
import fr.helesia.fallenkingdoms.Main;
import fr.helesia.fallenkingdoms.game.GameManager;
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
			Bukkit.broadcastMessage("§6Démarrage de la partie dans §e" + timer + " " + getSecond() + "§6.");
			for (Player players : Bukkit.getOnlinePlayers()) {
				players.playSound(players.getLocation(), Sound.ORB_PICKUP, 10f, 1f);
			}
		}
	}

	private String getSecond() { return timer == 1 ? "seconde" : "secondes"; }
	
	private void setLevel() {
		for (Player players : Bukkit.getOnlinePlayers()) {
			players.setLevel(timer);
		}
	}
}