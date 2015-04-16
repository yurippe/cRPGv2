package com.symcs.cRPG.BaseClasses;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import com.symcs.cRPG.cRPG;

public class Skill {
	
	//Skill types (not yet used for anything)
	public enum Type{DEFAULT, TOTEM}
	public enum CastFrom{PLAYER, ENTITY, WORLD}
	
	protected Type SkillType = Type.DEFAULT;
	protected CastFrom SkillCastFrom = CastFrom.PLAYER;
	
	protected cRPG plugin;
	private int skillCooldown = 0;
	protected double damage = 0.0D;
	
	protected Player player;
	protected Entity entity;
	
	public ItemStack skillItem;
	
	private String skillName = "";
	private List<String> skillDescription = Arrays.asList("");
	private boolean ignoreAllies=false;
	private boolean specialEffectIfHitAllies=false;
	
	public Skill(cRPG plugin){
		this.plugin = plugin;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public void setPlayer(Player player){
		this.player = player;
		this.entity = (Entity) player;
	}
	
	public void setEntity(Entity entity){
		this.entity = entity;
	}
	
	public Entity getEntity(){
		return this.entity;
	}
	
	public cRPG getPlugin(){
		return this.plugin;
	}
	
	public void setSkillDamage(double dmg){
		this.damage = dmg;
	}
	
	public double getSkillDamage(){
		return this.damage;
	}
	
	public void setIgnoreAllies(boolean b_ignoreAllies){this.ignoreAllies = b_ignoreAllies;}
	public void setIgnoreAllies(){setIgnoreAllies(true);}
	//aliases:
	public void setSkillIgnoreAllies(boolean b_ignoreAllies){setIgnoreAllies(b_ignoreAllies);}
	public void setSkillIgnoreFriendly(boolean b_ignoreAllies){setIgnoreAllies(b_ignoreAllies);}
	public void setSkillIgnoreAllies(){setIgnoreAllies(true);}
	public void setSkillIgnoreFriendly(){setIgnoreAllies(true);}
	
	public boolean getIgnoreAllies(){return this.ignoreAllies;}
	//alias
	public boolean ignoreAllies(){return getIgnoreAllies();}
	
	public void setSkillDifferentOnAllies(boolean b_special){this.specialEffectIfHitAllies = b_special;}
	public void setSkillDifferentOnAllies(){setSkillDifferentOnAllies(true);}
	
	public boolean getSkillDifferentOnAllies(){return this.specialEffectIfHitAllies;}
	
	public void setCastFrom(CastFrom type){
		this.SkillCastFrom = type;
	}
	
	public CastFrom getCastFrom(){
		return this.SkillCastFrom;
	}

	public void setSkillName(String name){
		skillName = name;
		ItemMeta temp = this.skillItem.getItemMeta();
		temp.setDisplayName(name);
		this.skillItem.setItemMeta(temp);
	}
	
	public String getSkillName(){
		return this.skillName;
	}
	
	public void setSkillItem(ItemStack item){
		this.skillItem = item;
	}
	
	
	public ItemStack getSkillItem(){
		return this.skillItem;
	}
	
	protected void setSkillDescription(List<String> list){
		
		skillDescription = list;
		ItemMeta temp = this.skillItem.getItemMeta();
		temp.setLore(list);;
		this.skillItem.setItemMeta(temp);
	}
	
	public List<String> getSkillDescription(){
		return this.skillDescription;
	}
	protected void setSkillCooldown(int seconds){
		//assuming 1 second = 20 ticks, can't be guaranteed!
		this.skillCooldown = seconds;	
	}
	public int getSkillCooldown(){
		return this.skillCooldown;
	}
	
	public void setSkillType(Type stype){
		this.SkillType = stype;
	}
	
	public Type getSkillType(){
		return this.SkillType;
	}
	protected Projectile registerProjectile(Projectile projectile){
		return plugin.getProjectileManager().registerProjectile(projectile, this);
	}
	protected void setSkillBlock(Block b){
		b.setMetadata("SkillBlock", new FixedMetadataValue(plugin, this));
	}
	protected void unsetSkillBlock(Block b){
		b.removeMetadata("SkillBlock", plugin);
	}
	
	//public EntityDamageByEntityEvent onDamageDeal(EntityDamageByEntityEvent event){
	@Deprecated
	public void onProjectileHitPlayer(EntityDamageByEntityEvent event){	
		onSkillHitPlayer((Player)event.getEntity());
	}
	
	@Deprecated
	public void onProjectileHitEntity(EntityDamageByEntityEvent event){
		if(event.getEntity() instanceof LivingEntity){
		onSkillHitEntity((LivingEntity)event.getEntity());}
	}
	
	//can be overriden to add extra effects on hit
	public void onSkillHitPlayer(Player hit){
		
	}
	
	//can be overriden to add extra effects on hit
	public void onSkillHitEntity(LivingEntity hit){
		
	}
	
	//can be overriden to add extra effects on hit friendly
	public void onSkillHitFriendly(Player hit){
		
	}
	
	//can be overriden to add extra effects on hit
	public void onSkillHitEntityOrPlayer(LivingEntity hit){
		onSkillHit(hit);
	}
	//alias for onSkillHitEntityOrPlayer
	public void onSkillHit(LivingEntity hit){
		
	}
	
	//can override to get location of where a projectile lands
	public void onProjectileLand(Location loc){
		
	}
	
	protected void dealDamage(LivingEntity entity, double dmg){
		plugin.getDamageManager().createCustomDamageEvent(entity, this.player, dmg);
	}
	
	protected void dealDamage(LivingEntity entity){
		plugin.getDamageManager().createCustomDamageEvent(entity, this.player, this.damage);
		//dealDamage(entity, this.damage);
	}
	
	public void Cast(){
		
	}
	
	
}
