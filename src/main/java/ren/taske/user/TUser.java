package ren.taske.user;

import java.io.File;

import cc.moecraft.icq.user.User;
import ren.taske.data.SimpleDataStorage;

public class TUser {

	final File datafile;
	final SimpleDataStorage data;
	
	public TUser(long userid) {
		datafile = new File("ConnectionRe/data/"+userid+".dat");
		data = new SimpleDataStorage(datafile);
	}
	
	public boolean hasNickname() {
		return !(data.get("nickname") == null);
	}
	
	public void setNickname(String nickname) {
		data.setString("nickname", nickname);
		data.save();
	}
	
	public String getNickname(String def) {
		return data.getString("nickname", def);
	}
	
	public String getNickname(long def) {
		return getNickname(Long.toString(def));
	}
	
	public String getNickname(User user) {
		return getNickname(user.getId());
	}
	
}
