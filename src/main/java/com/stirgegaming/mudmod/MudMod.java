package com.stirgegaming.mudmod;

import com.stirgegaming.mudmod.core.events.EventsHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = MudMod.MODID, name = MudMod.NAME, version = MudMod.VERSION)
public class MudMod
{
    public static final String MODID = "mudmod";
    public static final String NAME = "Mud Mod";
    public static final String VERSION = "1.0";

    public static final Logger logger = LogManager.getLogger(MudMod.MODID);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ModItems.init();
        ModRecipes.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("MudMod Loading Up");

    }

    @Mod.EventHandler
    public void load( FMLInitializationEvent event ) {
        // register our event handlers
        MinecraftForge.EVENT_BUS.register( new EventsHandler() );

        // create the storage directory for our mod
        File directory = new File("MudMod");
        if( !directory.exists()) {
            if( !directory.mkdir() ) {
                logger.error("Could not create directory: MudMod");
            }
        }
    }

}
