package com.stirgegaming.mudmod.core.stats;

import com.stirgegaming.mudmod.MudMod;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.StatList;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MMPlayerStats {
    public int deaths;
    public int mobkills;
    public float steps;

    public void setDeaths(int x) {
        deaths = x;
    }

    public void setMobKills(int x) {
        mobkills = x;
    }

    public void setSteps(int x) {
        steps = x/100f;
    }

    public void GetPlayerStats( EntityPlayerMP player) {
        setDeaths(player.getStatFile().readStat(StatList.DEATHS)+1);
        setMobKills(player.getStatFile().readStat(StatList.MOB_KILLS));
        setSteps(player.getStatFile().readStat(StatList.WALK_ONE_CM));
    }

    public void WritePlayerStats( EntityPlayerMP player ) {
        String fileName = "MudMod/"+player.getName()+"_stats.xml";
        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("<mmstats>");
            bw.write( "<playerName>"+player.getName()+"</player>");
            bw.write( "<deaths>"+deaths+"</deaths>");
            bw.write( "<mobkills>"+mobkills+"</mobkills>");
            bw.write( "<steps>"+steps+"</steps>");
            bw.write( "</mmstats>");
            bw.newLine();

            bw.close();
        }
        catch(IOException ex) {
            MudMod.logger.error( "Error writing to file "+fileName+" "
                    +ex.getMessage());
        }
    }
}
