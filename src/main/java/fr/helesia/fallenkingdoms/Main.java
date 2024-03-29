package fr.helesia.fallenkingdoms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import fr.helesia.fallenkingdoms.utils.RegionManager;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.helesia.fallenkingdoms.listeners.ListenersManager;
import fr.helesia.fallenkingdoms.runnables.LobbyRunnable;

public class Main extends JavaPlugin {
	
	private static Main INSTANCE;
	public LobbyRunnable lobbyRunnable;
	
	public RegionManager redBase, blueBase, greenBase;
	
	public List<UUID> red_team = new ArrayList<>();
	public List<UUID> blue_team = new ArrayList<>();
	public List<UUID> green_team = new ArrayList<>();
	
	public Map<UUID, Villager> villagers = new HashMap<>();

	public boolean assaut, pvp, deathmatch = false;
	
	public Random random = new Random();
	
	@Override
	public void onLoad() { INSTANCE = this; }
	
	@Override
	public void onEnable() {
		this.getCommand("start").setExecutor(new TestCommand());
		this.getCommand("team").setExecutor(new TestCommand());

		lobbyRunnable = new LobbyRunnable();
		random = new Random();

		Bukkit.getWorld("world").setPVP(false);
		Bukkit.getWorld("world").setTime(0);
		Bukkit.getWorld("world").setDifficulty(Difficulty.PEACEFUL);
		
		blueBase = new RegionManager(new Location(Bukkit.getWorld("world"), 1984.500, 82.0, 1930.509), new Location(Bukkit.getWorld("world"), 2017.798, 56.19015, 1897.430));
		redBase = new RegionManager(new Location(Bukkit.getWorld("world"), 2016.582, 82.0, 2070.412), new Location(Bukkit.getWorld("world"), 1983.658, 57.89932, 2103.974));
		greenBase = new RegionManager(new Location(Bukkit.getWorld("world"), 1930.565, 82.0, 1983.449), new Location(Bukkit.getWorld("world"), 1898.903, 58.16942, 2015.359));

		new ListenersManager(this).registerListeners();
		GameStatus.setStatus(GameStatus.ATTENTE);
		for(Entity entity : Bukkit.getWorld("world").getEntities()) {
			if ((entity instanceof EnderCrystal) || (entity instanceof Villager)) {
				entity.remove();
			}
		}
		
	}
	
	public static Main getINSTANCE() {
		return INSTANCE;
	}
	
	public String getPrefix() {
		return "§6[FK] §f";
	}
	
	@Override
	public void onDisable() { 
		
		for(Entity entity : Bukkit.getWorld("world").getEntities()) {
			if ((entity instanceof EnderCrystal) || (entity instanceof Villager)) {
				entity.remove();
			}
		}
	}

	public String getTeam(Player player) {
		if (Main.getINSTANCE().red_team.contains(player.getUniqueId())) {
			return "§cRouge";
		} else if (Main.getINSTANCE().blue_team.contains(player.getUniqueId())) {
			return "§3Bleu";
		} else if (Main.getINSTANCE().green_team.contains(player.getUniqueId())){
			return "§aVert";
		}
		return "§7Aucune";
	}
}