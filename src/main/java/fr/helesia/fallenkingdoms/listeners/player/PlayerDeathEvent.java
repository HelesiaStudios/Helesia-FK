package fr.helesia.fallenkingdoms.listeners.player;

import fr.helesia.fallenkingdoms.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
public class PlayerDeathEvent implements Listener {
    private Location redSpawn, blueSpawn, greenSpawn;

    public PlayerDeathEvent() {
        this.redSpawn = new Location(Bukkit.getWorld("world"), 1999.628, 65.56250, 2108.371, -179, -0);
        this.blueSpawn = new Location(Bukkit.getWorld("world"), 2001.685, 65.56250, 1892.300, -0, 2);
        this.greenSpawn = new Location(Bukkit.getWorld("world"), 1892.429, 65.56250, 1999.704, -89, -0);
    }

    @EventHandler
    public void Death(org.bukkit.event.entity.PlayerDeathEvent e){
        e.setDeathMessage(null);
        Player victim = (Player) e.getEntity();
        Player attacker = victim.getKiller();
        victim.spigot().respawn();
        victim.setHealth(victim.getMaxHealth());
        victim.resetMaxHealth();
        victim.setGameMode(GameMode.SPECTATOR);
        Location lastDeath = new Location(victim.getWorld(), victim.getLocation().getX(), victim.getLocation().getY(), victim.getLocation().getZ());

        for(Player players : Bukkit.getOnlinePlayers()){
            if(Main.getINSTANCE().pvp == false){
                victim.teleport(lastDeath);
                victim.sendMessage("§cLe pvp n'est pas encore actif !");
            }



            if(Bukkit.getOnlinePlayers().contains(attacker)){
                victim.setGameMode(GameMode.SURVIVAL);
                Bukkit.broadcastMessage("§7✝ §f" + Main.getINSTANCE().getTeam(victim) + " " + victim.getName() + " §7a été tué(e) par §f" + Main.getINSTANCE().getTeam(attacker) + " " + attacker.getName());
                if (Main.getINSTANCE().red_team.contains(victim.getUniqueId())) {
                    victim.teleport(getRedSpawn());
                } else if (Main.getINSTANCE().blue_team.contains(victim.getUniqueId())) {
                    victim.teleport(getBlueSpawn());
                } else if (Main.getINSTANCE().green_team.contains(victim.getUniqueId())){
                    victim.teleport(getGreenSpawn());
                }
            }
            victim.setGameMode(GameMode.SURVIVAL);
            if (Main.getINSTANCE().red_team.contains(victim.getUniqueId())) {
                victim.teleport(getRedSpawn());
            } else if (Main.getINSTANCE().blue_team.contains(victim.getUniqueId())) {
                victim.teleport(getBlueSpawn());
            } else if (Main.getINSTANCE().green_team.contains(victim.getUniqueId())){
                victim.teleport(getGreenSpawn());
            }
            Bukkit.broadcastMessage("§7✝ §f" + Main.getINSTANCE().getTeam(victim) + " " + victim.getName() + " §7a été tué(e) par §b" + attacker);
        }

    }

    public Location getBlueSpawn() {
        return blueSpawn;
    }

    public Location getRedSpawn() {
        return redSpawn;
    }
    public Location getGreenSpawn() {
        return greenSpawn;
    }
}
