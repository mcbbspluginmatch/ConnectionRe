package ren.taske.connection.database;

import static ren.taske.connection.ConnectionPlugin.log;

import java.util.HashMap;
import java.util.Map;

import cc.moecraft.icq.user.User;
import ren.taske.user.MinecraftUser;
import ren.taske.user.PermissionUser;
import ren.taske.user.TencentUser;

public class FileManager {

	protected static final Map<Long, TencentUser> TUSERS = new HashMap<>();
	protected static final Map<String, MinecraftUser> MUSERS = new HashMap<>();
	protected static final Map<Long, PermissionUser> PERMS = new HashMap<>();
	
	private static void loadTencentUser(long userid) {
		log("[FileManager] Loading Tencent user data file for "+userid);
		TUSERS.put(userid, new TencentUser(userid));
	}
	
	public static TencentUser getTencentUser(User user) {
		return getTencentUser(user.getId());
	}
	
	public static TencentUser getTencentUser(long userid) {
		if(!TUSERS.containsKey(userid)) loadTencentUser(userid);
		return TUSERS.get(userid);
	}
	
	private static void loadMinecraftUser(String userid) {
		log("[FileManager] Loading Minecraft user data file for "+userid);
		MUSERS.put(userid, new MinecraftUser(userid));
	}
	
	public static MinecraftUser getMinecraftUser(String userid) {
		if(!MUSERS.containsKey(userid)) loadMinecraftUser(userid);
		return MUSERS.get(userid);
	}
	
	private static void loadPermission(long userid) {
		log("[FileManager] Loading Tencent user permission data file for "+userid);
		PERMS.put(userid, new PermissionUser(userid));
	}
	
	public static PermissionUser getPermission(User user) {
		return getPermission(user.getId());
	}
	
	public static PermissionUser getPermission(long userid) {
		if(!PERMS.containsKey(userid)) loadPermission(userid);
		return PERMS.get(userid);
	}
	
}
