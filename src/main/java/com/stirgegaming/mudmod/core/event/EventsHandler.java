package com.stirgegaming.mudmod.core.event;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Iterator;

import static com.stirgegaming.mudmod.core.event.util.StringUtils.ordinal;

public class EventsHandler {

    @SubscribeEvent( priority = EventPriority.LOWEST)
    public void onEntityLivingDeath( LivingDeathEvent event ) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            if( event.getEntityLiving() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) event.getEntity();
                int deathCount = ((EntityPlayerMP)player).getStatFile().readStat(StatList.DEATHS)+1;
                int mobKills = ((EntityPlayerMP)player).getStatFile().readStat(StatList.MOB_KILLS);

                Iterator<EntityPlayer> i = ((EntityPlayerMP) player).getServerWorld().playerEntities.iterator();
                while( i.hasNext() ) {
                    i.next().sendMessage( new TextComponentString( player.getName()+" "+
                            ChatFormatting.RED+"died"+ChatFormatting.WHITE+" for the "+
                            ordinal(deathCount)+ " time having "+ChatFormatting.GREEN+"destroyed"+ChatFormatting.WHITE+
                            " "+mobKills+" monsters."));
                }
            }
        }
    }
}

