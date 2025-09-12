package pl.philx.rtp.config;

public class RtpConfig {
    private final int teleportRange;
    private final int oceanLevel;
    private final int teleportAttempts;

    public RtpConfig(int teleportRange, int oceanLevel, int teleportAttempts) {
        this.teleportRange = teleportRange;
        this.oceanLevel = oceanLevel;
        this.teleportAttempts = teleportAttempts;
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
}
