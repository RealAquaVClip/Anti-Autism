package com.arc.antiautism.checks.combat;

import com.arc.antiautism.AntiAutism;
import com.arc.antiautism.Check;
import com.arc.antiautism.PlayerData;
import com.arc.antiautism.utils.MathUtil;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

public class ReachA extends Check {

    public ReachA() {
        super("Reach", 15);
    }

    @Override
    public void run(Player player, PlayerData data, Object... args) {
        if (!(args[0] instanceof EntityDamageByEntityEvent)) return;
        EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) args[0];

        Entity damaged = event.getEntity();
        if (!(damaged instanceof LivingEntity)) return;

        double reachRaw = player.getEyeLocation().distance(damaged.getLocation());
        // add hitbox expansion (0.4 blocks for entity hitbox)
        double maxReach = AntiAutism.getInstance().getConfig().getDouble("checks.reach.max-reach") + 0.5;

        //ping compensation for the starlinkers (simplified: subtract 0.1 per 50ms of ping)
        if (AntiAutism.getInstance().getConfig().getBoolean("checks.reach.latence-compensation")) {
            int ping = MathUtil.getPing(player);
            maxReach += ping * 0.0015; // approx
        }

        if (reachRaw > maxReach) {
            flag(player, "tried reach " + String.format("%.2f", reachRaw) + " > max " + String.format("%.2f", maxReach));
        }
    }
}