package com.symcs.cRPG.Tasks;

import java.util.Arrays;
import java.util.List;

import org.bukkit.block.Block;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;
import com.symcs.cRPG.utils.Hitboxes.Hitbox;

public class TaskHitboxRegister extends Task{

	private int TickCount;
	private int MaxTickCount;
	private Skill skill;
	private Hitbox hitbox;
	
	private List<Block> blocks;
	private boolean registerself=false;
	
	public TaskHitboxRegister(cRPG plugin, int Ticks, Hitbox hitbox, Skill skill, List<Block> blocks){
		super(plugin);
		this.MaxTickCount = Ticks;
		this.hitbox = hitbox;
		this.skill = skill;
		this.blocks = blocks;
	}
	public TaskHitboxRegister(cRPG plugin, int Ticks, Hitbox hitbox, Skill skill, Block block){
		this(plugin, Ticks, hitbox, skill, Arrays.asList(block));
	}
	
	public TaskHitboxRegister(cRPG plugin, int Ticks, Hitbox hitbox, Skill skill) {
		super(plugin);
		this.MaxTickCount = Ticks;
		this.hitbox = hitbox;
		this.skill = skill;
		this.blocks = null;
	}
	
	public void setRegisterSelf(){setRegisterSelf(true);}
	public void setRegisterSelf(boolean b){
		this.registerself = b;
	}
	
	
	@Override
	public void cancelEarly(){
		this.TickCount = this.MaxTickCount;
	}
			
	@Override
	public void run(){
				
		try
		{
			if(TickCount <= MaxTickCount)
			{
					
  					hitbox.registerHits(this.skill);
  					if(this.registerself){hitbox.registerSelf(this.skill);}
  					
  					TickCount ++;
  			}
		}catch(Exception e){
			for(Block block:this.blocks){plugin.getBlockManager().restoreBlock(block);}
			this.cancel();
			}
				
		if(TickCount >= MaxTickCount){
			for(Block block:this.blocks){plugin.getBlockManager().restoreBlock(block);}
			this.cancel();
		}
	}
}
//.addArgs(10, this, hitbox,b).runTaskTimer(this.plugin, 0, 20);


