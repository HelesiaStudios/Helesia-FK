package fr.helesia.fallenkingdoms.listeners.player;

import fr.helesia.fallenkingdoms.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
public class PlayerDeathEvent implements Listener {

    @EventHandler
    public void Death(org.bukkit.event.entity.PlayerDeathEvent e){
        e.setDeathMessage(null);
        Player victim = (Player) e.getEntity();

        if (Main.getINSTANCE().red_team.contains(victim.getUniqueId())) {
            victim.teleport(getRedSpawn());
        } else if (Main.getINSTANCE().blue_team.contains(victim.getUniqueId())) {
            victim.teleport(getBlueSpawn());
        }
    }
    public Location getBlueSpawn(){
        return new Location(Bukkit.getWorld("world"), 2001.685, 65.56250, 1892.300);
    }

    public Location getRedSpawn(){
        return new Location(Bukkit.getWorld("world"), 1999.628, 65.56250, 2108.371);
    }
}
