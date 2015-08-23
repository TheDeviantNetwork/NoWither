package com.thedeviantnetwork.nowither;

import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.object.Coord;
import com.palmergames.bukkit.towny.object.TownyWorld;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityEvent;

import org.bukkit.plugin.java.JavaPlugin;

public class NoWitherPlugin extends JavaPlugin implements Listener {

    private Towny towny;

    @Override
    public void onEnable() {
        towny = ((Towny)getServer().getPluginManager().getPlugin("Towny"));
        getServer().getPluginManager().registerEvents(this,this);
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event){
        if(event.getEntityType().equals(EntityType.WITHER) && inprotectedland(event.getEntity().getLocation())){
            event.setCancelled(true);
        }
    }

    public boolean inprotectedland(Location location) {
        try{
            TownyWorld world = this.towny.getTownyUniverse().getWorldMap().get(location.getWorld().getName());
            return world.getTownBlock(Coord.parseCoord(location)) != null;
        }catch (Exception e){}
        return false;
    }
}
