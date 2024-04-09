package fi.makelord95.simpleteleport;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SimpleTeleport extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("SimpleTeleport has been enabled!");

        TeleportRequest teleportRequest = TeleportRequest.getInstance();

        Objects.requireNonNull(getCommand("tpa")).setExecutor(new TPACommand(teleportRequest));
        Objects.requireNonNull(getCommand("tpahere")).setExecutor(new TPHereCommand(teleportRequest));

        Objects.requireNonNull(getCommand("tpaccept")).setExecutor(new TPAcceptCommand(teleportRequest));
        Objects.requireNonNull(getCommand("tpdeny")).setExecutor(new TPDenyCommand(teleportRequest));
    }

    @Override
    public void onDisable() {
        getLogger().info("SimpleTeleport has been disabled!");
    }
}
