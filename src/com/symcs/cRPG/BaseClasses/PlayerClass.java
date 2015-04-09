package com.symcs.cRPG.BaseClasses;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import org.bukkit.Location;
import org.bukkit.Material;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.PlayerSkill;

public class PlayerClass {
	
	
	public cRPG plugin;
	public String name;
	public Player player;
	public boolean isArmed = false;
	
	protected int level = 1;
	protected int weapon_tier = 1;
	
	protected boolean unlockable = false;
	
	protected double damageMulitplier = 1.0;
	protected double defenseMultiplier = 1.0;
	protected double healingMultiplier = 1.0;
	protected double MaxHealth = 20;
	
	public HashMap<Integer, ItemStack> weapons = new HashMap<Integer, ItemStack>();
	
	private HashMap<Integer, ItemStack> hiddenItems = new HashMap<Integer, ItemStack>();
	protected HashMap<Integer, PlayerSkill> Skills = new HashMap<Integer, PlayerSkill>();
	protected HashMap<Integer, Integer> SkillUnlockLevels = new HashMap<Integer, Integer>();
	public HashMap<PlayerSkill, Integer> Keybinds = new HashMap<PlayerSkill, Integer>();
	
	
	public ItemStack weapon_placeholder = new ItemStack(Material.ARMOR_STAND);
	

	public PlayerClass(Player player, cRPG plugin){
		
		this.player = player;
		this.plugin = plugin;
		
		ItemMeta weapon_placeholder_meta = new ItemStack(Material.ARMOR_STAND).getItemMeta();
		weapon_placeholder_meta.setDisplayName("Sheathed weapon");
		weapon_placeholder_meta.setLore(Arrays.asList("Right click","to draw weapon","Left click", "to open menu"));
		this.weapon_placeholder.setItemMeta(weapon_placeholder_meta);
		
	}
	
	public void ToggleWeapon(){
		
		player.sendMessage("Toggled weapon");
		if(isArmed){
			//remove wep
			this.player.getInventory().setItem(0, weapon_placeholder);
			if(hiddenItems.containsKey(1)){player.getInventory().setItem(1, hiddenItems.get(1));}else{player.getInventory().setItem(1, null);}
			if(hiddenItems.containsKey(2)){player.getInventory().setItem(2, hiddenItems.get(2));}else{player.getInventory().setItem(2, null);}
			if(hiddenItems.containsKey(3)){player.getInventory().setItem(3, hiddenItems.get(3));}else{player.getInventory().setItem(3, null);}
			if(hiddenItems.containsKey(4)){player.getInventory().setItem(4, hiddenItems.get(4));}else{player.getInventory().setItem(4, null);}
			if(hiddenItems.containsKey(5)){player.getInventory().setItem(5, hiddenItems.get(5));}else{player.getInventory().setItem(5, null);}
			if(hiddenItems.containsKey(6)){player.getInventory().setItem(6, hiddenItems.get(6));}else{player.getInventory().setItem(6, null);}
			if(hiddenItems.containsKey(7)){player.getInventory().setItem(7, hiddenItems.get(7));}else{player.getInventory().setItem(7, null);}
			if(hiddenItems.containsKey(8)){player.getInventory().setItem(8, hiddenItems.get(8));}else{player.getInventory().setItem(8, null);}
			
		}else{
			
			//arm
			this.player.getInventory().setItem(0, weapons.get(weapon_tier));
			this.hiddenItems = new HashMap<Integer, ItemStack>();
			this.hiddenItems.put(1, player.getInventory().getItem(1));
			this.hiddenItems.put(2, player.getInventory().getItem(2));
			this.hiddenItems.put(3, player.getInventory().getItem(3));
			this.hiddenItems.put(4, player.getInventory().getItem(4));
			this.hiddenItems.put(5, player.getInventory().getItem(5));
			this.hiddenItems.put(6, player.getInventory().getItem(6));
			this.hiddenItems.put(7, player.getInventory().getItem(7));
			this.hiddenItems.put(8, player.getInventory().getItem(8));
			if(Skills.containsKey(1)){player.getInventory().setItem(1, Skills.get(1).getSkillItem());}else{player.getInventory().setItem(1, null);}
			if(Skills.containsKey(2)){player.getInventory().setItem(2, Skills.get(2).getSkillItem());}else{player.getInventory().setItem(2, null);}
			if(Skills.containsKey(3)){player.getInventory().setItem(3, Skills.get(3).getSkillItem());}else{player.getInventory().setItem(3, null);}
			if(Skills.containsKey(4)){player.getInventory().setItem(4, Skills.get(4).getSkillItem());}else{player.getInventory().setItem(4, null);}
			if(Skills.containsKey(5)){player.getInventory().setItem(5, Skills.get(5).getSkillItem());}else{player.getInventory().setItem(5, null);}
			if(Skills.containsKey(6)){player.getInventory().setItem(6, Skills.get(6).getSkillItem());}else{player.getInventory().setItem(6, null);}
			if(Skills.containsKey(7)){player.getInventory().setItem(7, Skills.get(7).getSkillItem());}else{player.getInventory().setItem(7, null);}
			if(Skills.containsKey(8)){player.getInventory().setItem(8, Skills.get(8).getSkillItem());}else{player.getInventory().setItem(8, null);}
			
		}
		
		player.updateInventory();
		this.isArmed = !(isArmed);
		
	}
	
	public void UseSkill(int slot){
		
		if(this.Skills.containsKey(slot)){
			if(this.SkillUnlockLevels.containsKey(slot)){
				int unlockedAtLvl = this.SkillUnlockLevels.get(slot);
				
				//Too low lvl:
				if(unlockedAtLvl > this.level){
					this.player.sendMessage(
					"You are too low level to use this skill (Requires level " + Integer.toString(unlockedAtLvl) + ")");
				}
				//High enough lvl:
				else{this.Skills.get(slot).Cast();}
			}
			
			//Skill is not unlockable - It is always accessible
			else{this.Skills.get(slot).Cast();}
		}
		
	}
	
	//triggered on login
	public void onLogin(){
		
		this.player.getInventory().setHeldItemSlot(0);
		this.player.getInventory().setItem(0, weapon_placeholder);
		this.isArmed = false;
		
		
	}
	
	//triggered on logout
	public void onLogout(){
		if(isArmed){ToggleWeapon();}	
	}
	
	public void onDeath(EntityDeathEvent event){
		List<ItemStack> drops = event.getDrops();
		drops.remove(player.getInventory().getItem(0));
		if(isArmed){
			for(int i=1; i<10; i++){
				drops.remove(player.getInventory().getItem(i));
				if(hiddenItems.containsKey(i)){
				drops.add(hiddenItems.get(i));
				}
			}
		}
		this.hiddenItems = new HashMap<Integer, ItemStack>();
		this.isArmed = false;
	}
	public void onRespawn(){
		this.player.getInventory().setHeldItemSlot(0);
		this.player.getInventory().setItem(0, weapon_placeholder);
		this.isArmed = false;
		this.player.updateInventory();
	}
	
	
	@Deprecated
	public void onDamageTaken(EntityDamageEvent event){
		
		if(this.player.getHealth() - event.getFinalDamage() <= 0){
			if(!(this.plugin.getPlayerManager().getPlayer((this.player)).getStatusEffectManager().canDie())){
				event.setCancelled(true);
				this.player.setHealth(1);
			}
		}
		
		double originalDMG = event.getDamage();
		double modifiedDMG = originalDMG * this.damageMulitplier;
		event.setDamage(modifiedDMG);
	}
	
	@Deprecated
	public void dealDamage(double rawDamage){
		double modifiedDMG = rawDamage * this.damageMulitplier;
		double newHP = this.player.getHealth() - modifiedDMG;
		if(newHP <= 0){newHP = 0;}
		
		this.player.setHealth(newHP);
	}
	
	//triggered when damage is taken
	@Deprecated
	public void onDamageTakenByEntity(EntityDamageByEntityEvent event){
		if(plugin.getProjectileManager().isSkillProjectile(event.getDamager())){
			//the damager was a skill projectile
			Skill damagerSkill = plugin.getProjectileManager().getSkillOfProjectile(event.getDamager());
			damagerSkill.onProjectileHitPlayer(event);
		}

	}
	
	protected void setSkill(int slot, PlayerSkill skill){
		
		this.Skills.put(slot, skill);
		this.Keybinds.put(skill, slot);
		
	}
	
	protected void setSkillUnlockLevel(int skillslot, int unlocklevel){
		this.SkillUnlockLevels.put(skillslot, unlocklevel);
	}
	
	public String getClassName(){
		return this.name;
	}
	
	public void setClassName(String name){
		this.name = name;
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public void levelUp(){
		this.level += 1;
	}
	public void levelUp(int levels){
		for(int x = 0; x < levels; x = x+1) {
			levelUp();
		}
	}
	public double getDamageMultiplier(){
		return this.damageMulitplier;
	}
	public void setDamageMultiplier(double DM){
		this.damageMulitplier = DM;
	}
	public double getHealingMultiplier(){
		return this.healingMultiplier;
	}
	public void setHealingMultiplier(double HM){
		this.healingMultiplier = HM;
	}
	public double getDefenseMultiplier(){
		return this.defenseMultiplier;
	}
	
	public void setDefenseMultiplier(double mult){
		this.defenseMultiplier = mult;
	}
	
	public void setTierWeapon(int tier, ItemStack item, String name, List<String> lore){
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(lore);
		item.setItemMeta(meta);
		weapons.put(tier, item);
	}
	public void setWeaponTier(int tier){
		this.weapon_tier = tier;
	}
	public int getWeaponTier(){
		return this.weapon_tier;
	}
	
	public void healPlayer(double healAmount){
          double maxHP = player.getMaxHealth();
          double curHP = player.getHealth();
          
          double newHP = (curHP) + (healAmount * healingMultiplier);
          if(newHP > maxHP){player.setHealth(maxHP);}
          else{player.setHealth(curHP + healAmount);}
	}
	
	
	//Fixes error in bukkit
	public Block getTargetBlock(Player player, int maxrange) {
	    Location loc = player.getEyeLocation();
	    Vector dir = loc.getDirection().normalize();
	     
	    Block b = null;
	     
	    for (int i = 0; i <= maxrange; i++) {
	    b = loc.add(dir).getBlock();
	    if(!(b.getType() == Material.AIR)){
	    	return b;
	    	}
	    }
	     
	    return b;
	}
	
	public Block getTargetBlock(int maxrange){
		return getTargetBlock(this.player, maxrange);
	}

	public Player getPlayer() {
		return this.player;
	}
	
	public cRPG getPlugin(){
		return this.plugin;
	}
	
	public void setMaxHealth(double maxhp){this.MaxHealth = maxhp;}
	public double getMaxHealth(){return this.MaxHealth;}

	public void onClassChange() {
		this.player.setMaxHealth(this.MaxHealth);
		
	}
	
	public void setUnlockable(boolean status){this.unlockable = status;}
	public void setUnlockable(){setUnlockable(true);}
	public boolean isUnlockable(){return this.unlockable;}
	
	
	
}
