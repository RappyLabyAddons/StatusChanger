package de.einsjuannn.core.command;

import net.labymod.api.Laby;
import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.network.server.ServerAddress;
import net.labymod.api.client.network.server.ServerData;
import net.labymod.api.client.network.server.ServerType;
import net.labymod.api.labyconnect.LabyConnectSession;
import net.labymod.api.util.I18n;

public class StatusCommand extends Command {

    public StatusCommand() {
        super("status");
    }

    @Override
    public boolean execute(String prefix, String[] args) {
        if (args.length < 1) {
            displayMessage(I18n.translate("labystatus.messages.usage"));
            return true;
        }
        String status = String.join(" ", args).substring(prefix.length() + 2);
        if (status.length() > 32) {
            displayMessage(I18n.translate("labystatus.messages.maxLength"));
            return true;
        }
        LabyConnectSession labyConnectSession = Laby.labyAPI().labyConnect().getSession();
        if (labyConnectSession == null) {
            displayMessage(I18n.translate("labystatus.messages.labyConnect"));
            return true;
        }
        ServerAddress serverAddress = new ServerAddress(status, 25565);
        ServerData serverData = new ServerDataImpl(status, serverAddress);

        labyConnectSession.sendCurrentServer(serverData, null, false);
        displayMessage(I18n.translate("labystatus.messages.set"));
        return true;
    }

    private static class ServerDataImpl extends ServerData {

        protected ServerDataImpl(String name, ServerAddress serverAddress) {
            super(name, serverAddress, ServerType.THIRD_PARTY);
        }
    }
}
