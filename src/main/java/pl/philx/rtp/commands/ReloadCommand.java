package pl.philx.rtp.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.jspecify.annotations.Nullable;
import pl.philx.rtp.RTPPlugin;
import pl.philx.rtp.config.RtpConfig;

public class ReloadCommand implements BasicCommand {
    private final RTPPlugin rtpPlugin;
    private final RtpConfig rtpConfig;

    public ReloadCommand(RTPPlugin rtpPlugin, RtpConfig rtpConfig) {
        this.rtpPlugin = rtpPlugin;
        this.rtpConfig = rtpConfig;
    }

    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        rtpPlugin.reloadConfig();
        rtpConfig.setTeleportRange(rtpPlugin.getConfig().getInt("rtp_range"));
        commandSourceStack.getSender().sendRichMessage("<green>RTPPlugin configuration reloaded successfully</green>");
    }

    @Override
    public @Nullable String permission() {
        return "rtp.reload";
    }
}
