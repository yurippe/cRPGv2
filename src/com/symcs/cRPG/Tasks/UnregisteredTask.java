package com.symcs.cRPG.Tasks;

import com.symcs.cRPG.cRPG;

public class UnregisteredTask extends Task{
	
	protected cRPG plugin;
	

	protected int times;
	
	public UnregisteredTask(cRPG plugin){
		this(plugin, -1);
	}
	
	public UnregisteredTask(cRPG plugin, int times){
		super(plugin, false);
		this.plugin = plugin;
		this.times = times;
	}
	
	public void setTimes(int times){
		this.times = times;
	}
	
	
	
	@Override
	public void run() {
		if(!(this.times == -1)){if(this.times <= 1){this.times--;this.cancel();}else{this.times--;}}
	}
	
	public void cancelEarly(){
		this.cancel();
	}
	
	
	@Override
	public void cancel(){
		super.cancel();
	}
	
}
