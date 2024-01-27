package fr.helesia.fallenkingdoms.listeners.player;

import fr.helesia.fallenkingdoms.GameStatus;
import fr.helesia.fallenkingdoms.Main;
import fr.helesia.fallenkingdoms.player.GamePlayer;
import fr.helesia.fallenkingdoms.utils.ArrowTargetUtils;
import fr.helesia.fallenkingdoms.utils.TitleManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		
		GamePlayer gp = GamePlayer.gamePlayers.get(player.getName());
		
		if (GameStatus.isStatus(GameStatus.DEATHMATCH)) {
			
			if (gp.getTeam() != null) {
				TitleManager.sendActionBar(player, "§7Votre base: §f" + (int) player.getLocation().distance(gp.region.getMiddle()) + "b " + getArrowColor(player.getLocation().distance(gp.region.getMiddle())) + ArrowTargetUtils.calculateArrow(player, gp.region.getMiddle()));
			}
			
			if (Main.getINSTANCE().redBase.isInArea(player.getLocation())) {
				if (Main.getINSTANCE().red_team.contains(player.getUniqueId())) {
					if (!gp.isInBase) {
						gp.isInBase = true;
						player.sendMessage("§bVous entrez dans votre base.");
					}
				}
			} else {
				
				if (Main.getINSTANCE().red_team.contains(player.getUniqueId())) {
					if (gp.isInBase) {
						gp.isInBase = false;
						player.sendMessage("§cVous sortez de votre base.");
					}
				}
			}
			
			// bleus
			if (Main.getINSTANCE().blueBase.isInArea(player.getLocation())) {
				if (Main.getINSTANCE().blue_team.contains(player.getUniqueId())) {
					if (!gp.isInBase) {
						gp.isInBase = true;
						player.sendMessage("§bVous entrez dans votre base.");
					}
				}
			} else {
				
				if (Main.getINSTANCE().blue_team.contains(player.getUniqueId())) {
					if (gp.isInBase) {
						gp.isInBase = false;
						player.sendMessage("§cVous sortez de votre base.");
					}
				}
			}
		}
	}
	
	public String getArrowColor(double d) {
		if (d >= 100) return "§c";
		if (d > 20  && d < 100) return "§e";
		if (d <= 20) return "§a";
		return "§a";
	}
}