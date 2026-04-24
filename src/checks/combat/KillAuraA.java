package com.arc.antiautism.checks.combat;

import com.arc.antiautism.AntiAutism;
import com.arc.antiautism.Check;
import com.arc.antiautism.PlayerData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class KillAuraA extends Check {
    public KillAuraA() { super("KillAura", 20); }

    @Override
    public void run(Player player, PlayerData data, Object... args) {
        if (!(args[0] instanceof EntityDamageByEntityEvent)) return;
        EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) args[0];

        // cps check (coarse)
        long now = System.currentTimeMillis();
        long diff = now - data.lastClickTime;
        if (diff < 50) { // 20 or more cps
            flag(player, "CPS " + (1000 / diff) + " > 20");
        }
        data.lastClickTime = now;

        // check consistency of aim (simple direction change threshold)
        // this would require storing previous hit vectors, omitted for brevity
    }
}
