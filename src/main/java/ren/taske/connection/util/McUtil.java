package ren.taske.connection.util;

import ren.taske.connection.ConnectionPlugin;

public class McUtil {

	public static void bc(String str) {
		ConnectionPlugin.server().broadcastMessage(str);
	}
	
}
