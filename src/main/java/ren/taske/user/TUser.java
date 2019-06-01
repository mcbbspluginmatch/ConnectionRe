package ren.taske.user;

import java.io.File;

import cc.moecraft.icq.user.User;
import ren.taske.data.SimpleDataStorage;

public class TUser {

	public static final String _NICKNAME = "nickname";
	public static final String _SILENT = "shut-up";
	
	final long userid;
	
	final File datafile;
	final SimpleDataStorage data;
	
	public TUser(long userid) {
		this.userid = userid;
		datafile = new File("ConnectionRe/data/"+userid+".dat");
		data = new SimpleDataStorage(datafile);
	}
	
	public boolean hasNickname() {
		return data.has(_NICKNAME);
	}
	
	public void setNickname(String nickname) {
		data.setString(_NICKNAME, nickname);
		data.save();
	}
	
	public String getNickname(String def) {
		return data.getString(_NICKNAME, def);
	}
	
	public String getNickname() {
		return getNickname(userid);
	}
	
	public String getNickname(long def) {
		return getNickname(Long.toString(def));
	}
	
	public String getNickname(User user) {
		return getNickname(user.getId());
	}
	
	public void setSilent(boolean status) {
		data.setBoolean(_SILENT, status);
		data.save();
	}
	
	public boolean isSilent() {
		return data.getBoolean(_SILENT, false);
	}
	
}
