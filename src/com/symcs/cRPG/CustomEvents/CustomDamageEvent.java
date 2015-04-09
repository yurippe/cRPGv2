package com.symcs.cRPG.CustomEvents;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CustomDamageEvent extends Event implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
 
    private LivingEntity damager;
    private LivingEntity damagee;
    private double damage;
    
    public CustomDamageEvent(LivingEntity damagee, LivingEntity damager, double damage) {
    	this.damager = damager;
    	this.damagee = damagee;
    	this.damage = damage;
    }
    
    public CustomDamageEvent(LivingEntity damagee, double damage){
    	this(damagee, null, damage);
    }
 
    public double getDamage(){
    	return this.damage;
    }
    
    public void setDamage(double dmg){
    	this.damage = dmg;
    }
    
    public LivingEntity getDamager(){
    	return this.damager;
    }
    
    public LivingEntity getDamagee(){
    	return this.damagee;
    }
    
    
    
    
    
    public boolean isCancelled() {
        return cancelled;
    }
 
    public void setCancelled(boolean bln) {
        this.cancelled = bln;
    }

    public HandlerList getHandlers() {
        return handlers;
    }
 
    public static HandlerList getHandlerList() {
        return handlers;
    }
 
}
