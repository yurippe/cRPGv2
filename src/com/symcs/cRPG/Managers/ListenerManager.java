package com.symcs.cRPG.Managers;

import java.util.HashMap;

import org.bukkit.event.Listener;

import com.symcs.cRPG.cRPG;

public class ListenerManager {
	
	@SuppressWarnings("unused")
	private cRPG plugin;
	private HashMap<String, Listener> Listeners = new HashMap<String, Listener>();
	public ListenerManager(cRPG plugin){
		this.plugin = plugin;
	}
	
	public void addListener(String commonname, Listener listener){
		this.Listeners.put(commonname, listener);
	}
	
	

}
