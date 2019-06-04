package ren.taske.connection.util;

import org.bukkit.ChatColor;

import ren.taske.connection.ConnectionPlugin;

public class McUtil {

	public static String prefix() {
		return ChatColor.GOLD+"[ConnectionRe] "+ChatColor.RESET;
	}
	
	public static void bc(String str) {
		ConnectionPlugin.server().broadcastMessage(prefix()+str);
	}
	
}
