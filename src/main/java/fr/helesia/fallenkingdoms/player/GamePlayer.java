package fr.helesia.fallenkingdoms.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import fr.helesia.fallenkingdoms.utils.RegionManager;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;

import fr.helesia.fallenkingdoms.scoreboard.ScoreboardManager;

public class GamePlayer {

	private String name;
	private List<UUID> team;
	
	public boolean isInBase;
	public RegionManager region;

	public List<Block> fours = new ArrayList<>();
	
	public static Map<String, GamePlayer> gamePlayers = new HashMap<>();
	public ScoreboardManager scoreboard;
	
	public GamePlayer(String name) {
		this.name = name;
		this.team = new ArrayList<UUID>();
		this.isInBase = false;

		scoreboard = new ScoreboardManager(Bukkit.getPlayer(name));
		gamePlayers.put(name, this);
	}
	
	public String getName() {
		return name;
	}
	
	public void setTeam(List<UUID> team) {
		this.team = team;
	}
	
	public List<UUID> getTeam() {
		return team;
	}
}