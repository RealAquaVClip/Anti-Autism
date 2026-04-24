package com.arc.antiautism.utils;

import org.bukkit.entity.Player;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MathUtil {

    public static int getPing(Player player) {
        try {
            Object craftPlayer = player.getClass().getMethod("getHandle").invoke(player);
            Field pingField = craftPlayer.getClass().getDeclaredField("ping");
            pingField.setAccessible(true);
            return pingField.getInt(craftPlayer);
        } catch (Exception e) {
            return 0;
        }
    }

    public static double offset(Vector a, Vector b) {
        return a.subtract(b).length();
    }
}