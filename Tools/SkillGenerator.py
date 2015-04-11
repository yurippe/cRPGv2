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
		//setSkillIgnoreFriendly(); //Use this if you want the skill to completely ignore allies (onSkillHitFriendly will never be called however)
		setSkillDifferentOnAllies(); //Required for onSkillHitFriendly to be triggered (Allies are defined as party members)
	}
	
	@Override
	public void Cast(){

	}
	

	@Override
	public void onSkillHitPlayer(Player hit){
		
	}
	
	@Override
	public void onSkillHitEntity(LivingEntity hit){
		
	}
	
	//hit can be either a player or a living entity usefull if there is no difference on the effect
	@Override
	public void onSkillHit(LivingEntity hit){
	
	}
	
	@Override
	public void onSkillHitFriendly(Player hit){
		
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
