package fr.helesia.fallenkingdoms.game;

import fr.helesia.fallenkingdoms.Villager;
import fr.helesia.fallenkingdoms.player.GamePlayer;
import fr.helesia.fallenkingdoms.utils.ItemBuilder;
import fr.helesia.fallenkingdoms.utils.TitleManager;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import fr.helesia.fallenkingdoms.Main;
import org.bukkit.inventory.ItemStack;

public class GameManager {
	
	private Location redSpawn, blueSpawn;

	public GameManager() {
		this.redSpawn = new Location(Bukkit.getWorld("world"), 1999.628, 65.56250, 2108.371);
		this.blueSpawn = new Location(Bukkit.getWorld("world"), 2001.685, 65.56250, 1892.300);
	}
	
	public void loadGame() {
		for(Entity entity : Bukkit.getWorld("world").getEntities()) {
			if ((entity instanceof EnderCrystal) || (entity instanceof Villager)) {
				entity.remove();
			}
		}
		Bukkit.getWorld("world").setPVP(false);
		Bukkit.getWorld("world").setTime(0);
		Bukkit.getWorld("world").setDifficulty(Difficulty.NORMAL);
		
		// rouge
		this.create(new Location(Bukkit.getWorld("world"), 1999.990, 66.0, 2086.954), "rouge");
		// bleu
		this.create(new Location(Bukkit.getWorld("world"), 2001.019, 66.0, 1913.966), "bleu");
		
		// scoreboard
		for (Player player : Bukkit.getOnlinePlayers()) {
			GamePlayer gp = GamePlayer.gamePlayers.get(player.getName());
			gp.scoreboard.loadScoreboardGame();
			
			// tp les teams dans leur base
			if (Main.getINSTANCE().red_team.contains(player.getUniqueId())) {
				player.teleport(getRedSpawn());
			} else if (Main.getINSTANCE().blue_team.contains(player.getUniqueId())) {
				player.teleport(getBlueSpawn());
			}
			
			player.getInventory().clear();
			ItemStack pioche = new ItemBuilder(Material.WOOD_PICKAXE).toItemStack();
			player.getInventory().setItem(0, pioche);
			ItemStack hache = new ItemBuilder(Material.WOOD_AXE).toItemStack();
			player.getInventory().setItem(1, hache);
			ItemStack shovel = new ItemBuilder(Material.WOOD_SPADE).toItemStack();
			player.getInventory().setItem(2, shovel);

			TitleManager.sendTitle(player, "§6Fallen Kingdoms", "§eLa partie a commencé", 40);
		}
		Bukkit.broadcastMessage("§6§m-§6FK§6§m----------------------------------------------");
		Bukkit.broadcastMessage("§fLa phase §bPréparation §fcommence !");
		Bukkit.broadcastMessage("§f");
		Bukkit.broadcastMessage("§fEquipe toi et récolte un maximum de TNT pour te préparer à l'assaut !");
		Bukkit.broadcastMessage("§6§m-§6§m--§6§m----------------------------------------------");
		Bukkit.broadcastMessage(Main.getINSTANCE().getPrefix() + "Si tu es coincé, tu peux utiliser la commande §b/suicide§f.");
	}
	
	private void create(Location loc, String name) {
		EnderCrystal enderCrystal = (EnderCrystal) Bukkit.getWorld("world").spawnEntity(loc, EntityType.ENDER_CRYSTAL);
		enderCrystal.setCustomName(name);
		enderCrystal.setCustomNameVisible(false);
		
		new HearthManager(enderCrystal, name);
	}
	
	public Location getBlueSpawn() {
		return blueSpawn;
	}
	
	public Location getRedSpawn() {
		return redSpawn;
	}
}