package com.onarandombox.MultiverseEssentialsSupport;

import com.earth2me.essentials.api.IWarps;
import com.onarandombox.MultiverseCore.api.MVDestination;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class EssentialsWarpDestination implements MVDestination {
    private static boolean CHECK_ESSENTIALS_PERMS;
    private static IWarps WARPS;

    private String name;

    public static void setStatics(final IWarps WARPS, final boolean checkEssentialsPerms) {
        EssentialsWarpDestination.WARPS = WARPS;
        EssentialsWarpDestination.CHECK_ESSENTIALS_PERMS = checkEssentialsPerms;
    }

    @Override
    public String getIdentifier() {
        return "esw";
    }

    @Override
    public boolean isThisType(final JavaPlugin plugin, final String destination) {
        return destination.startsWith(getIdentifier() + ":");
    }

    @Override
    public Location getLocation(final Entity entity) {
        try {
            return WARPS.getWarp(name);
        } catch (Exception e) {
            // "throws Exception"? Seriously?
            // Wow this is some shitty coding. Did I mention that I don't really like Essentials?
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Vector getVelocity() {
        return new Vector();
    }

    @Override
    public void setDestination(final JavaPlugin plugin, final String destination) {
        this.name = destination.substring(getIdentifier().length() + 1);
    }

    @Override
    public boolean isValid() {
        return getLocation(null) != null;
    }

    @Override
    public String getType() {
        return "Essentials Warp";
    }

    @Override
    public String getName() {
        return "Essentials Warp: " + name;
    }

    @Override
    public String getRequiredPermission() {
        return CHECK_ESSENTIALS_PERMS ? "essentials.warps." + name : "";
    }

    @Override
    public boolean useSafeTeleporter() {
        return false;
    }
}
