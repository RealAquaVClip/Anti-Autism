package com.arc.antiautism;

import org.bukkit.entity.Player;

public abstract class Check {
    protected final String name;
    protected final int maxVL;
    protected boolean enabled;

    public Check(String name, int maxVL) {
        this.name = name;
        this.maxVL = maxVL;
        this.enabled = true; // can be overriden from config
    }

    // all argument types depend on the check.
    public abstract void run(Player player, PlayerData data, Object... args);

    // call when the check triggers.
    protected void flag(Player player, String details) {
        Violation vl = AntiAutism.getInstance().getDataManager()
                  .getPlayerData(player).getViolation(this);
        vl.addVL();
        if (vl.getVL() >= maxVL) {
            String cmd = AntiAutism.getInstance().getConfig()
                    .getString("punishment.command")
                    .replace("%player%", player.getName());
            AntiAutism.getInstance().getServer().dispatchCommand(
                    AntiAutism.getInstance().getServer().getConsoleSender(), cmd);
            vl.reset();
        }
        // send yo own alerts, im too broke for a website :(
    }

    public String getName() { return name; }
}