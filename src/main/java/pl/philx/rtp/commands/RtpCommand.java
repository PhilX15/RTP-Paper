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

        Location playerLocation = sender.getLocation();
        Random randomNumberGenerator = new Random();
        int teleportRange = 500;
        final int randomX = playerLocation.getBlockX() + getRandomCoordinate(randomNumberGenerator, teleportRange);
        final int randomZ = playerLocation.getBlockZ() + getRandomCoordinate(randomNumberGenerator, teleportRange);

        int oceanLevel = 63;
        Location randomLocation = new Location(sender.getWorld(), randomX, oceanLevel, randomZ);

        sender.teleport(randomLocation);
    }

    private int getRandomCoordinate(Random rng, int range) {
        return rng.nextInt(2 * range) - range;
    }
}
