package com.symcs.cRPG.Skills;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;
import com.symcs.cRPG.utils.Hitboxes.HitboxCollection;

public class SkillBlessedStrings extends Skill{

	public SkillBlessedStrings(cRPG plugin) {
		super(plugin);
		
		setSkillItem(new ItemStack(Material.APPLE));
		setSkillName("Sample skill name");
		setSkillDescription(Arrays.asList("sample skill", "description"));
		setSkillCooldown(3);
		//setSkillIgnoreFriendly();
		//setSkillDifferentOnAllies();
	}
	
	@Override
	public void Cast(){
		HitboxCollection hitbox = new HitboxCollection(plugin, plugin.getPartyManager().getPartyMembersOrPlayer(this.player));
		hitbox.registerHits(this);
	}
	
	@Override
	public void onSkillHitPlayer(Player p){
		
		p.addPotionEffect(PotionEffectType.REGENERATION.createEffect(1200, 2));
		//TODO status effect with increased dmg
		p.sendMessage("Got buffed with Blessed Strings - Regeneration and Strength increased for 6 seconds");
		
	}

}
