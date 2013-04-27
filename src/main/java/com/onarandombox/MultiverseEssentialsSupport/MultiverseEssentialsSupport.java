package com.onarandombox.MultiverseEssentialsSupport;

import com.earth2me.essentials.api.IEssentials;
import com.onarandombox.MultiverseCore.api.MultiversePlugin;
import com.pneumaticraft.commandhandler.multiverse.CommandHandler;

public class MultiverseEssentialsSupport extends MultiversePlugin {
    @Override
    public int getProtocolVersion() {
        return 18;
    }

    private static final String PERMCHECK_CONFIG_KEY = "check-essentials-warp-permissions";

    @Override
    protected void onPluginEnable() {
        if (!this.getConfig().isSet(PERMCHECK_CONFIG_KEY)) {
            this.getConfig().set(PERMCHECK_CONFIG_KEY, false);
            this.saveConfig();
        }
        IEssentials essentials = (IEssentials) this.getServer().getPluginManager().getPlugin("Essentials");
        if (essentials == null) // dafuq are you doing, Bukkit
            throw new IllegalStateException("Bukkit, y u no enforce dependencies??");

        EssentialsWarpDestination.setStatics(essentials.getWarps(), this.getConfig().getBoolean(PERMCHECK_CONFIG_KEY));
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
