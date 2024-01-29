package fr.helesia.fallenkingdoms.listeners;

import fr.helesia.fallenkingdoms.listeners.player.*;
import fr.helesia.fallenkingdoms.menus.TeamsMenu;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import fr.helesia.fallenkingdoms.listeners.entity.CreaturesSpawnListener;
import fr.helesia.fallenkingdoms.listeners.entity.EntityDamageListener;

public class ListenersManager {

	public Plugin plugin;
	public PluginManager pm;

	public ListenersManager(Plugin plugin) {
		this.plugin = plugin;
		this.pm = Bukkit.getPluginManager();
	}
	
	public void registerListeners() {
		pm.registerEvents(new PlayerJoinListener(), this.plugin);
		pm.registerEvents(new PlayerMoveListener(), this.plugin);
		pm.registerEvents(new PlayerBlockListener(), this.plugin);
		pm.registerEvents(new CreaturesSpawnListener(), this.plugin);
		pm.registerEvents(new FurnasePropertyListener(), this.plugin);
		pm.registerEvents(new PlayerQuitListener(), this.plugin);
		pm.registerEvents(new EntityDamageListener(), this.plugin);
		pm.registerEvents(new PlayerDeathEvent(), this.plugin);
		pm.registerEvents(new PlayerWaiting(), this.plugin);

		
		pm.registerEvents(new TeamsMenu(), this.plugin);
	}
}