package fr.helesia.fallenkingdoms.listeners.player;

import fr.helesia.fallenkingdoms.GameStatus;
import fr.helesia.fallenkingdoms.Main;
import fr.helesia.fallenkingdoms.player.GamePlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerBlockListener implements Listener {

	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		
		Player player = (event.getPlayer());
		GamePlayer gp = GamePlayer.gamePlayers.get(player.getName());
		
		if (!gp.isInBase) {
			if (player.getItemInHand().getType() == Material.WATER_BUCKET || player.getItemInHand().getType() == Material.LAVA_BUCKET && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				event.setCancelled(true);
				return;
			}
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		
		if (event.getBlock().getType().equals(Material.TNT) && (GameStatus.isStatus(GameStatus.ASSAUTS) || GameStatus.isStatus(GameStatus.DEATHMATCH))) {
			event.setCancelled(false);
		} else if (event.getBlock().getType().equals(Material.TNT) && !(GameStatus.isStatus(GameStatus.ASSAUTS))) {
			player.sendMessage("§cLes assauts ne sont pas encore activés.");
			event.setCancelled(true);
		}

		
		if (Main.getINSTANCE().red_team.contains(player.getUniqueId())) {
			if (!Main.getINSTANCE().redBase.isInArea(event.getBlock().getLocation())) {
				if (!event.getBlock().getType().equals(Material.TNT) && !event.getBlock().getType().equals(Material.FIRE)) {
					event.setCancelled(true);
					return;
				}
			}
		}

		
		if (Main.getINSTANCE().blue_team.contains(player.getUniqueId())) {
			if (!Main.getINSTANCE().blueBase.isInArea(event.getBlock().getLocation())) {
				if (!event.getBlock().getType().equals(Material.TNT) && !event.getBlock().getType().equals(Material.FIRE)) {
					event.setCancelled(true);
					return;
				}
			}
		}

		if (Main.getINSTANCE().green_team.contains(player.getUniqueId())) {
			if (!Main.getINSTANCE().greenBase.isInArea(event.getBlock().getLocation())) {
				if (!event.getBlock().getType().equals(Material.TNT) && !event.getBlock().getType().equals(Material.FIRE)) {
					event.setCancelled(true);
					return;
				}
			}
		}
	}

}