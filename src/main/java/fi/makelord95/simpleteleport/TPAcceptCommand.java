package fi.makelord95.simpleteleport;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class TPAcceptCommand implements CommandExecutor {
    private final TeleportRequest teleportRequest;

    public TPAcceptCommand(TeleportRequest teleportRequest) {
        this.teleportRequest = teleportRequest;
    }

    private void teleportPlayer(String sender, String target, String type) {
        if (type.equals("tpa")) {
            Objects.requireNonNull(Bukkit.getPlayer(sender)).teleport(Objects.requireNonNull(Bukkit.getPlayer(target)));
        } else if (type.equals("tpahere")) {
            Objects.requireNonNull(Bukkit.getPlayer(target)).teleport(Objects.requireNonNull(Bukkit.getPlayer(sender)));
        }
    }


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can use this command!");
            return true;
        }

        if (teleportRequest.getSender() == null) {
            commandSender.sendMessage("No teleport request to accept!");
            return true;
        }

        String sender = teleportRequest.getSender();
        String target = teleportRequest.getTarget();

        Player senderPlayer = Bukkit.getPlayer(sender);
        Player targetPlayer = Bukkit.getPlayer(target);

        if (teleportRequest.getType().equals("tpa")) {
            Objects.requireNonNull(senderPlayer).sendMessage("Teleporting you to " + teleportRequest.getTarget());
            Objects.requireNonNull(targetPlayer).sendMessage("Teleporting " + teleportRequest.getSender() + " to you");

            teleportPlayer(sender, target, "tpa");

        } else if (teleportRequest.getType().equals("tpahere")) {
            Objects.requireNonNull(senderPlayer).sendMessage("Teleporting " + teleportRequest.getTarget() + " to you");
            Objects.requireNonNull(targetPlayer).sendMessage("Teleporting you to " + teleportRequest.getSender());

            teleportPlayer(sender, target, "tpahere");
        }

        teleportRequest.setSender(null);
        teleportRequest.setTarget(null);
        teleportRequest.setType(null);

        return true;
    }
}
