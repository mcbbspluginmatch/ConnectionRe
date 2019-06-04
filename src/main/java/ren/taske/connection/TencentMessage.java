package ren.taske.connection;

import org.bukkit.Bukkit;

import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;
import ren.taske.connection.database.FileManager;
import ren.taske.connection.util.McUtil;
import ren.taske.user.PermissionUser;
import ren.taske.user.TencentUser;

public class TencentMessage extends IcqListener {

	@EventHandler
	public void message(EventMessage evt) {
		User sender = evt.getSender();
		TencentUser tu = new TencentUser(sender.getId());
		String message = evt.getMessage();
		String username;
		
		// Permission Check
		// Node: app.speakable
		if(!FileManager.getPermission(sender).hasPermission(PermissionUser._APP_SPEAKABLE)) return;
		
		// Fix #4
		if(message.startsWith(Config.CMD_PREFIX)) {
			return;
		}
		
		// Check if starts with prefix
		if(Config.REQUIRE_PREFIX) {
			if(!hasPrefix(message)) return;
		}
		
		if(!tu.isSilent()) {
			if(tu.hasNickname()) {
				username = tu.getNickname(sender);
			} else {
				username = evt.getSender().getInfo().getNickname();
			}
			
			String msg = String.format("<%s> %s", username, replace(message.substring(Config.REQUIRE_PREFIX?1:0)));
			McUtil.bc(msg);
		}
		
	}
	
	public String replace(String str) {
		return str.replaceAll("\\[.*\\]", "#NOPE#");
	}
	
	public static boolean hasPrefix(String str) {
		if(str.startsWith("!"))      return true;
		if(str.startsWith("\uff01")) return true;
		return false;
	}
	
}
