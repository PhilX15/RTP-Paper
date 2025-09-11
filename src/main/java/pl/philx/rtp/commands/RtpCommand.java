package pl.philx.rtp.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NullMarked;

import java.util.Random;

@NullMarked
public class RtpCommand implements BasicCommand {
    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        if (!(commandSourceStack.getSender() instanceof Player sender)) {
           return;
        }

        Random randomNumberGenerator = new Random();
        final int TELEPORT_RANGE = 500;

        Location playerLocation = sender.getLocation();
        final int randomX = playerLocation.getBlockX() + getRandomCoordinate(randomNumberGenerator, TELEPORT_RANGE);
        final int randomZ = playerLocation.getBlockZ() + getRandomCoordinate(randomNumberGenerator, TELEPORT_RANGE);
        Location randomLocation = new Location(sender.getWorld(), randomX, 80, randomZ);

        sender.teleport(randomLocation);
    }

    private int getRandomCoordinate(Random rng, int range) {
        return rng.nextInt(2 * range) - range;
    }
}
