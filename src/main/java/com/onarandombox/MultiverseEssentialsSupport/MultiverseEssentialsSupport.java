package com.onarandombox.MultiverseEssentialsSupport;

import com.earth2me.essentials.api.IEssentials;
import com.onarandombox.MultiverseCore.api.MultiversePlugin;
import com.pneumaticraft.commandhandler.multiverse.CommandHandler;

public class MultiverseEssentialsSupport extends MultiversePlugin {
    @Override
    public int getProtocolVersion() {
        return 18;
    }

    @Override
    protected void onPluginEnable() {
        IEssentials essentials = (IEssentials) this.getServer().getPluginManager().getPlugin("Essentials");
        if (essentials == null) // dafuq are you doing, Bukkit
            throw new IllegalStateException("Bukkit, y u no enforce dependencies??");

        EssentialsWarpDestination.setWarps(essentials.getWarps());
        this.getCore().getDestFactory().registerDestinationType(EssentialsWarpDestination.class,
                new EssentialsWarpDestination().getIdentifier());
    }

    @Override
    public void onDisable() {
    }

    @Override
    protected void registerCommands(CommandHandler handler) {
        // no commands (yet?)
    }
}
