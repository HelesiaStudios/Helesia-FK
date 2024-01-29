package fr.helesia.fallenkingdoms.listeners.player;

import fr.helesia.fallenkingdoms.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
public class PlayerDeathEvent implements Listener {

    @EventHandler
    public void Death(org.bukkit.event.entity.PlayerDeathEvent e){
        e.setDeathMessage(null);
        Player victim = (Player) e.getEntity();
        Player attacker = victim.getKiller();
        victim.spigot().respawn();
        victim.setHealth(victim.getMaxHealth());
        victim.resetMaxHealth();
        victim.setGameMode(GameMode.SPECTATOR);

        for(Player players : Bukkit.getOnlinePlayers()){
            if(attacker.getName().equals(players.getPlayer().getName())){
                Bukkit.broadcastMessage("§7✝ §f" + Main.getINSTANCE().getTeam(victim) + " " + victim.getName() + " §7a été tué(e) par §f" + Main.getINSTANCE().getTeam(attacker) + " " + attacker.getName());
            }

            Bukkit.broadcastMessage("§7✝ §f" + Main.getINSTANCE().getTeam(victim) + " " + victim.getName() + " §7a été tué(e) par §b" + attacker.getName());
        }

    }
}
