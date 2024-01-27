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
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import fr.helesia.fallenkingdoms.listeners.ListenersManager;
import fr.helesia.fallenkingdoms.runnables.LobbyRunnable;

public class Main extends JavaPlugin {
	
	private static Main INSTANCE;
	public LobbyRunnable lobbyRunnable;
	
	public RegionManager redBase, blueBase;
	
	public List<UUID> red_team = new ArrayList<>();
	public List<UUID> blue_team = new ArrayList<>();
	
	public Map<UUID, Villager> villagers = new HashMap<>();

	public boolean assaut, pvp, deathmatch = false;
	
	public Random random = new Random();
	
	@Override
	public void onLoad() { INSTANCE = this; }
	
	@Override
	public void onEnable() {
		this.getCommand("start").setExecutor(new TestCommand());
		
		lobbyRunnable = new LobbyRunnable();
		random = new Random();

		Bukkit.getWorld("world").setPVP(false);
		Bukkit.getWorld("world").setTime(0);
		Bukkit.getWorld("world").setDifficulty(Difficulty.PEACEFUL);
		
		blueBase = new RegionManager(new Location(Bukkit.getWorld("world"), 1984.500, 82.0, 1930.509), new Location(Bukkit.getWorld("world"), 2017.798, 56.19015, 1897.430));
		redBase = new RegionManager(new Location(Bukkit.getWorld("world"), 2016.582, 82.0, 2070.412), new Location(Bukkit.getWorld("world"), 1983.658, 57.89932, 2103.974));
		
		new ListenersManager(this).registerListeners();
		GameStatus.setStatus(GameStatus.ATTENTE);
		
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
}