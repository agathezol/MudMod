package com.stirgegaming.mudmod;

import com.stirgegaming.mudmod.MudMod;
import com.stirgegaming.mudmod.items.ItemStatsClipboard;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid=MudMod.MODID)
public class ModItems {
    static Item statsClipboard;

    public static void init() {
        MudMod.logger.info("initializing items");
        statsClipboard = new ItemStatsClipboard();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event ) {
        MudMod.logger.info("registering items");
        event.getRegistry().registerAll(statsClipboard);
    }

    private static void registerRender(Item item ) {
        ModelLoader.setCustomModelResourceLocation(item, 0,
                new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event ) {
        MudMod.logger.info("registering renders");
        registerRender(statsClipboard);
    }
}
