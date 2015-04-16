package com.symcs.cRPG.Managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.utils.EventReservation;

public class ListenerManager {
	
	@SuppressWarnings("unused")
	private cRPG plugin;
	private HashMap<String, Listener> Listeners = new HashMap<String, Listener>();
	
	//////
	private HashMap<Listener, ArrayList<EventReservation>> Reservations = new HashMap<Listener, ArrayList<EventReservation>>();
	
	public ListenerManager(cRPG plugin){
		this.plugin = plugin;
	}
	
	public void addListener(String commonname, Listener listener){
		this.Listeners.put(commonname, listener);
	}
	//---------------------------------------------------------------------------------//
	public void reserveNextEvent(String eventname, EventReservation reservation){//not including Event & case sensitive
		if(eventname.endsWith("Event")){
			eventname = eventname.substring(0, eventname.length() - 5);
		}
		
		Listener key = this.Listeners.get(eventname);
		if(key == null){return;}
		
		if(this.Reservations.containsKey(key)){
			this.Reservations.get(key).add(reservation);
		}else{
			ArrayList<EventReservation> a = new ArrayList<EventReservation>();
			a.add(reservation);
			this.Reservations.put(key, a);
		}
		
	}

	public boolean isReserved(Listener l) {
		
		return this.Reservations.containsKey(l);
		
	}
	
	public void PassToReserver(Listener l, Event e){
		if((this.Reservations.containsKey(l))){
		Iterator<EventReservation> iter = this.Reservations.get(l).iterator();
		while(iter.hasNext()){
			EventReservation er = iter.next();
			if(er.Check(e)){iter.remove();}
			}
		if(this.Reservations.get(l).size() == 0){
			this.Reservations.remove(l); //is empty now, dont keep it
		}
		}
	}
	
	

}
