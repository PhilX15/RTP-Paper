package pl.philx.rtp;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pl.philx.rtp.commands.*;
import pl.philx.rtp.config.RtpConfig;
import pl.philx.rtp.services.RtpService;

import java.io.File;

public class RTPPlugin extends JavaPlugin implements Listener {
    public static final int OCEAN_LEVEL = 63;
    public static final int TELEPORT_ATTEMPTS = 10;
    private RtpConfig rtpConfig;

    @Override
    public void onEnable() {
        initConfigFile();
        initConfig();
        RtpService rtpService = new RtpService(rtpConfig);

        initCommands(rtpService);
    }

    private void initCommands(RtpService rtpService) {
        registerCommand("rtp", new RtpCommand(rtpService));
        registerCommand("reload", new ReloadCommand(this));
        registerCommand("setrange", new SetRangeCommand(this));
    }

    private void initConfig() {
        rtpConfig = new RtpConfig(OCEAN_LEVEL, TELEPORT_ATTEMPTS);
        loadFromConfig();
    }

    private void initConfigFile() {
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveResource("config.yml", false);
        }
        reloadConfig();
    }

    public RtpConfig getRtpConfig() {
        return rtpConfig;
    }

    public void updateRange(int range) {
        getConfig().set("rtp-range", range);
        saveConfig();
        rtpConfig.setTeleportRange(range);
    }

    public void loadFromConfig() {
        long cooldown = getConfig().getLong("rtp-cooldown", 60);
        int range = getConfig().getInt("rtp-range", 500);
        int minRange = getConfig().getInt("min-rtp-range", 100);
        int maxRange = getConfig().getInt("max-rtp-range", 5000);
        rtpConfig.setTeleportCooldown(cooldown);
        rtpConfig.setTeleportRange(range);
        rtpConfig.setMinTeleportRange(minRange);
        rtpConfig.setMaxTeleportRange(maxRange);
    }
}