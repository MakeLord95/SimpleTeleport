package fi.makelord95.simpleteleport;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TPDenyCommand implements CommandExecutor {
    private final TeleportRequest teleportRequest;

    public TPDenyCommand(TeleportRequest teleportRequest) {
        this.teleportRequest = teleportRequest;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can use this command!");
            return true;
        }

        if (teleportRequest.getSender() == null) {
            commandSender.sendMessage("No teleport request to deny!");
            return true;
        }

        commandSender.sendMessage("Teleport request denied!");

        teleportRequest.setSender(null);
        teleportRequest.setTarget(null);
        teleportRequest.setType(null);

        return true;
    }
}
