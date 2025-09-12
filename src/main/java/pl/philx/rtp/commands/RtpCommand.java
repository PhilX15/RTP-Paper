package pl.philx.rtp.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import pl.philx.rtp.exceptions.NoSafeLocationFoundException;
import pl.philx.rtp.services.RtpService;

@NullMarked
public class RtpCommand implements BasicCommand {
    private final RtpService rtpService;

    public RtpCommand(RtpService rtpService) {
        this.rtpService = rtpService;
    }

    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        if (!(commandSourceStack.getSender() instanceof Player sender)) {
            return;
        }

        if (!sender.hasPermission("rtp.rtp.bypasscooldown")) {
            long cooldown = rtpService.getCooldown(sender.getUniqueId()) / 1000L;
            if (cooldown > 0) {
                sender.sendRichMessage("<red>You must wait " + cooldown + " second(s) before using /rtp again</red>");
                return;
            }
        }

        sender.sendRichMessage("<yellow>Teleporting...</yellow>");

        try {
            Location destination = rtpService.findSafeLocation(sender.getWorld(), sender.getLocation());
            sender.sendRichMessage("<green>Successful teleport</green>");
            sender.teleport(destination);
            rtpService.enableCooldown(sender.getUniqueId());
        } catch (NoSafeLocationFoundException e) {
            sender.sendRichMessage("<red>Unable to find a safe place to teleport</red>");
        }
    }

    @Override
    public @Nullable String permission() {
        return "rtp.rtp.use";
    }
}
