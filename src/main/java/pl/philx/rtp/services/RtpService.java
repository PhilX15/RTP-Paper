package pl.philx.rtp.services;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import pl.philx.rtp.config.RtpConfig;
import pl.philx.rtp.exceptions.NoSafeLocationFoundException;

import java.util.Random;

public class RtpService {
    private final RtpConfig rtpConfig;

    public RtpService(RtpConfig rtpConfig) {
        this.rtpConfig = rtpConfig;
    }

    public Location findSafeLocation(World world, Location center) throws NoSafeLocationFoundException {
        int attempts = rtpConfig.getTeleportAttempts();

        Random randomNumberGenerator = new Random();
        while (attempts > 0) {
            Location randomLocation = pickRandomLocation(world, center, randomNumberGenerator);

            if (isSafeLocation(randomLocation)) {
                return randomLocation;
            }

            attempts--;
        }

        throw new NoSafeLocationFoundException();
    }

    private @NotNull Location pickRandomLocation(World world, Location center, Random randomNumberGenerator) {
        final int randomX = center.getBlockX() + getRandomCoordinate(randomNumberGenerator, rtpConfig.getTeleportRange());
        final int randomZ = center.getBlockZ() + getRandomCoordinate(randomNumberGenerator, rtpConfig.getTeleportRange());

        return new Location(world, randomX, rtpConfig.getOceanLevel(), randomZ);
    }

    private boolean isSafeLocation(Location location) {
        Location blockAbove = location.clone().add(0, 1, 0);
        return (location.getBlock().getType() == Material.AIR && blockAbove.getBlock().getType() == Material.AIR);
    }

    private int getRandomCoordinate(Random rng, int range) {
        return rng.nextInt(2 * range) - range;
    }
}
