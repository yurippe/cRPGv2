package com.symcs.cRPG.listeners;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.CustomEvents.CustomDamageEvent;
import com.symcs.cRPG.Data.PlayerData;
import com.symcs.cRPG.utils.DamageCalculator;

public final class CustomDamageEventListener implements Listener {

 public cRPG plugin;
	
 public CustomDamageEventListener(cRPG plugin) {
     plugin.getServer().getPluginManager().registerEvents(this, plugin);
     this.plugin = plugin;


 }
 

 @EventHandler
 public void onEvent(CustomDamageEvent event)
 {
  
	 DamageCalculator dcal = new DamageCalculator(plugin, event.getDamagee(), event.getDamager(), event.getDamage());
	 double finale = dcal.getFinalDamage();
	 dealDamage(event.getDamagee(), finale);
	
	 if(event.getDamager() == null){
		 String hp = " (" + Double.toString(event.getDamagee().getHealth()) + "/" + Double.toString(event.getDamagee().getMaxHealth()) + ")";
		 plugin.getLogger().info(event.getDamagee().getName() + hp + " received " + Double.toString(event.getDamage()) + " damage"); 
	 }
	 else{
		 String hp = " (" + Double.toString(event.getDamagee().getHealth()) + "/" + Double.toString(event.getDamagee().getMaxHealth()) + ")";
		 plugin.getLogger().info(event.getDamagee().getName() + hp + " received " + Double.toString(event.getDamage()) + " damage from " + event.getDamager().getName());
	 }
	 
	 

 }
 
 private void dealDamage(LivingEntity entity, double damage){
	 double newHealth = entity.getHealth() - damage;
	 if(newHealth < 0.0){newHealth = 0.0;}
	 
	 if(entity instanceof Player){
		 PlayerData pdat = plugin.getPlayerManager().getPlayer((Player) entity);
		 pdat.getStatusEffectManager().damageTaken(); //remove all status effects that cancels on damage taken
		 
		 if(newHealth == 0.0){
			 if(pdat.getStatusEffectManager().canDie() == false){
				 newHealth = 1.0;
		 	}
		 }
		 
	}
	
	entity.setHealth(newHealth);
 }

}
