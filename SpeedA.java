package com.arc.antiautism.checks.movement;

import com.arc.antiautism.AntiAutism;
import com.arc.antiautism.Check;
import com.arc.antiautism.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class SpeedA extends Check {
    public SpeedA() { super("Speed", 20); }

    @Override
    public void run(Player player, PlayerData data, Object... args) {
        double x = data.lastX - player.getLocation().getX();
        double z = data.lastZ - player.getLocation().getZ();
        double horizontal = Math.sqrt(x*x + z*z);
        long delta = System.currentTimeMillis() - data.lastMoveTime;
        double speed = horizontal / (delta / 1000.0); // bps

        double maxSpeed = AntiAutism.getInstance().getConfig().getDouble("checks.speed.max-horizontal");
        // apply speed effect
        if (player.hasPotionEffect(PotionEffectType.SPEED)) {
            int level = player.getPotionEffect(PotionEffectType.SPEED).getAmplifier() + 1;
            maxSpeed *= (1 + 0.2 * level);
        }

        if (speed > maxSpeed) {
            flag(player, "speed " + String.format("%.2f", speed) + " > max " + maxSpeed);
        }

        // update last pos
        data.lastX = player.getLocation().getX();
        data.lastY = player.getLocation().getY();
        data.lastZ = player.getLocation().getZ();
        data.lastMoveTime = System.currentTimeMillis();
    }
}