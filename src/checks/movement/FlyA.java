package com.arc.antiautism.checks.movement;

import com.arc.antiautism.AntiAutism;
import com.arc.antiautism.Check;
import com.arc.antiautism.PlayerData;
import org.bukkit.entity.Player;

public class FlyA extends Check {
    public FlyA() { super("Fly", 20); }

    @Override
    public void run(Player player, PlayerData data, Object... args) {
        double deltaY = player.getLocation().getY() - data.lastY;
        if (player.isOnGround() || player.isFlying() || player.getAllowFlight()) return;

        double maxUp = AntiAutism.getInstance().getConfig().getDouble("checks.fly.vertical-ascend-limit");
        if (deltaY > maxUp) {
            flag(player, "ascended " + String.format("%.2f", deltaY) + " > max " + maxUp);
        }
    }
}
