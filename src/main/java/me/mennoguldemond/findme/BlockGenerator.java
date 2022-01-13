package me.mennoguldemond.findme;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class BlockGenerator {

    public static void createBox(Player player, int x, int y, int z) {
        new Location(player.getWorld(), x, y, z).getBlock().setType(Material.GLASS);

        new Location(player.getWorld(), x + 1, y, z).getBlock().setType(Material.GLASS);
        new Location(player.getWorld(), x + 1, y + 1, z).getBlock().setType(Material.GLASS);
        new Location(player.getWorld(), x + 1, y + 2, z).getBlock().setType(Material.GLASS);

        new Location(player.getWorld(), x - 1, y, z).getBlock().setType(Material.GLASS);
        new Location(player.getWorld(), x - 1, y + 1, z).getBlock().setType(Material.GLASS);
        new Location(player.getWorld(), x - 1, y + 2, z).getBlock().setType(Material.GLASS);

        new Location(player.getWorld(), x, y, z + 1).getBlock().setType(Material.GLASS);
        new Location(player.getWorld(), x, y + 1, z + 1).getBlock().setType(Material.GLASS);
        new Location(player.getWorld(), x, y + 2, z + 1).getBlock().setType(Material.GLASS);

        new Location(player.getWorld(), x, y, z - 1).getBlock().setType(Material.GLASS);
        new Location(player.getWorld(), x, y + 1, z - 1).getBlock().setType(Material.GLASS);
        new Location(player.getWorld(), x, y + 2, z - 1).getBlock().setType(Material.GLASS);
    }

    public static void CreateHomeTower(World world) {
        int y = 255;
        while(y>0){
            if(new Location(world, 0, y, 0).getBlock().getType() != Material.AIR)
                break;
            y--;
        }

        for (int i = 0; i < 50; i++) {
            new Location(world, 0, y + i, 0).getBlock().setType(Material.DIAMOND_BLOCK);
        }
    }

}
