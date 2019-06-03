package ren.taske.connection.util;

import cc.moecraft.icq.sender.message.MessageBuilder;
import cc.moecraft.icq.sender.message.components.ComponentAt;
import cc.moecraft.icq.user.User;
import ren.taske.connection.ConnectionPlugin;

public class BotUtil {

	public static String genRetMsg(User user, String...strs) {
		return genRetMsg(user.getId(), strs);
	}
	
	public static String genRetMsg(long userid, String...strs) {
		MessageBuilder mb = new MessageBuilder();
		mb.add(new ComponentAt(userid)).newLine();
		for(String str : strs) mb.add(str).newLine();
		return mb.toString().trim();
	}
	
	private static final String MSG_UNAUTHORIZED = "You have no permission!";
	
	public static String getUnauthorizedMessage(User user) {
		return getUnauthorizedMessage(user.getId());
	}
	
	private static String getUnauthorizedMessage(long user) {
		return genRetMsg(user, MSG_UNAUTHORIZED);
	}
	
	public static boolean isOwner(User user) {
		return ConnectionPlugin.isOwner(user.getId());
	}
	
}
