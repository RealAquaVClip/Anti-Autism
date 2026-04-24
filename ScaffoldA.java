package com.arc.antiautism.checks.movement;

import com.arc.antiautism.AntiAutism;
import com.arc.antiautism.Check;
import com.arc.antiautism.PlayerData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;

public class ScaffoldA extends Check {
    public ScaffoldA() { super("Scaffold", 15); }

    @Override
    public void run(Player player, PlayerData data, Object... args) {
        if (!(args[0] instanceof BlockPlaceEvent)) return;
        BlockPlaceEvent event = (BlockPlaceEvent) args[0];

       // check if player is on the very edge of the block they placed (which is dumb and stupid but it works so im not complaining)
        Block placed = event.getBlock();
        Block below = placed.getRelative(0, -1, 0);
        if (below.getType() == Material.AIR) {
            // they are bridging, check angle
            if (AntiAutism.getInstance().getConfig().getBoolean("checks.scaffold.expand-bridge")) {
                // additional sneak / angle checks
                flag(player, "suspicious bridge placement");
            }
        }
    }
}