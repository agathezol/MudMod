package com.stirgegaming.mudmod.core.events;

import com.stirgegaming.mudmod.core.stats.MMPlayerStats;
import com.stirgegaming.mudmod.core.util.StringUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventsHandler {

    @SubscribeEvent( priority = EventPriority.LOWEST)
    public void onEntityLivingDeath( LivingDeathEvent event ) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            if( event.getEntityLiving() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) event.getEntity();
                MMPlayerStats  mmps = new MMPlayerStats();
                mmps.GetPlayerStats(((EntityPlayerMP)player));
                for( EntityPlayer e : ((EntityPlayerMP)player).getServerWorld().playerEntities )  {
                    e.sendMessage( new TextComponentString( player.getName()+" "+
                            TextFormatting.RED+"died"+TextFormatting.WHITE+" for the "+
                            StringUtils.ordinal(mmps.deaths+1)+ " time having "+TextFormatting.GREEN+"destroyed"+TextFormatting.WHITE+
                            " "+mmps.mobkills+" monsters."));
                }
                mmps.WritePlayerStats(((EntityPlayerMP)player));
            }
        }
    }
}

