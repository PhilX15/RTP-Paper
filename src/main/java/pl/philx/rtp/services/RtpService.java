package pl.philx.rtp.services;

import org.bukkit.*;
import org.bukkit.block.Biome;
import org.jetbrains.annotations.NotNull;
import pl.philx.rtp.config.RtpConfig;
import pl.philx.rtp.exceptions.NoSafeLocationFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class RtpService {
    private final RtpConfig rtpConfig;
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public RtpService(RtpConfig rtpConfig) {
        this.rtpConfig = rtpConfig;
    }

    public void enableCooldown(UUID uuid) {
        cooldowns.put(uuid, System.currentTimeMillis());
    }

    public Long getCooldown(UUID uuid) {
        if (!cooldowns.containsKey(uuid)) {
            return 0L;
        }

        return ((cooldowns.get(uuid) + rtpConfig.getTeleportCooldown() * 1000) - System.currentTimeMillis());
    }

    public Location findSafeLocation(World world, Location center) throws NoSafeLocationFoundException {
        int attempts = rtpConfig.getTeleportAttempts();

        Random randomNumberGenerator = new Random();
        while (attempts > 0) {
            Location randomLocation = pickRandomLocation(world, center, randomNumberGenerator);
            if (isAllowedBiome(randomLocation.getBlock().getBiome())) {
                Location safeLocation = findSafeY(randomLocation);
                if (safeLocation != null) {
                    return safeLocation;
                }
            }

            attempts--;
        }

        throw new NoSafeLocationFoundException();
    }

    private @NotNull Location pickRandomLocation(World world, Location center, Random randomNumberGenerator) {
        final double randomX = center.getBlockX() + getRandomCoordinate(randomNumberGenerator, rtpConfig.getTeleportRange()) + 0.5;
        final double randomZ = center.getBlockZ() + getRandomCoordinate(randomNumberGenerator, rtpConfig.getTeleportRange()) + 0.5;

        return new Location(world, randomX, world.getMaxHeight(), randomZ);
    }

    private Location findSafeY(Location location) {
        Location locationClone = location.clone();

        while (locationClone.getBlockY() >= rtpConfig.getOceanLevel()) {
            Location blockUnder = locationClone.clone().subtract(0, 1, 0);
            Location blockAbove = locationClone.clone().add(0, 1, 0);

            if (locationClone.getBlock().getType() == Material.AIR && blockAbove.getBlock().getType() == Material.AIR && isAllowedBlock(blockUnder.getBlock().getType()) && blockUnder.getBlock().isSolid()) {
                return locationClone;
            }

            locationClone.add(0, -1, 0);
        }

        return null;
    }

    private boolean isAllowedBlock(Material block) {
        return !rtpConfig.getBlockBlacklist().contains(block);
    }

    private boolean isAllowedBiome(Biome biome) {
        return !rtpConfig.getBiomeBlacklist().contains(biome);
    }

    private int getRandomCoordinate(Random rng, int range) {
        return rng.nextInt(2 * range + 1) - range;
    }
}
