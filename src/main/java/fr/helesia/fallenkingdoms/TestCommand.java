package fr.helesia.fallenkingdoms;

import fr.helesia.fallenkingdoms.runnables.LobbyRunnable;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("start")){
			new LobbyRunnable().runTaskTimer(Main.getINSTANCE(), 0L, 20L);
			Main.getINSTANCE().lobbyRunnable.start = true;
		}

		return true;
	}
}