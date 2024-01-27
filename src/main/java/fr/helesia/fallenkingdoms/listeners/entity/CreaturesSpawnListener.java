package fr.helesia.fallenkingdoms.listeners.entity;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class CreaturesSpawnListener implements Listener {

	@EventHandler
	public void onSpawn(CreatureSpawnEvent event) {
		
		if (event.getEntityType() == EntityType.CREEPER) {
			if ((Math.random() * 100) <= 50) {
				Creeper creeper = (Creeper) Bukkit.getWorld("world").spawnEntity(event.getEntity().getLocation(), EntityType.CREEPER);
				creeper.setPowered(true);
				event.getEntity().remove();
			}
		}
	}
	
	@EventHandler
	public void onEntityDamage(EntityDeathEvent event) {
		
		if (event.getEntity() instanceof Creeper) {
			
			Creeper creeper = (Creeper) event.getEntity();
			
			if (creeper.isPowered()) {
				creeper.getWorld().dropItem(creeper.getLocation(), new ItemStack(Material.TNT, 1));
			}
		}
		
		if (event.getEntity() instanceof Zombie) {
			event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), new ItemStack(Material.SAND, 2));
		}
		
		if (event.getEntity() instanceof Enderman) {
			event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), new ItemStack(Material.GOLDEN_APPLE, 1));
			
			if ((Math.random() * 100) <= 30) {
				event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), new ItemStack(Material.DIAMOND, 2));
			}
		}
	}
}