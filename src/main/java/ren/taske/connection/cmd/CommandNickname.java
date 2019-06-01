package ren.taske.connection.cmd;

import java.util.ArrayList;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.sender.message.MessageBuilder;
import cc.moecraft.icq.sender.message.components.ComponentAt;
import cc.moecraft.icq.user.User;
import ren.taske.user.TUser;

public class CommandNickname implements EverywhereCommand {

	@Override
	public CommandProperties properties() {
		return new CommandProperties("nickname", "nick");
	}
	
	@Override
	public String run(EventMessage event, User sender, String command, ArrayList<String> args) {
		if(args.size() == 0) {
			TUser tu = new TUser(sender.getId());
			return retNickname(tu.getNickname(sender), sender, false);
		}
		if(args.size() > 0) {
			TUser tu = new TUser(sender.getId());
			tu.setNickname(args.get(0));
			return retNickname(tu.getNickname(sender), sender, true);
		}
		return null;
	}
	
	public static String retNickname(String nickname, User sender, boolean isNew) {
		
		MessageBuilder mb = new MessageBuilder();
		
		mb.add(new ComponentAt(sender.getId())).newLine();
		mb.add("Your nickname"+(isNew?" now ":" ")+"is").newLine();
		mb.add(nickname);
		
		return mb.toString();
		
	}
	
}
