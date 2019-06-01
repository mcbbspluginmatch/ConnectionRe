package ren.taske.connection.util;

import cc.moecraft.icq.sender.message.MessageBuilder;
import cc.moecraft.icq.sender.message.components.ComponentAt;

public class BotUtil {

	public static String genRetMsg(long userid, String...strs) {
		MessageBuilder mb = new MessageBuilder();
		mb.add(new ComponentAt(userid)).newLine();
		for(String str : strs) mb.add(str).newLine();
		return mb.toString().trim();
	}
	
}
