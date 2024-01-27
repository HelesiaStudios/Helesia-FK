package fr.helesia.fallenkingdoms.runnables;

import fr.helesia.fallenkingdoms.Main;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class SuicideMessage extends BukkitRunnable {

    @Override
    public void run() {
        Bukkit.broadcastMessage(Main.getINSTANCE().getPrefix() + "Si tu es coincé, tu peux utiliser la commande §b/suicide§f.");
    }

}
