package com.stirgegaming.mudmod.items;

import com.stirgegaming.mudmod.core.stats.MMPlayerStats;
import com.stirgegaming.mudmod.core.util.StringUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemStatsClipboard extends Item {

    public ItemStatsClipboard() {
        super();
        setUnlocalizedName( "statsclipboard");
        setRegistryName("statsclipboard");
        setCreativeTab(CreativeTabs.MISC);
        setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand ) {
        if( !world.isRemote ) {
            MMPlayerStats mmps = new MMPlayerStats();
            mmps.GetPlayerStats((EntityPlayerMP) player);

            for (EntityPlayer e : ((EntityPlayerMP) player).getServerWorld().playerEntities) {
                e.sendMessage(new TextComponentString(player.getName() + " has: "+TextFormatting.RED + "died" + TextFormatting.RESET+ " " +
                        mmps.deaths + " time"+(mmps.deaths == 1 ? " " : "s ")));
                e.sendMessage(new TextComponentString( "     "+TextFormatting.GREEN + "destroyed" + TextFormatting.RESET+
                        " " + mmps.mobkills + " monsters"));
                e.sendMessage(new TextComponentString( "     "+TextFormatting.YELLOW+ "moved " + TextFormatting.RESET+
                        " " + mmps.steps + " meters"));
            }

            mmps.WritePlayerStats((EntityPlayerMP) player);
        }
        return super.onItemRightClick(world, player, hand);
    }
}
