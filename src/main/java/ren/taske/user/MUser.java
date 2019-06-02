package ren.taske.user;

import java.io.File;

import ren.taske.data.SimpleDataStorage;

public class MUser {

	public static final String _SILENT = "silent";
	
	final String name;
	
	final File datafile;
	final SimpleDataStorage data;
	
	public MUser(String name) {
		this.name = name;
		datafile = new File("ConnectionRe/data/mc/"+name+".dat");
		data = new SimpleDataStorage(datafile);
	}
	
	public void setSilent(boolean val) {
		data.setBoolean(_SILENT, val);
		data.save();
	}
	
	public boolean isSilent() {
		return data.getBoolean(_SILENT, false);
	}
	
}
