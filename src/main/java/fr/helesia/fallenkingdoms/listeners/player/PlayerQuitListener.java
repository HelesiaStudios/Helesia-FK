package fr.helesia.fallenkingdoms.listeners.player;

import fr.helesia.fallenkingdoms.GameStatus;
import fr.helesia.fallenkingdoms.Main;
import fr.helesia.fallenkingdoms.scoreboard.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerQuitListener implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		player.getInventory().clear();
		
		event.setQuitMessage(Main.getINSTANCE().getPrefix() + "§c- §e" + player.getName() + " (" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers()+")");
		if (!GameStatus.isStatus(GameStatus.ATTENTE)) createPNJ(player);
	}
	
	@EventHandler
	public void onVillagerAttack(EntityDeathEvent event) {
		
		if (!(event.getEntity() instanceof Villager)) return;
		
		Villager villager = (Villager) event.getEntity();
		Player player = (Player) event.getEntity().getKiller();
		
		fr.helesia.fallenkingdoms.Villager v = Main.getINSTANCE().villagers.get(player.getUniqueId());
		
		if (villager.getCustomName().equalsIgnoreCase(v.getVillagerID())) {


			for (ItemStack i : v.getInventory()) {
				Bukkit.getWorld("world").dropItemNaturally(player.getLocation(), i);
			}
			Main.getINSTANCE().villagers.remove(player.getUniqueId());
		}
	}
	
	private void createPNJ(Player player) {
		
		Villager villager = (Villager) Bukkit.getWorld("world").spawnEntity(player.getLocation(), EntityType.VILLAGER);
		// name floating text
		villager.setCustomName(Main.getINSTANCE().getTeam(player) + " " + player.getName());
		villager.setCustomNameVisible(true);
		
		// inventory
		Main.getINSTANCE().villagers.put(player.getUniqueId(), new fr.helesia.fallenkingdoms.Villager(player.getName(), player));
	}
}