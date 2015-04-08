package com.symcs.cRPG.utils;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.v1_8_R1.BlockPosition;
import net.minecraft.server.v1_8_R1.ChatClickable;
import net.minecraft.server.v1_8_R1.ChatMessage;
import net.minecraft.server.v1_8_R1.EnumClickAction;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;
import net.minecraft.server.v1_8_R1.PacketPlayOutWorldEvent;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NMSFunctions {
	
	//public PacketPlayOutWorldEvent(int paramInt1, BlockPosition paramBlockPosition, int paramInt2, boolean paramBoolean)
	public static void sendIngameEffect(Location loc, int EffectID, int Data, Player sendTo){
		if(!(loc.getWorld() == sendTo.getWorld())){return;}
	    PacketPlayOutWorldEvent packet = new PacketPlayOutWorldEvent(EffectID, new BlockPosition(loc.getX(), loc.getY(), loc.getZ()), Data, false);
	    ((CraftPlayer)sendTo).getHandle().playerConnection.sendPacket(packet);
	}
	
	public static void sendIngameEffect(Player p, int EffectID, int Data){sendIngameEffect(p, EffectID, Data, p);}
	public static void sendIngameEffect(Player p, int EffectID, int Data, Player sendTo){sendIngameEffect(p.getLocation(), EffectID, Data, sendTo);}
	
	/*
	 * In the ItemStack class (This is the part likely to break on update)
   public IChatBaseComponent C()
  {
    ChatComponentText chatcomponenttext = new ChatComponentText(getName());
    if (hasName()) {
      chatcomponenttext.getChatModifier().setItalic(Boolean.valueOf(true));
    }
    IChatBaseComponent ichatbasecomponent = new ChatComponentText("[").addSibling(chatcomponenttext).a("]");
    if (this.item != null)
    {
      NBTTagCompound nbttagcompound = new NBTTagCompound();
      
      save(nbttagcompound);
      ichatbasecomponent.getChatModifier().setChatHoverable(new ChatHoverable(EnumHoverAction.SHOW_ITEM, new ChatComponentText(nbttagcompound.toString())));
      ichatbasecomponent.getChatModifier().setColor(u().e);
    }
    return ichatbasecomponent;
  }
	 */

	
	public static void sendItemInMessage(Player toPlayer, ItemStack stack, String message){
    	net.minecraft.server.v1_8_R1.ItemStack cstack = CraftItemStack.asNMSCopy(stack);
    	IChatBaseComponent iChat = cstack.C();
    	message = message.replace("<item>", "%1$s");
    	ChatMessage msg = new ChatMessage(message, new Object[] {iChat});
    	PacketPlayOutChat packet = new PacketPlayOutChat(msg);
    	((CraftPlayer) toPlayer).getHandle().playerConnection.sendPacket(packet);
    	

	}
	
	public static void sendItemInMessageWithCommand(Player toPlayer, ItemStack stack, String message, String command){ //remember / on command
    	net.minecraft.server.v1_8_R1.ItemStack cstack = CraftItemStack.asNMSCopy(stack);
    	IChatBaseComponent iChat = cstack.C();
    	message = message.replace("<item>", "%1$s");
    	
    	iChat.getChatModifier().setChatClickable(new ChatClickable(EnumClickAction.RUN_COMMAND, command)); //adds a hidden command on click
    	
    	ChatMessage msg = new ChatMessage(message, new Object[] {iChat});
    	PacketPlayOutChat packet = new PacketPlayOutChat(msg);
    	((CraftPlayer) toPlayer).getHandle().playerConnection.sendPacket(packet);
    	
	}
	
	public static void sendItemInMessage(Player toPlayer, List<ItemStack> stacks, String message){
		String ireplacement = "<item>";
		for(int i=1; i<=stacks.size(); i++){
			String substr = "%" + Integer.toString(i) + "$s";
			int si = message.indexOf(ireplacement);
			if(si == -1){break;}
			int ei = si + ireplacement.length();
			String p1 = message.substring(0 , si);
			String p2 = message.substring(ei);
			message = p1 + substr + p2;
			}
		List<IChatBaseComponent> iChats = new ArrayList<IChatBaseComponent>();
		for(ItemStack istack: stacks){
			net.minecraft.server.v1_8_R1.ItemStack cstack = CraftItemStack.asNMSCopy(istack);
			IChatBaseComponent iChat = cstack.C();
			iChats.add(iChat);
		}
		Object[] obj = new Object[iChats.size()];
		for(int i=0;i<iChats.size();i++){
			obj[i] = iChats.get(i);
		}
		
		ChatMessage msg = new ChatMessage(message, obj);
    	PacketPlayOutChat packet = new PacketPlayOutChat(msg);
    	((CraftPlayer) toPlayer).getHandle().playerConnection.sendPacket(packet);
	}
	
	public static void sendItemInMessageWithCommand(Player toPlayer, List<ItemStack> stacks, String message, List<String> commands){
		String ireplacement = "<item>";
		for(int i=1; i<=stacks.size(); i++){
			String substr = "%" + Integer.toString(i) + "$s";
			int si = message.indexOf(ireplacement);
			if(si == -1){break;}
			int ei = si + ireplacement.length();
			String p1 = message.substring(0 , si);
			String p2 = message.substring(ei);
			message = p1 + substr + p2;
			}
		List<IChatBaseComponent> iChats = new ArrayList<IChatBaseComponent>();
		
		int commandi = 0;
		for(ItemStack istack: stacks){
			net.minecraft.server.v1_8_R1.ItemStack cstack = CraftItemStack.asNMSCopy(istack);
			IChatBaseComponent iChat = cstack.C();
			
			iChat.getChatModifier().setChatClickable(new ChatClickable(EnumClickAction.RUN_COMMAND, commands.get(commandi)));
			
			iChats.add(iChat);
			commandi++;
		}
		Object[] obj = new Object[iChats.size()];
		for(int i=0;i<iChats.size();i++){
			obj[i] = iChats.get(i);
		}
		
		ChatMessage msg = new ChatMessage(message, obj);
    	PacketPlayOutChat packet = new PacketPlayOutChat(msg);
    	((CraftPlayer) toPlayer).getHandle().playerConnection.sendPacket(packet);
	}
}
