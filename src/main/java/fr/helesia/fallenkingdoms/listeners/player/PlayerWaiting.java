package fr.helesia.fallenkingdoms.listeners.player;

import fr.helesia.fallenkingdoms.GameStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
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
    public void pvp(EntityDamageByEntityEvent e){
        if(GameStatus.isStatus(GameStatus.ATTENTE)){
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
}
