package ren.taske.connection;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class McMessage implements Listener {

	@EventHandler
	public void message(AsyncPlayerChatEvent evt) {
		String message = evt.getMessage();
		String username = evt.getPlayer().getName();
		if(message.length() > 1 && (message.startsWith("!") || message.startsWith("\uff01"))) {
			message = message.substring(1);
			String msg = String.format("\uff3b%s\uff3d%s", username, message);
			ConnectionPlugin.getApi().sendGroupMsg(Config.GROUP_ID, msg);
			// Set the message in Minecraft to what outprinted in QQ
			evt.setMessage(message);
		}
	}
	
}
