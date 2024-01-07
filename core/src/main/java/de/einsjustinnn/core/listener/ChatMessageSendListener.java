package de.einsjustinnn.core.listener;

import net.labymod.api.Laby;
import net.labymod.api.client.network.server.ServerAddress;
import net.labymod.api.client.network.server.ServerData;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatMessageSendEvent;
import net.labymod.api.labyconnect.LabyConnectSession;

public class ChatMessageSendListener {

  @Subscribe
  public void onChatMessageSend(ChatMessageSendEvent event) {

    String message = event.getMessage();

    if (message.startsWith("/status")) {

      String[] args = message.split(" ");

      if (args.length == 1) {
        this.sendMessage("/status <message>");
      } else if (args.length > 1) {

        String status = message.substring(8);

        if (status.length() > 32) {
          this.sendMessage("max status length 32 chars.");
          return;
        }

        ServerAddress serverAddress = new ServerAddress(status, 25565);
        ServerData serverData = new ServerDataImpl(status, serverAddress, false);

        LabyConnectSession labyConnectSession = Laby.labyAPI().labyConnect().getSession();

        if (labyConnectSession == null) {
          return;
        }

        labyConnectSession.sendCurrentServer(serverData, null, false);

        this.sendMessage("set status to " + status);

      }

      event.setCancelled(true);
    }
  }

  private void sendMessage(String message) {
    Laby.labyAPI().minecraft().chatExecutor().displayClientMessage(message);
  }

  private static class ServerDataImpl extends ServerData {
    public ServerDataImpl(String name,ServerAddress address, boolean lan) {
      super(name, address, lan);
    }
  }
}
