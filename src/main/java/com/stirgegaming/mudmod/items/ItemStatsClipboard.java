package com.stirgegaming.mudmod.items;

import com.mojang.realmsclient.gui.ChatFormatting;
import com.stirgegaming.mudmod.MudMod;
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
                e.sendMessage(new TextComponentString(player.getName() + " has: "+ChatFormatting.RED + "died" + ChatFormatting.RESET+ " " +
                        StringUtils.ordinal(mmps.deaths) + " times "));
                e.sendMessage(new TextComponentString( "     "+ChatFormatting.GREEN + "destroyed" + ChatFormatting.RESET+
                        " " + mmps.mobkills + " monsters"));
                e.sendMessage(new TextComponentString( "     "+ChatFormatting.YELLOW+ "moved " + ChatFormatting.RESET+
                        " " + mmps.steps + " meters"));
            }

            mmps.WritePlayerStats((EntityPlayerMP) player);
        }
        return super.onItemRightClick(world, player, hand);
    }
}
