package com.symcs.cRPG.utils;

import org.bukkit.event.Event;

import com.symcs.cRPG.BaseClasses.Skill;


public class EventReservation {
	protected Skill skill;
	
	public EventReservation(Skill callback){
		this.skill = callback;
	}
	
	//Override this for any real effect, else you just get the first event that triggers
	public boolean If(Event e){
		return true;
	}
	
	public void Then(Event e){
		this.skill.EventReservationCallback(e);
	}
	
	public boolean Check(Event e){
		if(If(e)){Then(e); return true;}
		return false;
	}
	
	

}
