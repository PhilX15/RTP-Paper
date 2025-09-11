package pl.philx.rtp;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pl.philx.rtp.commands.RtpCommand;

public class RTPPlugin extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);

        registerCommand("rtp", new RtpCommand());
    }
}