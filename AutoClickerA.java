package com.arc.antiautism.checks.combat;

import com.arc.antiautism.Check;
import com.arc.antiautism.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

public class AutoClickerA extends Check {
    public AutoClickerA() { super("AutoClicker", 20); }

    @Override
    public void run(Player player, PlayerData data, Object... args) {
        if (!(args[0] instanceof PlayerInteractEvent)) return;
        PlayerInteractEvent event = (PlayerInteractEvent) args[0];
        if (event.getAction().toString().contains("LEFT_CLICK")) {
            // store timing and compute standard deviation after X clicks
            // flag if deviation < threshold (very consistent = autoclicker)
        }
    }
}