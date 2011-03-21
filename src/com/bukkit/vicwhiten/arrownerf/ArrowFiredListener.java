package com.bukkit.vicwhiten.arrownerf;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ArrowFiredListener extends PlayerListener {
	private final ArrowNerf plugin;
	
	public ArrowFiredListener(ArrowNerf instance)
	{
		plugin = instance;
	}
	
	
	public void onPlayerItem(PlayerItemEvent event)
	{
		Player player = event.getPlayer();
		Inventory inv = player.getInventory();
    	ItemStack inHand = player.getItemInHand();
		//if the user is not using a bow, do nothing
		if(inHand.getType() != Material.BOW)
		{
			event.setCancelled(false);
			return;
		}
		event.setCancelled(true);
		//if the user isn't in the hash, they haven't even used an item yet.
		//do nothing
		if(!plugin.canShoot.containsKey(player.getName()))
		{
			plugin.canShoot.put(player.getName(), true);
		}
		
		
		ItemStack oneArrow = new ItemStack(Material.ARROW, 1);
		//if the users canShoot is true
		//fire the arrow, set the timer
		if(plugin.canShoot.get(player.getName()))
		{
			if(inv.contains(Material.ARROW)) { 
			player.shootArrow();
			
			
			inv.removeItem(oneArrow);
			plugin.canShoot.put(player.getName(), false);	
			plugin.hasShot(player.getName());
			return;
			}
		}
		player.sendMessage(ChatColor.YELLOW + "[You fumble and drop the arrow, fire slower!]");
		return;
	}
}
