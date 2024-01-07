package de.einsjuannn.core.listener;

import net.labymod.api.Laby;
import net.labymod.api.client.network.server.ServerAddress;
import net.labymod.api.client.network.server.ServerData;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatMessageSendEvent;
import net.labymod.api.labyconnect.LabyConnectSession;
import net.labymod.api.util.I18n;

public class ChatMessageSendListener {

  @Subscribe
  public void onChatMessageSend(ChatMessageSendEvent event) {
    String message = event.getMessage();
    if (message.startsWith("/status")) {
      String[] args = message.split(" ");
      if (args.length == 1) {
        Laby.references().chatExecutor().displayClientMessage(I18n.translate("labystatus.messages.usage"));
      } else if (args.length > 1) {
        String status = message.substring(8);
        if (status.length() > 32) {
          Laby.references().chatExecutor().displayClientMessage(I18n.translate("labystatus.messages.maxlenght"));
          return;
        }
        ServerAddress serverAddress = new ServerAddress(status, 25565);
        ServerData serverData = new ServerDataImpl(status, serverAddress, false);
        LabyConnectSession labyConnectSession = Laby.labyAPI().labyConnect().getSession();

        if (labyConnectSession == null) {
          return;
        }
        labyConnectSession.sendCurrentServer(serverData, null, false);
        Laby.references().chatExecutor().displayClientMessage(I18n.translate("labystatus.messages.set"));
      }
      event.setCancelled(true);
    }
  }


  private static class ServerDataImpl extends ServerData {
    public ServerDataImpl(String name,ServerAddress address, boolean lan) {
      super(name, address, lan);
    }
  }
}
