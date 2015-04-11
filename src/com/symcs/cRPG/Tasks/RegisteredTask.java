package com.symcs.cRPG.Tasks;

import com.symcs.cRPG.cRPG;

public class RegisteredTask extends Task{
	
	protected cRPG plugin;
	
	protected int times;
	
	
	public RegisteredTask(cRPG plugin){
		this(plugin, -1);
	}
	
	public RegisteredTask(cRPG plugin, int times){
		super(plugin);
		this.times = times;

	}
	
	public void setTimes(int times){
		this.times = times;
	}
	
	
	
	@Override
	public void run() {
		if(!(this.times == -1)){if(this.times <= 1){this.times--;this.cancel();}else{this.times--;}}
	}
	
	

}
