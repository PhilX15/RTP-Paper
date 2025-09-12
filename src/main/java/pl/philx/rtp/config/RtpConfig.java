package pl.philx.rtp.config;

import org.bukkit.Material;
import org.bukkit.block.Biome;

import java.util.List;

public class RtpConfig {
    private int teleportRange;
    private final int oceanLevel;
    private final int teleportAttempts;
    private final List<Material> blockBlacklist = List.of(Material.AIR, Material.MAGMA_BLOCK, Material.LAVA, Material.WATER, Material.BEDROCK, Material.POWDER_SNOW, Material.CAMPFIRE, Material.FIRE, Material.BIG_DRIPLEAF, Material.SOUL_CAMPFIRE, Material.ACACIA_LEAVES, Material.AZALEA_LEAVES, Material.BIRCH_LEAVES, Material.CHERRY_LEAVES, Material.FLOWERING_AZALEA_LEAVES, Material.JUNGLE_LEAVES, Material.MANGROVE_LEAVES, Material.OAK_LEAVES, Material.DARK_OAK_LEAVES, Material.PALE_OAK_LEAVES, Material.SPRUCE_LEAVES);
    private final List<Biome> biomeBlacklist = List.of(Biome.OCEAN, Biome.COLD_OCEAN, Biome.DEEP_COLD_OCEAN, Biome.DEEP_OCEAN, Biome.DEEP_FROZEN_OCEAN, Biome.DEEP_LUKEWARM_OCEAN, Biome.LUKEWARM_OCEAN, Biome.WARM_OCEAN);

    public RtpConfig(int teleportRange, int oceanLevel, int teleportAttempts) {
        this.teleportRange = teleportRange;
        this.oceanLevel = oceanLevel;
        this.teleportAttempts = teleportAttempts;
    }

    public void setTeleportRange(int teleportRange) {
        this.teleportRange = teleportRange;
    }

    public int getTeleportRange() {
        return teleportRange;
    }

    public int getOceanLevel() {
        return oceanLevel;
    }

    public int getTeleportAttempts() {
        return teleportAttempts;
    }

    public List<Biome> getBiomeBlacklist() {
        return biomeBlacklist;
    }

    public List<Material> getBlockBlacklist() {
        return blockBlacklist;
    }
}
