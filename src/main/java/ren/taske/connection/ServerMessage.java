package ren.taske.connection;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ServerMessage implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent evt) {
		
		if(Config.PLAYER_JOIN_AND_LEAVE_MESSAGE) {
			String username = evt.getPlayer().getPlayer().getName();
			String msg = String.format("\uff3b%s\uff3d joined the game", username);
			ConnectionPlugin.getApi().sendGroupMsg(Config.GROUP_ID, msg);
		}
		
	}
	
	@EventHandler
	public void onPlayerLeft(PlayerQuitEvent evt) {
		
		if(Config.PLAYER_JOIN_AND_LEAVE_MESSAGE) {
			String username = evt.getPlayer().getPlayer().getName();
			String msg = String.format("\uff3b%s\uff3d left the game", username);
			ConnectionPlugin.getApi().sendGroupMsg(Config.GROUP_ID, msg);
		}
		
	}
	
}
