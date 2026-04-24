package com.arc.antiautism;

import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;

public class PlayerData {
    private final Player player;
    private final Map<Check, Violation> violations = new HashMap<>();
    // velocity/movement history
    public double lastX, lastY, lastZ;
    public long lastMoveTime;
    // combat history
    public int cps;
    public long lastClickTime;

    public PlayerData(Player player) {
        this.player = player;
    }

    public Violation getViolation(Check check) {
          return violations.computeIfAbsent(check, k -> new Violation());
    }

    public Player getPlayer() { return player; }
}
