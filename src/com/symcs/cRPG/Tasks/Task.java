package com.symcs.cRPG.Tasks;


import org.bukkit.scheduler.BukkitRunnable;


import com.symcs.cRPG.cRPG;

public class Task extends BukkitRunnable{
	
	protected cRPG plugin;
	

	
	public Task(cRPG plugin){this(plugin, true);}
	public Task(cRPG plugin, boolean register){
		this.plugin = plugin;
		if(register){plugin.getTaskManager().registerTask(this);}

	}
	
	
	
	@Override
	public void run() {
		
	}
	
	public void cancelEarly(){
		//should reset stuff so the server can shutdown
		
		this.cancel();
	}
	
	
	@Override
	public void cancel(){
		super.cancel();
		plugin.getTaskManager().unregisterTask(this);
	}
	
	//run() will be executed ONCE after 20 ticks
	//BukkitTask task = new Task(this.plugin).runTaskLater(this.plugin, 20)
	
	//run() will be executed every 20 ticks after waiting 10 ticks
	//BukkitTask task = new Task(this.plugin).runTaskTimer(this.plugin, 10, 20)
	
	/*
	//run() will be executed ONCE after 20 ticks
    new BukkitRunnable() {
 
            @Override
            public void run() {
                // What you want to schedule goes here
                plugin.getServer().broadcastMessage(
                    "Welcome to Bukkit! Remember to read the documentation!");
            }
 
        }.runTaskLater(this.plugin, 20);
	 */
	
	/*
	 
	  new Task(this.plugin){
	  			
	  			@Override
	  			public void run(){
	  				plugin.getLogger().info("a Task made me say this 20 ticks ago");
	  			}
	  	
	  }.runTaskLater(this.plugin, 20)
	 
	 */
	
	/*
	 	//Pretty complicated but obvious:
	 	new Task(this.plugin){
			
				SkillFireball skill;
				
				public Task addArgs(SkillFireball skill){
					this.skill = skill;
					return this;
				}
	  			
	  			@Override
	  			public void run(){
	  				plugin.getLogger().info(this.skill.Class.player.getName() + " cast a fireball 20 ticks ago");
	  			}
	  	
		}.addArgs(this).runTaskLater(this.plugin, 20);
	 
	 */

}
