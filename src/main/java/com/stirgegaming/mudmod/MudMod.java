package com.stirgegaming.mudmod;

import com.stirgegaming.mudmod.core.event.EventsHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = MudMod.MODID, name = MudMod.NAME, version = MudMod.VERSION)
public class MudMod
{
    public static final String MODID = "mudmod";
    public static final String NAME = "Mud Mod";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("MudMod Loading Up");

    }

    @Mod.EventHandler
    public void load( FMLInitializationEvent event ) {
        MinecraftForge.EVENT_BUS.register( new EventsHandler() );
    }

}
