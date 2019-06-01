package ren.taske.connection.cmd;

import java.util.ArrayList;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.sender.message.MessageBuilder;
import cc.moecraft.icq.sender.message.components.ComponentAt;
import cc.moecraft.icq.user.User;
import ren.taske.user.TUser;

public class CommandSilent implements EverywhereCommand {

	@Override
	public CommandProperties properties() {
		return new CommandProperties("silent", "si");
	}
	
	
	
	@Override
	public String run(EventMessage event, User sender, String command, ArrayList<String> args) {
		TUser user = new TUser(sender.getId());
		int length = args.size();
		if(length == 0) {
			return getStatus(sender.getId(), user.isSilent());
		}
		if(length == 1) {
			boolean value = Boolean.parseBoolean(args.get(0));
			user.setSilent(value);
			return getStatus(sender.getId(), user.isSilent());
		}
		return null;
	}
	
	public static String getStatus(long id, boolean status) {
		
		MessageBuilder mb = new MessageBuilder();
		mb.add(new ComponentAt(id)).newLine();
		mb.add("You now are "+(status?"silent":"speakable"));
		
		return mb.toString();
		
	}
	
}
