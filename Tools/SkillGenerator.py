def generateNewSkill(name):
    x="""
// Put this in its own class and call it 'Skill%s'
package com.symcs.cRPG.Skills;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;

public class Skill%s extends Skill{
	
	public Skill%s(cRPG plugin){
		super(plugin);
		
		setSkillItem(new ItemStack(Material.APPLE));
		setSkillName("%s");
		setSkillDescription(Arrays.asList("sample skill", "description"));
		setSkillCooldown(3);
		setSkillDamage(1.0);
		//setSkillIgnoreFriendly(); //Use this if you want the skill to completely ignore allies (onSkillHitFriendly will never be called however)
		setSkillDifferentOnAllies(); //Required for onSkillHitFriendly to be triggered (Allies are defined as party members)
	}
	
	@Override
	public void Cast(){
        //Remember to make it register hits, this can be done in various ways
        //Either by using projectiles and using registerProjectile(projectile)
        //Or by using hitboxes (just make a hitbox and use hitbox.registerHits(this)

        //To deal damage to an entity via this skill use
        //this.dealDamage(livingEntity, damage);
        //or
        //this.dealDamage(livingEntity);
        //this deals the damage you set with
        //setSkillDamage(damage);
	}
	

	@Override
	public void onSkillHitPlayer(Player hit){
	//Will be called when the skill hits a player	
	}
	
	@Override
	public void onSkillHitEntity(LivingEntity hit){
	//Will be called when the skill hits a living entity that is NOT a player	
	}

	
	@Override
	public void onSkillHit(LivingEntity hit){
	//Will be called when the skill hits a living entity, be it a player or a mob
	//Mostly usefull if there are no distinct difference on behaviour for player and mobs
	//If you want though, you can just put the same code in
	//onSkillHitPlayer and onSkillHitEntity
	}
	
	@Override
	public void onSkillHitFriendly(Player hit){
	//If you have used setSkillDifferentOnAllies()
	//this will be called when the the skill hits allies
	//If you didn't set this setting, this will never be called
	}

	@Override
	public void onProjectileLand(Location loc){
	//if you register a projectile with something along the lines of
	//registerProjectile(this.player.launchProjectile(Fireball.class));
	//You can use this method to see when and where it lands.
	}

}
"""%(name, name, name,name)
    return x

nam = raw_input("Name:")
data = generateNewSkill(nam)
print data
with open("Skill" + nam + ".java", "w") as f:
    f.write(data)

raw_input("Press enter to exit...")
