package ren.taske.connection;

import java.io.File;

import ren.taske.data.SimpleDataStorage;

public class Config {

	public static final SimpleDataStorage basic = new SimpleDataStorage(new File("ConnectionRe/config/basic.cfg"));
	public static final SimpleDataStorage tencent = new SimpleDataStorage(new File("ConnectionRe/config/tencent.cfg"));
	public static final SimpleDataStorage function = new SimpleDataStorage(new File("ConnectionRe/config/function.cfg"));
	
	private static final String _PORT_IN  = "port_in";
	private static final String _PORT_OUT = "port_out";
	private static final String _URL_OUT  = "url_out";
	
	private static final String _GROUP_ID       = "group_id";
	private static final String _OWNER_ID       = "onwer_id";
	private static final String _CMD_PREFIX     = "cmd_prefix";
	private static final String _REQUIRE_PREFIX = "require_prefix";
	
	private static final String _SERVER_START_STOP_MESSAGE = "server_start_stop_message";
	private static final String _PLAYER_JOIN_LEAVE_MESSAGE = "player_join_leave_message";
	
	public static int    PORT_IN;
	public static int    PORT_OUT;
	public static String URL_OUT;
	
	public static long    GROUP_ID;
	public static long    OWNER_ID;
	public static String  CMD_PREFIX;
	public static boolean REQUIRE_PREFIX;
	
	public static boolean SERVER_START_AND_STOP_MESSAGE;
	public static boolean PLAYER_JOIN_AND_LEAVE_MESSAGE;
	
	static {
		refresh();
	}
	
	public static void refresh() {
		
		/* **************************
		 *     Prepare
		 * **************************/
		
		if(!basic.has(_PORT_IN))  basic.setInteger(_PORT_IN, 25561);
		if(!basic.has(_PORT_OUT)) basic.setInteger(_PORT_OUT, 25560);
		if(!basic.has(_URL_OUT))  basic.setString(_URL_OUT, "127.0.0.1");
		
		if(!tencent.has(_GROUP_ID))       tencent.setLong(_GROUP_ID, 740764124L);
		if(!tencent.has(_OWNER_ID))       tencent.setLong(_OWNER_ID, 180017725L);
		if(!tencent.has(_CMD_PREFIX))     tencent.setString(_CMD_PREFIX, "!");
		if(!tencent.has(_REQUIRE_PREFIX)) tencent.setBoolean(_REQUIRE_PREFIX, true);
		
		if(!function.has(_SERVER_START_STOP_MESSAGE)) function.setBoolean(_SERVER_START_STOP_MESSAGE, true);
		if(!function.has(_PLAYER_JOIN_LEAVE_MESSAGE)) function.setBoolean(_PLAYER_JOIN_LEAVE_MESSAGE, true);
		
		/* **************************
		 *     Load
		 * **************************/
		
		PORT_IN  = basic.getInteger(_PORT_IN, 32760);
		PORT_OUT = basic.getInteger(_PORT_OUT, 80);
		URL_OUT  = basic.getString(_URL_OUT, "127.0.0.1");
		
		GROUP_ID       = tencent.getLong(_GROUP_ID, 740764124L);
		OWNER_ID       = tencent.getLong(_OWNER_ID, 180017725L);
		CMD_PREFIX     = tencent.getString(_CMD_PREFIX, "!");
		REQUIRE_PREFIX = tencent.getBoolean(_REQUIRE_PREFIX, true);
		
		SERVER_START_AND_STOP_MESSAGE = function.getBoolean(_SERVER_START_STOP_MESSAGE, true);
		PLAYER_JOIN_AND_LEAVE_MESSAGE = function.getBoolean(_PLAYER_JOIN_LEAVE_MESSAGE, true);
		
		/* **************************
		 *     Save
		 * **************************/
		
		basic.save();
		tencent.save();
		function.save();
		
	}
	
}
