package ren.taske.connection;

import org.bukkit.Bukkit;

import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;
import ren.taske.user.TUser;

public class TencentMessage extends IcqListener {

	@EventHandler
	public void message(EventMessage evt) {
		User sender = evt.getSender();
		TUser tu = new TUser(sender.getId());
		String message = evt.getMessage();
		String username;
		
		// Fix #4
		if(message.startsWith(Config.CMD_PREFIX)) {
			return;
		}
		
		// Check if starts with prefix
		if(Config.REQUIRE_PREFIX && !(message.startsWith("!") || message.startsWith("\uff01"))) {
			return;
		}
		
		if(!tu.isSilent()) {
			if(tu.hasNickname()) {
				username = tu.getNickname(sender);
			} else {
				username = evt.getSender().getInfo().getNickname();
			}
			
			String msg = String.format("<%s> %s", username, replace(message));
			Bukkit.broadcastMessage(msg);
			ConnectionPlugin.log(msg);
		}
		
	}
	
	public String replace(String str) {
		return str.replaceAll("\\[.*\\]", "#NOPE#");
	}
	
}
