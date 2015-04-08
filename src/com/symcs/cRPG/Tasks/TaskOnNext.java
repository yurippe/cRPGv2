package com.symcs.cRPG.Tasks;

import org.bukkit.scheduler.BukkitRunnable;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.utils.ConditionalAction;

public class TaskOnNext extends BukkitRunnable{
	
	protected cRPG plugin;
	protected ConditionalAction condition;
	private int timeout;
	
	public TaskOnNext(cRPG plugin, ConditionalAction condition, int delayTicks, int timeoutTicks){
		this.plugin = plugin;
		this.condition = condition;
		this.timeout = timeoutTicks;
		this.runTaskTimer(plugin,delayTicks,1);
	}
	
	public TaskOnNext(cRPG plugin, ConditionalAction condition){
		this(plugin, condition, 1, -1);
	}
	public TaskOnNext(cRPG plugin, ConditionalAction condition, int delayTicks){
		this(plugin, condition, delayTicks, -1);
	}

	@Override
	public void run() 
	{
		if(this.condition.If()){this.condition.Then(); this.cancel();}
		if(!(this.timeout == -1)){if(this.timeout <= 1){this.cancel();}else{this.timeout--;}}
	}
	

}
