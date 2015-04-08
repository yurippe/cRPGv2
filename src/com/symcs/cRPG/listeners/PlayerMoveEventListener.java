package com.symcs.cRPG.listeners;


import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import com.symcs.cRPG.cRPG;
 
public final class PlayerMoveEventListener implements Listener {

    public cRPG plugin;
	
    public PlayerMoveEventListener(cRPG plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;

    }
    
 
    @EventHandler
    public void onEvent(PlayerMoveEvent event)
    {
        

    }

}
