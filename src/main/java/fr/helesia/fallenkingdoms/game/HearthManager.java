package fr.helesia.fallenkingdoms.game;

import java.util.HashMap;

import org.bukkit.entity.EnderCrystal;

public class HearthManager {

	public int heart;
	public String name;
	public EnderCrystal ec;
	
	public static HashMap<String, HearthManager> enderCrystals = new HashMap<>();
	
	public HearthManager(EnderCrystal ec, String name) {
		this.name = name;
		this.heart = 500;
		this.ec = ec;
		enderCrystals.put(name, this);
	}
	
	public String getName() {
		return name;
	}
	
	public int getLife() {
		return heart;
	}
	
	public void setHeart(int heart) {
		this.heart = heart;
	}
	
	public void removeHeart(int i) {
		this.heart-=i;
	}

	public EnderCrystal getEc() {
		return ec;
	}
}