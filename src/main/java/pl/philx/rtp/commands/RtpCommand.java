package pl.philx.rtp.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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

        sender.sendRichMessage("<yellow>Teleporting...</yellow>");

        Location playerLocation = sender.getLocation();
        Location destination = null;
        Random randomNumberGenerator = new Random();
        int teleportRange = 50;
        int oceanLevel = 63;
        int attempts = 10;
        while (attempts > 0) {
            final int randomX = playerLocation.getBlockX() + getRandomCoordinate(randomNumberGenerator, teleportRange);
            final int randomZ = playerLocation.getBlockZ() + getRandomCoordinate(randomNumberGenerator, teleportRange);

            Location randomLocation = new Location(sender.getWorld(), randomX, oceanLevel, randomZ);

            if (isSafeLocation(randomLocation)) {
                destination = randomLocation;
                break;
            }

            attempts--;
        }

        if (destination == null) {
            sender.sendRichMessage("<red>Unable to find a self place to teleport</red>");
            return;
        }

        sender.sendRichMessage("<green>Successful teleport</green>");
        sender.teleport(destination);
    }

    private int getRandomCoordinate(Random rng, int range) {
        return rng.nextInt(2 * range) - range;
    }

    private boolean isSafeLocation(Location location) {
        Location blockAbove = location.add(0, 1, 0);
        return (location.getBlock().getType() == Material.AIR && blockAbove.getBlock().getType() == Material.AIR);
    }
}
