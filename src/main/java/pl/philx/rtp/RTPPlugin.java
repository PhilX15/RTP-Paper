package pl.philx.rtp;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pl.philx.rtp.commands.ReloadCommand;
import pl.philx.rtp.commands.RtpCommand;
import pl.philx.rtp.commands.SetRangeCommand;
import pl.philx.rtp.config.RtpConfig;
import pl.philx.rtp.services.RtpService;

import java.io.File;

public class RTPPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveResource("config.yml", false);
        }

        RtpConfig rtpConfig = new RtpConfig(100, getConfig().getInt("rtp_range"), 63, 10);
        RtpService rtpService = new RtpService(rtpConfig);

        Bukkit.getPluginManager().registerEvents(this, this);

        registerCommand("rtp", new RtpCommand(rtpService));
        registerCommand("reload", new ReloadCommand(this, rtpConfig));
        registerCommand("setrange", new SetRangeCommand(this, rtpConfig));
    }
}