package fi.makelord95.simpleteleport;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TPHereCommand implements CommandExecutor {
    private final TeleportRequest teleportRequest;

    public TPHereCommand(TeleportRequest teleportRequest) {
        this.teleportRequest = teleportRequest;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can use this command!");
            return true;
        }

        if (strings.length != 1) {
            commandSender.sendMessage("Usage: /tpahere <player>");
            return true;
        }

        Player target = commandSender.getServer().getPlayer(strings[0]);

        if (target == null) {
            commandSender.sendMessage("Player not found!");
            return false;
        }

        TextComponent.Builder requestMessage = Component.text()
                .append(Component.text(commandSender.getName()).color(NamedTextColor.BLUE)) // Player name in blue
                .append(Component.text(" wants to teleport you to them. Type ")
                        .color(NamedTextColor.YELLOW))
                .append(Component.text("/tpaccept").color(NamedTextColor.GREEN))
                .append(Component.text(" to accept or "))
                .append(Component.text("/tpdeny").color(NamedTextColor.RED))
                .append(Component.text(" to deny."))
                .color(NamedTextColor.YELLOW);

        target.sendMessage(requestMessage.build());

        teleportRequest.setSender(commandSender.getName());
        teleportRequest.setTarget(strings[0]);
        teleportRequest.setType("tpahere");

        commandSender.sendMessage("Teleport request sent to " + strings[0]);

        return true;
    }
}
