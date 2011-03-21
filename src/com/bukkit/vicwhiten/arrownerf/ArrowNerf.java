//The Package
package com.bukkit.vicwhiten.arrownerf;
//All the imports
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;




public class ArrowNerf extends JavaPlugin{
	//constants for easy access
   public HashMap<String,Boolean> canShoot = new HashMap<String,Boolean>();
   public int arrowDelay = 500;
	

	
    //called when plugin is disabled
	public void onDisable() {
		System.out.println("ArrowNerf Disabled");
	
	}

	//called when plugin starts
	public void onEnable() {
		System.out.println("ArrowNerf Enabled");
		PluginManager pm = getServer().getPluginManager();
	    pm.registerEvent(Event.Type.PLAYER_ITEM, new ArrowFiredListener(this), Event.Priority.High, this);
	    
	}

	public void hasShot(String playerName)
	{
		
		Timer timer = new Timer();
		timer.schedule(new AllowShot(playerName, this), arrowDelay);
	}
	
	class AllowShot extends TimerTask{

		String playerName;
		ArrowNerf p;
		public AllowShot(String name, ArrowNerf plugin)
		{
			playerName = name;
			p = plugin;
			System.out.println("Timer Set!");
		}
		public void run() {
			
			p.canShoot.put(playerName, true);
			System.out.println("Timer Done!");
		}
		
	}
	
	   

}
