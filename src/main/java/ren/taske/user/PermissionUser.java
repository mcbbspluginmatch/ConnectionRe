package ren.taske.user;

public class PermissionUser extends PermissionStorage {

	public PermissionUser(long userid) {
		super(Long.toString(userid));
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
	
}
