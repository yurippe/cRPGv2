package com.symcs.cRPG.utils.AnimationEffects;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.Tasks.RegisteredTask;
import com.symcs.cRPG.Tasks.Task;
import com.symcs.cRPG.Tasks.UnregisteredTask;

public abstract class Effect {
	
	public enum Type{DEFAULT, REGISTERED}
	
	public enum EffectType{
		//http://wiki.vg/Protocol#Effect
		SMOKE(2000, true), BLOCK_BREAK(2001,true), SPLASH_POTION(2002, true), MOB_SPAWN(2004, false), GREEN_CROSS(2005, false);
		
		private int EffectID;
		private boolean reqData;
		private EffectType(int EffectID, boolean requireData){this.EffectID = EffectID; this.reqData = requireData;}
		int getEffectID(){return EffectID;}
		boolean requiresData(){return reqData;}
	}
	
	protected cRPG plugin;
	protected Type type;
	protected int MaxTicks;
	protected boolean runBackwards=true;
	private Task task;
	private Location loc;
	
	public Effect(cRPG plugin,int ticks){this(plugin, ticks, Type.DEFAULT);}
	public Effect(cRPG plugin){this(plugin, 1, Type.DEFAULT);}
	public Effect(cRPG plugin, int ticks, Type type){
		this.plugin = plugin;
		this.type = type;
		
		if(type == Type.DEFAULT){
			this.task = new UnregisteredTask(plugin, ticks){
				
			private Effect effect;
			public UnregisteredTask setEffect(Effect e){
				this.effect = e;
				return this;
			}
			@Override
			public void run(){
				super.run();
				if(this.effect.runBackwards){this.effect.effect(this.times);}
				else{this.effect.effect(this.effect.getMaxTicks() - this.times);}
				if(this.times == 0){effect.onFade();}
			}
		}.setEffect(this);}
		
		else if(type == Type.REGISTERED){
			this.task = new RegisteredTask(plugin, ticks){
				
				private Effect effect;
				public RegisteredTask setEffect(Effect e){
					this.effect = e;
					return this;
				}
				@Override
				public void run(){
					super.run();
					if(this.effect.runBackwards){this.effect.effect(this.times);}
					else{this.effect.effect(this.effect.getMaxTicks() - this.times);}
					if(this.times == 0){effect.onFade();}
				}
			}.setEffect(this);}
		
		setIterations(ticks);
		
	}
	
	public void sendTo(Player player){
		
	}
	
	public void sendAll(){
		for(Player p:plugin.getServer().getOnlinePlayers()){
			sendTo(p);
		}
	}
	
	public int getMaxTicks(){
		return this.MaxTicks;
	}
	
	public void setLocation(Location loc){
		this.loc = loc;
	}
	public Location getLocation(){
		return this.loc;
	}
	
	public void setRunBackwards(){setRunBackwards(true);}
	public void setRunBackwards(boolean b){
		this.runBackwards = b;
	}
	public boolean isRunningBackwards(){
		return this.runBackwards;
	}
	
	public void setIterations(int iterations){
		if(this.task instanceof UnregisteredTask){
			((UnregisteredTask) this.task).setTimes(iterations);
			this.MaxTicks = iterations;
		}
		else if(this.task instanceof RegisteredTask){
			((RegisteredTask) this.task).setTimes(iterations);
			this.MaxTicks = iterations;
		}
	}
	
	public void play(int period){
		this.task.runTaskTimer(this.plugin, 0, period);
	}
	
	//tick is decreasing from max value to 0
	public abstract void effect(int tick);
	public abstract void onFade();

}
