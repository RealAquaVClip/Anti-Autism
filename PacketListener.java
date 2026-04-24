package com.arc.antiautism.listeners;

import com.arc.antiautism.AntiAutism;
import com.arc.antiautism.Check;
import com.arc.antiautism.PlayerData;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.entity.Player;

public class PacketListener {

    public void start(AntiAutism plugin) {
        ProtocolManager pm = ProtocolLibrary.getProtocolManager();

        // listens to movement packets
        pm.addPacketListener(new PacketAdapter(plugin,
                com.comphenix.protocol.PacketType.Play.Client.POSITION,
                com.comphenix.protocol.PacketType.Play.Client.POSITION_LOOK,
                com.comphenix.protocol.PacketType.Play.Client.FLYING) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                Player player = event.getPlayer();
                PlayerData data = plugin.getDataManager().getPlayerData(player);
                // feeds all movement checks
                for (Check check : plugin.getCheckManager().getChecks()) {
                    if (check.getName().startsWith("Speed") ||
                        check.getName().startsWith("Fly") ||
                        check.getName().startsWith("Scaffold")) {
                        check.run(player, data, event.getPacket());
                    }
                }
            }
        });
        // combat packets (use PlayerListener for events if easier)
        // we use a simple Bukkit listener for combat events
    }
}