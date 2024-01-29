package fr.helesia.fallenkingdoms.listeners.player;

import fr.helesia.fallenkingdoms.GameStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerWaiting implements Listener {

    @EventHandler
    public void hunger(FoodLevelChangeEvent e){
        if(GameStatus.isStatus(GameStatus.ATTENTE)){
            e.setCancelled(true);
            e.setFoodLevel(20);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void pvp(EntityDamageEvent e){
        if(GameStatus.isStatus(GameStatus.ATTENTE)){
            e.setDamage(-1);
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void drop(PlayerDropItemEvent e){
        if(GameStatus.isStatus(GameStatus.ATTENTE)){
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void click(InventoryClickEvent e){
        if(GameStatus.isStatus(GameStatus.ATTENTE)){
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void place(BlockPlaceEvent e){
        if(GameStatus.isStatus(GameStatus.ATTENTE)){
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void breakblock(BlockBreakEvent e){
        if(GameStatus.isStatus(GameStatus.ATTENTE)){
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }
    
}
