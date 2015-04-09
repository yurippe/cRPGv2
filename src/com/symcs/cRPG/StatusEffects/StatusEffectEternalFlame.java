package com.symcs.cRPG.StatusEffects;

import com.symcs.cRPG.BaseClasses.StatusEffect;

import java.util.Collection;

import org.bukkit.entity.Player;

import com.symcs.cRPG.StatusEffects.Generic.StatusEffectDeathImmunity;
import com.symcs.cRPG.Tasks.UnregisteredTask;
import com.symcs.cRPG.utils.NMSFunctions;


public class StatusEffectEternalFlame extends StatusEffect{

	public StatusEffectEternalFlame(int charges, int timelimit) {
		super(charges, timelimit);
	}
	
	@Override
	public void onCast(){
		
	}
	
	@Override
	public void onFade(StatusEffect.FadeCause cause){
		
		if(cause == StatusEffect.FadeCause.CHARGES_SPENT){
		this.player.sendMessage("Eternal Flame triggered, you will die in 5 seconds");
		this.playerdata.getStatusEffectManager().addStatusEffect(new StatusEffectDeathImmunity(5){
			
			@Override
			public void onCast(){
				Collection<? extends Player> sendTo = plugin.getServer().getOnlinePlayers();
				
				new UnregisteredTask(plugin, 5){
					private Collection<? extends Player> sendto;
					private Player p;
					public UnregisteredTask args(Collection<? extends Player> sendto, Player effectPlayer){this.sendto = sendto;this.p=player;return this;}
					@Override
					public void run(){super.run();for(Player pst: sendto){NMSFunctions.sendIngameEffect(this.p, 2001, 11, pst);}}
				}.args(sendTo, this.player).runTaskTimer(plugin, 20, 20);
			}
			
			@Override
			public void onFade(){
				
				this.player.sendMessage("Eternal Flame fades");
				this.player.setHealth(0.0);
			}
		});}else{this.player.sendMessage("Eternal flame fades");}
	}
	
	@Override
	public boolean canDie(){return false;}

}
