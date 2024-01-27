package fr.helesia.fallenkingdoms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import fr.helesia.fallenkingdoms.utils.RegionManager;
import org.bukkit.Bukkit;
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
		this.getCommand("test").setExecutor(new TestCommand());
		
		lobbyRunnable = new LobbyRunnable();
		random = new Random();
		
		blueBase = new RegionManager(new Location(Bukkit.getWorld("world"), -405.5, 94, 369.5), new Location(Bukkit.getWorld("world"), -437.5, 91, 400.5));
		redBase = new RegionManager(new Location(Bukkit.getWorld("world"), -575.5, 85, 404), new Location(Bukkit.getWorld("world"), -605.5, 88, 373.5));
		
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