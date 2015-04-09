package com.symcs.cRPG.utils.Hitboxes;

import java.util.List;

import org.bukkit.entity.LivingEntity;

import com.symcs.cRPG.cRPG;

//fake hitbox for convenience, always returns it's list
public class HitboxCollection extends Hitbox{
	
	private List<? extends LivingEntity> ents;

	public HitboxCollection(cRPG plugin, List<? extends LivingEntity> list) {
		super(plugin);
		this.ents = list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LivingEntity> getEntitiesHit() {
		return (List<LivingEntity>)this.ents;
	}

}
