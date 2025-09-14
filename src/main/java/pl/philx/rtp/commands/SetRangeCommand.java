package pl.philx.rtp.commands;

import io.papermc.paper.command.brigadier.*;
import org.jetbrains.annotations.Nullable;
import pl.philx.rtp.RTPPlugin;
import pl.philx.rtp.config.RtpConfig;

public class SetRangeCommand implements BasicCommand {
    private final RTPPlugin rtpPlugin;
    private final RtpConfig rtpConfig;

    public SetRangeCommand(RTPPlugin rtpPlugin) {
        this.rtpPlugin = rtpPlugin;
        this.rtpConfig = rtpPlugin.getRtpConfig();
    }

    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        if (args.length != 1) {
            commandSourceStack.getSender().sendRichMessage("<red>You must specify a value for the teleport range!</red>");
            return;
        }

        try {
            int range = Integer.parseInt(args[0]);

            int minRange = rtpConfig.getMinTeleportRange();
            int maxRange = rtpConfig.getMaxTeleportRange();
            if (!isValidRange(range, minRange, maxRange)) {
                commandSourceStack.getSender().sendRichMessage("<red>RTP range must be between " + minRange + " and " + maxRange + "</red>");
                return;
            }

            rtpPlugin.updateRange(range);

            commandSourceStack.getSender().sendRichMessage("<green>RTP range successfully changed</green>");
        } catch (NumberFormatException e) {
            commandSourceStack.getSender().sendRichMessage("<red>Invalid range!</red>");
        }
    }

    private boolean isValidRange(int range, int minRange, int maxRange) {
        return range >= minRange && range <= maxRange;
    }

    @Override
    public @Nullable String permission() {
        return "rtp.setrange";
    }
}
