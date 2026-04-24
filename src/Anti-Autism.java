package com.arc.antiautism;

import com.arc.antiautism.checks.CheckManager;
import com.arc.antiautism.listeners.PacketListener;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiAutism extends JavaPlugin {

    private static AntiAutism instance;
    private CheckManager checkManager;
    private DataManager dataManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        dataManager = new DataManager();
        checkManager = new CheckManager();

        new PacketListener().start(this);
        
        getLogger().info("AntiAutism enabled – skidwatch type shi.");
    }

    @Override
    public void onDisable() {
        getLogger().info("AntiAutism disabled.");
    }

    public static AntiAutism getInstance() { return instance; }
    public CheckManager getCheckManager() { return checkManager; }
    public DataManager getDataManager() { return dataManager; }
}
