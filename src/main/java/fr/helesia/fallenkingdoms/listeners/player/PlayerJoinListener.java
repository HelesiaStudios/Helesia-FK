package fr.helesia.fallenkingdoms.listeners.player;

import fr.helesia.fallenkingdoms.GameStatus;
import fr.helesia.fallenkingdoms.Main;
import fr.helesia.fallenkingdoms.player.GamePlayer;
import fr.helesia.fallenkingdoms.utils.DyeColor;
import fr.helesia.fallenkingdoms.utils.ItemBuilder;
import jdk.internal.org.jline.utils.Colors;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import fr.helesia.fallenkingdoms.runnables.LobbyRunnable;

public class PlayerJoinListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		if (!GameStatus.isStatus(GameStatus.ATTENTE)) {
			player.setGameMode(GameMode.SPECTATOR);
			Location spawn = new Location(Bukkit.getWorld("world"), 2000.439, 66.0, 2000.512);
			player.teleport(spawn);
			player.sendMessage(ChatColor.GOLD + "La partie a déjà commencé vous êtes désormais spectateur.");
			
			new GamePlayer(player.getName());
			return;
		}

		Location spawn = new Location(Bukkit.getWorld("world"), 2000.439, 66.0, 2000.512);
		player.teleport(spawn);

		event.setJoinMessage(Main.getINSTANCE().getPrefix() + "§e" + player.getName() + " §7a rejoint la partie §a(" + Bukkit.getOnlinePlayers().size() + "§a/" + Bukkit.getMaxPlayers() + ")");
		
		new GamePlayer(player.getName());
		GamePlayer gp = GamePlayer.gamePlayers.get(player.getName());
		gp.scoreboard.loadScoreboard();
		
		if ((Bukkit.getOnlinePlayers().size() >= 1) && (!(Main.getINSTANCE().lobbyRunnable.start))) {
			new LobbyRunnable().runTaskTimer(Main.getINSTANCE(), 0L, 20L);
			Main.getINSTANCE().lobbyRunnable.start = true;
		}
		ItemStack selectTeam = new ItemBuilder(Material.STAINED_CLAY).setDyeColor(DyeColor.WHITE).setName("§6§lChoisir une équipe §7(Clic droit)").toItemStack();
		player.getInventory().setItem(0, selectTeam);
	}
}