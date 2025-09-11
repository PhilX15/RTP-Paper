package pl.philx.rtp.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NullMarked;
import pl.philx.rtp.exceptions.NoSafeLocationFoundException;

import java.util.Random;

@NullMarked
public class RtpCommand implements BasicCommand {

    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        if (!(commandSourceStack.getSender() instanceof Player sender)) {
            return;
        }

        sender.sendRichMessage("<yellow>Teleporting...</yellow>");

        Location playerLocation = sender.getLocation();
        Random randomNumberGenerator = new Random();
        int teleportRange = 50;
        int oceanLevel = 63;
        int attempts = 10;

        try {
            Location destination = findASafeDestinationLocation(sender, attempts, playerLocation, randomNumberGenerator, teleportRange, oceanLevel);
            sender.sendRichMessage("<green>Successful teleport</green>");
            sender.teleport(destination);
        } catch (NoSafeLocationFoundException e) {
            sender.sendRichMessage("<red>Unable to find a safe place to teleport</red>");
        }
    }

    private @NotNull Location findASafeDestinationLocation(Player sender, int attempts, Location playerLocation, Random randomNumberGenerator, int teleportRange, int oceanLevel) throws NoSafeLocationFoundException {
        while (attempts > 0) {
            final int randomX = playerLocation.getBlockX() + getRandomCoordinate(randomNumberGenerator, teleportRange);
            final int randomZ = playerLocation.getBlockZ() + getRandomCoordinate(randomNumberGenerator, teleportRange);

            Location randomLocation = new Location(sender.getWorld(), randomX, oceanLevel, randomZ);

            if (isSafeLocation(randomLocation)) {
                return randomLocation;
            }

            attempts--;
        }

        throw new NoSafeLocationFoundException();
    }

    private boolean isSafeLocation(Location location) {
        Location blockAbove = location.clone().add(0, 1, 0);
        return (location.getBlock().getType() == Material.AIR && blockAbove.getBlock().getType() == Material.AIR);
    }

    private int getRandomCoordinate(Random rng, int range) {
        return rng.nextInt(2 * range) - range;
    }
}
