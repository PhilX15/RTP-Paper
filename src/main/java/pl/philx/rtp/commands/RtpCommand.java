package pl.philx.rtp.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NullMarked;
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

        sender.sendRichMessage("<yellow>Teleporting...</yellow>");

        try {
            Location destination = rtpService.findSafeLocation(sender.getWorld(), sender.getLocation());
            sender.sendRichMessage("<green>Successful teleport</green>");
            sender.teleport(destination);
        } catch (NoSafeLocationFoundException e) {
            sender.sendRichMessage("<red>Unable to find a safe place to teleport</red>");
        }
    }
}
