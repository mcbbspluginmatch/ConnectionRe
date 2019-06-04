package ren.taske.user;

import ren.taske.connection.Config;

public class PermissionUser extends PermissionStorage {

	final boolean isOwner;
	
	public PermissionUser(long userid) {
		super(Long.toString(userid));
		this.isOwner = Config.OWNER_ID == userid;
	}
	
	public static final String _APP_SPEAKABLE = "app.speakable";
	public static final String _APP_SILENT    = "app.silent";
	public static final String _APP_NICKNAME  = "app.nickname";
	
	@Override
	public void rebuild() {
		
		setPermission(_APP_SPEAKABLE, true);
		setPermission(_APP_SILENT,    true);
		setPermission(_APP_NICKNAME,  true);
		
	}
	
	@Override
	public boolean hasPermission(String key) {
		return isOwner || super.hasPermission(key);
	}
	
}
