package pl.philx.rtp.commands;

import io.papermc.paper.command.brigadier.*;
import org.jspecify.annotations.Nullable;
import pl.philx.rtp.RTPPlugin;

public class ReloadCommand implements BasicCommand {
    private final RTPPlugin rtpPlugin;

    public ReloadCommand(RTPPlugin rtpPlugin) {
        this.rtpPlugin = rtpPlugin;
    }

    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        rtpPlugin.reloadConfig();
        rtpPlugin.loadFromConfig();
        commandSourceStack.getSender().sendRichMessage("<green>RTPPlugin configuration reloaded successfully</green>");
    }

    @Override
    public @Nullable String permission() {
        return "rtp.reload";
    }
}
