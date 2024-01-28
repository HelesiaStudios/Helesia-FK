package fr.helesia.fallenkingdoms.listeners.player;

import fr.helesia.fallenkingdoms.GameStatus;
import fr.helesia.fallenkingdoms.Main;
import fr.helesia.fallenkingdoms.menus.TeamsMenu;
import fr.helesia.fallenkingdoms.player.GamePlayer;
import fr.helesia.fallenkingdoms.scoreboard.ScoreboardManager;
import fr.helesia.fallenkingdoms.utils.ItemBuilder;
import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import fr.helesia.fallenkingdoms.runnables.LobbyRunnable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerJoinListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		new TeamsMenu().open(player);
		player.closeInventory();
		player.setGameMode(GameMode.SURVIVAL);
		
		if (!GameStatus.isStatus(GameStatus.ATTENTE)) {
			player.setGameMode(GameMode.SPECTATOR);
			Location spawn = new Location(Bukkit.getWorld("world"), 2000.439, 66.0, 2000.512);
			player.teleport(spawn);
			player.sendMessage(ChatColor.GOLD + "La partie a déjà commencé vous êtes désormais spectateur.");
			
			new GamePlayer(player.getName());
			return;
		}
		player.getInventory().clear();

		Location spawn = new Location(Bukkit.getWorld("world"), 2000.439, 66.0, 2000.512);
		player.teleport(spawn);

		event.setJoinMessage(Main.getINSTANCE().getPrefix() + "§a+ §e" + player.getName() + " (" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers() + ")");
		
		new GamePlayer(player.getName());
		GamePlayer gp = GamePlayer.gamePlayers.get(player.getName());
		gp.scoreboard.loadScoreboard();
		
		if ((Bukkit.getOnlinePlayers().size() >= 10) && (!(Main.getINSTANCE().lobbyRunnable.start))) {
			new LobbyRunnable().runTaskTimer(Main.getINSTANCE(), 0L, 20L);
			Main.getINSTANCE().lobbyRunnable.start = true;
			for(Player players : Bukkit.getOnlinePlayers()){
				ScoreboardManager.scoreboardGame.get(players).setLine(4, "§8➵ §7Joueurs: §a" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
				ScoreboardManager.scoreboardGame.get(players).setLine(2, "§8➵ §7En attente: §f" + new SimpleDateFormat("mm:ss").format(new Date(Main.getINSTANCE().lobbyRunnable.timer * 1000)));
			}
		}
		new LobbyRunnable().runTaskTimer(Main.getINSTANCE(), 0L, 20L);
		giveItem(player);
	}

	@EventHandler
	public void PlayerInteractItem(PlayerInteractEvent e){
		Player player = e.getPlayer();
		ItemStack item = e.getItem();
		Action action = e.getAction();

		if(e.getItem() == null && e.getAction() != null) return;

		switch (e.getItem().getType()) {
			case STAINED_CLAY:

				if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
					new TeamsMenu().open(player);
				}
				break;

			default:

				break;
		}
	}

	public void giveItem(Player player){
		ItemStack selectTeam = new ItemBuilder(Material.STAINED_CLAY).setDyeColor(DyeColor.getByColor(Color.WHITE)).setName("§6§lChoisir une équipe §7(Clic droit)").toItemStack();
		ItemStack kit = new ItemBuilder(Material.NAME_TAG).setName("§6§lChoisir un kit §7(Clic droit)").toItemStack();
		ItemStack hub = new ItemBuilder(Material.BED).setName("§6§lRetourner au lobby §7(Clic droit)").toItemStack();

		player.getInventory().setItem(0, selectTeam);
		player.getInventory().setItem(1, kit);
		player.getInventory().setItem(2, hub);

	}
}