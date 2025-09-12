package pl.philx.rtp.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.jetbrains.annotations.Nullable;
import pl.philx.rtp.RTPPlugin;
import pl.philx.rtp.config.RtpConfig;

public class SetRangeCommand implements BasicCommand {
    private final RTPPlugin rtpPlugin;
    private final RtpConfig rtpConfig;

    public SetRangeCommand(RTPPlugin rtpPlugin, RtpConfig rtpConfig) {
        this.rtpPlugin = rtpPlugin;
        this.rtpConfig = rtpConfig;
    }

    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        if (args.length != 1) {
            commandSourceStack.getSender().sendRichMessage("<red>You must specify a value for the teleport range!</red>");
            return;
        }

        try {
            int range = Integer.parseInt(args[0]);

            if (range < rtpConfig.getMinRange()) {
                commandSourceStack.getSender().sendRichMessage("<red>RTP range cannot be less than " + rtpConfig.getMinRange() + " blocks</red>");
                return;
            }

            rtpPlugin.getConfig().set("rtp_range", range);
            rtpPlugin.saveConfig();
            rtpConfig.setTeleportRange(range);

            commandSourceStack.getSender().sendRichMessage("<green>RTP range successfully changed</green>");
        } catch (NumberFormatException e) {
            commandSourceStack.getSender().sendRichMessage("<red>Invalid range!</red>");
        }
    }

    @Override
    public @Nullable String permission() {
        return "rtp.setrange";
    }
}
