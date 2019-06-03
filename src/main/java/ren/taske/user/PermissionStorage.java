package ren.taske.user;

import java.io.File;

import ren.taske.connection.ConnectionPlugin;
import ren.taske.data.SimpleDataStorage;

public abstract class PermissionStorage {

	protected final String username;
	
	protected final File permfile;
	protected final SimpleDataStorage perm;
	
	protected final boolean isNew;
	
	public PermissionStorage(String username) {
		this.username = username;
		permfile = new File("ConnectionRe/data/"+username+".perm.dat");
		isNew = !permfile.exists();
		perm = new SimpleDataStorage(permfile);
		if(isNew) {
			ConnectionPlugin.log("[FileManager/Perm] Creating new permission data for "+username+".");
			rebuild();
			perm.save();
		}
	}
	
	public void setPermission(String key, boolean val) {
		perm.setBoolean(key, val);
		perm.save();
	}
	
	public boolean hasPermission(String key) {
		return perm.getBoolean(key, false);
	}
	
	/**
	 * Invoke when creating new permission settings, it's used to set defaults value.
	 */
	public abstract void rebuild();
	
}
